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

import com.java.bean.User;
import com.java.dao.UserDao;
import com.java.util.DbUtil;
import com.java.util.StringUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.log.Log;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PassUpdate extends JInternalFrame {
	private JTextField text_name;
	private JPasswordField old_pass;
	private JPasswordField new_pass;
	private JPasswordField new_repass;

	private UserDao userDao = new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassUpdate frame = new PassUpdate();
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
	public PassUpdate() {
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 631, 573);

		JLabel label = new JLabel("\u4FEE\u6539\u5BC6\u7801");
		label.setFont(new Font("SimSun", Font.BOLD, 25));
		label.setIcon(new ImageIcon(PassUpdate.class.getResource("/images/Books.png")));

		JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_1.setFont(new Font("SimSun", Font.PLAIN, 20));

		text_name = new JTextField();
		text_name.setEnabled(false);
		text_name.setFont(new Font("SimSun", Font.PLAIN, 20));
		text_name.setColumns(10);

		old_pass = new JPasswordField();
		old_pass.setFont(new Font("SimSun", Font.PLAIN, 20));
		old_pass.setColumns(10);

		JLabel label_2 = new JLabel("\u65E7\u5BC6\u7801\uFF1A");
		label_2.setFont(new Font("SimSun", Font.PLAIN, 20));

		JLabel label_3 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		label_3.setFont(new Font("SimSun", Font.PLAIN, 20));

		new_pass = new JPasswordField();
		new_pass.setFont(new Font("SimSun", Font.PLAIN, 20));

		JLabel lblNewLabel = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 20));

		new_repass = new JPasswordField();
		new_repass.setFont(new Font("SimSun", Font.PLAIN, 20));

		JButton button = new JButton("\u786E\u8BA4\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePassWordActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(PassUpdate.class.getResource("/images/Modify.png")));
		button.setFont(new Font("SimSun", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(216).addComponent(label))
						.addGroup(groupLayout.createSequentialGroup().addGap(139).addGroup(groupLayout
								.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(label_2).addComponent(label_3).addComponent(label_1))
										.addGap(38))
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblNewLabel).addGap(18)))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(new_pass, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
										.addComponent(text_name, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 204,
												Short.MAX_VALUE)
										.addComponent(old_pass, 204, 204, 204)
										.addComponent(new_repass, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))))
				.addGap(154))
				.addGroup(groupLayout.createSequentialGroup().addGap(245)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(235, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(71).addComponent(label).addGap(50)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label_1).addComponent(
						text_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(27)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(old_pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
				.addGap(25)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label_3).addComponent(
						new_pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(27)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(
						new_repass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(41).addComponent(button, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(57, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);

		this.text_name.setText(Login.getLogin_username());// 调用用户名

	}

	// 修改密码
	private void updatePassWordActionPerformed(ActionEvent evt) {
		String old_pass = new String(this.old_pass.getPassword());
		String new_pass = new String(this.new_pass.getPassword());
		String new_repass = new String(this.new_repass.getPassword());
		if (StringUtil.isEmpty(old_pass)) {
			JOptionPane.showMessageDialog(null, "旧密码不能为空！");
			return;
		}
		if (StringUtil.isEmpty(new_pass)) {
			JOptionPane.showMessageDialog(null, "新密码不能为空！");
			return;
		}
		if (StringUtil.isEmpty(new_repass)) {
			JOptionPane.showMessageDialog(null, "确认密码不能为空！");
			return;
		}
		if ((!(new_pass.equals(new_repass)))) {
			JOptionPane.showMessageDialog(null, "两次密码不一致，请重新输入！");
			return;
		}
		if (!(old_pass.equals(Login.getLogin_password()))) {
			JOptionPane.showMessageDialog(null, "旧密码和新密码不能相同！");
			return;
		}

		Connection c = null;
		try {
			c = DbUtil.getConnection();
			User user = new User(Login.getLogin_id(), Login.getLogin_username(), new_pass);
			int updateNum = userDao.update(c, user);
			if (updateNum == 1) {
				JOptionPane.showMessageDialog(null, "修改成功,请退出重新登录！");
				dispose();// 关闭当前窗口
			} else {
				JOptionPane.showMessageDialog(null, "修改失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
