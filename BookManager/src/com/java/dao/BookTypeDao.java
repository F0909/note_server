package com.java.dao;

import java.sql.ResultSet;

import com.java.bean.BookType;
import com.java.util.StringUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class BookTypeDao {

	// 图书类别添加Dao
	public int add(Connection c, BookType bookType) throws Exception {
		String sql = "insert into t_booktype values(null,?,?)";
		PreparedStatement pstmt = (PreparedStatement) c.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookTypeName());// 获取界面传过来的图书类别名称
		pstmt.setString(2, bookType.getBookTypeDesc());// 获取界面传过来的图书类别描述
		return pstmt.executeUpdate();
	}

	// 查询图书类别集合
	public ResultSet list(Connection c, BookType bookType) throws Exception {
		StringBuffer sBuffer = new StringBuffer("select * from t_booktype");
		if (StringUtil.isNotEmpty(bookType.getBookTypeName())) {
			sBuffer.append(" and bookTypeName like '%" + bookType.getBookTypeName() + "%'");
		}
		PreparedStatement pstmt = (PreparedStatement) c
				.prepareStatement(sBuffer.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

	// 删除图书类别
	public int delete(Connection c, String id) throws Exception {
		String sql = "delete from t_booktype where id=?";
		PreparedStatement pstmt = (PreparedStatement) c.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}

	// 更新图书类别
	public int update(Connection c, BookType bookType) throws Exception {
		String sql = "update t_booktype set bookTypeName=?,bookTypeDesc=? where id=?";
		PreparedStatement pstmt = (PreparedStatement) c.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookTypeName());
		pstmt.setString(2, bookType.getBookTypeDesc());
		pstmt.setInt(3, bookType.getId());
		return pstmt.executeUpdate();
	}
}
