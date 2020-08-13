package com.java.dao;

import java.sql.ResultSet;

import com.java.bean.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UserDao {
	// 登录验证
	public User login(Connection c, User user) throws Exception {
		User resultUser = null;
		String sql = "select * from t_user where userName=? and passWord=?";
		PreparedStatement pstmt = (PreparedStatement) c.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());// 获取界面传过来的用户名
		pstmt.setString(2, user.getPassWord());// 获取界面传过来的密码
		ResultSet rs = pstmt.executeQuery();// 执行sql返回结果值
		if (rs.next()) {// 检查是否有下一条记录，如果有进行实例化
			resultUser = new User();// 实例化
			resultUser.setId(rs.getInt("id"));// 设置数据(结果集的id)
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassWord(rs.getString("passWord"));
		}
		return resultUser;
	}

	// 用户注册
	public int addUser(Connection c, User user) throws Exception {
		String sql = "insert into t_user values(null,?,?)";
		PreparedStatement pstmt = (PreparedStatement) c.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());// 获取界面传过来的用户名
		pstmt.setString(2, user.getPassWord());// 获取界面传过来的密码
		return pstmt.executeUpdate();// 执行sql返回结果值

	}

	// 修改密码
	public int update(Connection c, User user) throws Exception {
		String sql = "update t_user set username=? , password=? where id=?";
		PreparedStatement pstmt = (PreparedStatement) c.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassWord());
		pstmt.setInt(3, user.getId());
		return pstmt.executeUpdate();// 执行sql返回结果值
	}
}
