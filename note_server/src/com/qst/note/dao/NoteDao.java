package com.qst.note.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sound.midi.Patch;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.qst.note.bean.NoteBean;
import com.qst.note.util.DBUtil;

public class NoteDao {
	Connection c = DBUtil.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;

	// ���note_table����һ������
	public Boolean insert(String title, String content, String noteTime, String tel) {
		int id = new UserDao().getIDbyTel(tel);// �����û��绰��ȡ�û�id
		if (id < 1) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		String nowTime = sdf.format(new Date());// ��ȡ��ǰʱ��

		try {
			pst = (PreparedStatement) c.prepareStatement(
					"insert into note_table(title,content,note_time,user_id,create_time)values(?,?,?,?,?)");
			pst.setString(1, title);
			pst.setString(2, content);
			pst.setString(3, noteTime);
			pst.setInt(4, id);
			pst.setString(5, nowTime);
			pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	// ����id��ѯ���ݿⲢ����һ��note
	public NoteBean getNoteByID(int id) {
		NoteBean note = new NoteBean();
		try {
			pst = (PreparedStatement) c.prepareStatement("select * from note_table where id=?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.first()) {
				note.setId(id);
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setCreateTime("create_time");
				note.setUpdateTime(rs.getString("update_time"));
				note.setNoteTime(rs.getString("note_time"));
				note.setUserId(rs.getInt("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return note;
	}

	// ����id�޸ķ��������ݿ���һ��������¼
	public boolean ModifyNote(int id, String title, String content, String noteTime) {
		if (id < 1) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		String nowTime = sdf.format(new Date());// ��ȡ��ǰʱ��

		try {
			pst = (PreparedStatement) c
					.prepareStatement("update note_table set title=?,content=?,note_time=?,update_time=? where id=?");
			pst.setString(1, title);
			pst.setString(2, content);
			pst.setString(3, noteTime);
			pst.setString(4, nowTime);
			pst.setInt(5, id);
			pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// ���ݵ绰���룬�����û������б�����¼
	public ArrayList<NoteBean> getAllNotes(String tel) {
		ArrayList<NoteBean> all = new ArrayList<NoteBean>();
		int id = new UserDao().getIDbyTel(tel);// �����û�����ȡ�û�id
		try {
			pst = (PreparedStatement) c.clientPrepareStatement("select * from note_table where user_id=?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				NoteBean note = new NoteBean();
				note.setId(rs.getInt("id"));
				note.setTitle(rs.getString("title"));
				note.setContent("content");
				note.setCreateTime("create_time");
				note.setUpdateTime("update_time");
				note.setNoteTime("note_time");
				note.setUserId(rs.getInt("user_id"));
				all.add(note);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	// ����idɾ��һ��������¼��ɾ���ɹ�����true��ʧ�ܷ���false
	public boolean deleteById(int id) {
		try {
			pst = (PreparedStatement) c.prepareStatement("delete from note_table where id=?");
			pst.setInt(1, id);
			pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}