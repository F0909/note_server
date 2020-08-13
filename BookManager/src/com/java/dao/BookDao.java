package com.java.dao;

import java.sql.ResultSet;

import com.java.bean.Book;
import com.java.util.StringUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class BookDao {

	// 判断图书类别下边是否有图书
	public boolean existBookByBookType(Connection c, String typeId) throws Exception {
		String sql = "select * from t_book where bookTypeId=?";
		PreparedStatement pstmt;
		pstmt = (PreparedStatement) c.prepareStatement(sql);
		pstmt.setString(1, typeId);
		ResultSet rs = pstmt.executeQuery();
		return rs.next();
	}

	// 图书信息添加Dao
	public int add(Connection c, Book book) throws Exception {
		String sql = "insert into t_book values (null,?,?,?,?,?,?)";
		PreparedStatement pstmt = (PreparedStatement) c.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getSex());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setString(5, book.getBookDesc());
		pstmt.setInt(6, book.getBookTypeId());
		return pstmt.executeUpdate();
	}

	// 图书信息查询
	public ResultSet list(Connection c, Book book) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_book b,t_booktype bt where b.bookTypeId=bt.id");
		if (StringUtil.isNotEmpty(book.getBookName())) {
			sb.append(" and b.bookName like '%" + book.getBookName() + "%'");
		}
		if (StringUtil.isNotEmpty(book.getAuthor())) {
			sb.append(" and b.author like '%" + book.getAuthor() + "%'");
		}
		if (book.getBookTypeId() != null && book.getBookTypeId() != -1) {
			sb.append(" and b.bookTypeId=" + book.getBookTypeId());
		}
		PreparedStatement pstmt = (PreparedStatement) c.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	// 删除图书信息
	public int delete(Connection c, String id) throws Exception {
		String sql = "delete from t_book where id=?";
		PreparedStatement pstmt = (PreparedStatement) c.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}

	// 修改图书信息
	public int update(Connection c, Book book) throws Exception {
		String sql = "update t_book set bookName=?,author=?,sex=?,price=?,bookDesc=? where id=?";
		PreparedStatement pstmt = (PreparedStatement) c.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getSex());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setString(5, book.getBookDesc());
		pstmt.setInt(6, book.getId());
		return pstmt.executeUpdate();
	}
}
