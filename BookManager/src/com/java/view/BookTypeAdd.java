package com.java.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.java.bean.BookType;
import com.java.dao.BookTypeDao;
import com.java.util.DbUtil;
import com.java.util.StringUtil;
import com.mysql.jdbc.Connection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookTypeAdd extends JInternalFrame {
	private JTextField text_name;
	private JTextField text_desc;

	private BookTypeDao bookTypeDao = new BookTypeDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeAdd frame = new BookTypeAdd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookTypeAdd() {
		setIconifiable(true);
		setClosable(true);
		setTitle("\u56FE\u4E66\u7C7B\u522B\u6DFB\u52A0");
		setBounds(100, 100, 501, 582);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BookTypeAdd.class.getResource("/images/bookTypeAdd.jpg")));

		JLabel label = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0\uFF1A");
		label.setFont(new Font("SimSun", Font.PLAIN, 18));

		text_name = new JTextField();
		text_name.setFont(new Font("SimSun", Font.PLAIN, 18));
		text_name.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0\uFF1A");
		lblNewLabel_1.setFont(new Font("SimSun", Font.PLAIN, 18));

		text_desc = new JTextField();
		text_desc.setFont(new Font("SimSun", Font.PLAIN, 18));
		text_desc.setColumns(10);

		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBookTypeActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(BookTypeAdd.class.getResource("/images/Add.png")));
		button.setFont(new Font("SimSun", Font.PLAIN, 18));

		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(BookTypeAdd.class.getResource("/images/button_rotate.png")));
		button_1.setFont(new Font("SimSun", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addGap(28)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(label)
												.addComponent(lblNewLabel_1)))
								.addGroup(groupLayout.createSequentialGroup().addGap(82)
										.addComponent(button, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addGroup(groupLayout
								.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(text_name, GroupLayout.PREFERRED_SIZE, 245,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(text_desc, GroupLayout.PREFERRED_SIZE, 245,
												GroupLayout.PREFERRED_SIZE))
								.addGap(41))
								.addGroup(Alignment.TRAILING,
										groupLayout
												.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(button_1).addGap(79)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addComponent(lblNewLabel).addGap(70)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label).addComponent(
						text_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(56)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
						.addComponent(text_desc, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
				.addGap(103)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(81, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);

	}

	// 图书类别添加事件处理
	private void addBookTypeActionPerformed(ActionEvent evt) {
		String name = this.text_name.getText();// 获取图书类别名称
		String desc = this.text_desc.getText();// 获取图书类别描述
		if (StringUtil.isEmpty(name)) {
			JOptionPane.showMessageDialog(null, "图书类别名称不能为空！");// 提示信息
			return;
		}
		if (StringUtil.isEmpty(desc)) {
			JOptionPane.showMessageDialog(null, "图书类别描述不能为空！");// 提示信息
			return;
		}

		BookType bookType = new BookType(name, desc);
		Connection c = null;
		try {
			c = DbUtil.getConnection();
			int addNum = bookTypeDao.add(c, bookType);
			if (addNum == 1) {
				JOptionPane.showMessageDialog(null, "添加成功！");
				resetNull();
			} else {
				JOptionPane.showMessageDialog(null, "添加失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(c, null, null);
		}

	}

	// 图书重置事件处理
	private void resetActionPerformed(ActionEvent evt) {
		resetNull();
	}

	public void resetNull() {
		this.text_name.setText("");
		this.text_desc.setText("");
	}

}
