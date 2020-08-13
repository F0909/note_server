package com.qst.note.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.qst.note.bean.UserBean;
import com.qst.note.util.DBUtil;

public class UserDao {
	Connection c = DBUtil.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;

	// 向user表中插入一条数据（注册）
	public boolean regist(UserBean user) {
		try {
			pst = (PreparedStatement) c.prepareStatement("insert into user(name,pass,tel,qq,wechat)values(?,?,?,?,?)");
			pst.setString(1, user.getName());
			pst.setString(2, user.getPass());
			pst.setString(3, user.getTel());
			pst.setString(4, user.getQq());
			pst.setString(5, user.getWechat());
			boolean result = pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 验证密码和用户名是否正确（登录）
	public boolean login(String tel, String pass) {
		try {
			pst = (PreparedStatement) c.prepareStatement("select pass from user where tel=?");
			pst.setString(1, tel);
			rs = pst.executeQuery();
			if (rs.first()) {
				String passInDb = rs.getString("pass");
				if (passInDb.equals(pass)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(c, pst, rs);
		}

		return false;
	}

	// 根据用户手机号返回ID
	public int getIDbyTel(String tel) {
		int id = 0;
		try {
			pst = (PreparedStatement) c.prepareStatement("select id from user where tel=?");
			pst.setString(1, tel);
			ResultSet rs = pst.executeQuery();
			if (rs.first()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(c, pst, null);
		}
		return id;
	}

}
