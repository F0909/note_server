package com.java.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.java.bean.User;
import com.java.dao.UserDao;
import com.java.util.DbUtil;
import com.java.util.StringUtil;
import com.mysql.jdbc.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Regester extends JFrame {

	private JPanel contentPane;
	private JTextField text_name;
	private JPasswordField text_pass;
	private JPasswordField text_repass;

	private UserDao userDao = new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Regester frame = new Regester();
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
	public Regester() {
		setTitle("\u7528\u6237\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("\u7528\u6237\u6CE8\u518C");
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 30));
		lblNewLabel.setIcon(new ImageIcon(Regester.class.getResource("/images/Books.png")));

		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setFont(new Font("SimSun", Font.PLAIN, 18));
		lblNewLabel_1.setIcon(new ImageIcon(Regester.class.getResource("/images/user.png")));

		text_name = new JTextField();
		text_name.setFont(new Font("SimSun", Font.PLAIN, 18));
		text_name.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\u5BC6 \u7801\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(Regester.class.getResource("/images/lock_password.png")));
		lblNewLabel_2.setFont(new Font("SimSun", Font.PLAIN, 18));

		text_pass = new JPasswordField();
		text_pass.setFont(new Font("SimSun", Font.PLAIN, 18));

		JLabel lblNewLabel_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblNewLabel_3.setIcon(new ImageIcon(Regester.class.getResource("/images/lock_password.png")));
		lblNewLabel_3.setFont(new Font("SimSun", Font.PLAIN, 18));

		text_repass = new JPasswordField();
		text_repass.setFont(new Font("SimSun", Font.PLAIN, 18));

		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regesterActionPerformed(e);
			}
		});
		button.setFont(new Font("SimSun", Font.PLAIN, 18));
		button.setIcon(new ImageIcon(Regester.class.getResource("/images/Add.png")));

		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		button_1.setFont(new Font("SimSun", Font.PLAIN, 18));
		button_1.setIcon(new ImageIcon(Regester.class.getResource("/images/button_rotate.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(185).addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(104)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel_1).addComponent(lblNewLabel_2))
												.addGap(36)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addComponent(text_pass).addComponent(text_name,
																GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)))
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel_3)
												.addGap(18).addComponent(text_repass))))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(119).addComponent(button).addGap(111)
								.addComponent(button_1)))
				.addContainerGap(135, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(48).addComponent(lblNewLabel).addGap(69)
				.addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1).addComponent(text_name,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(32)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(text_pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
				.addGap(29)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_3)
						.addComponent(text_repass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE).addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(button).addComponent(button_1))
				.addGap(64)));
		contentPane.setLayout(gl_contentPane);

		this.setLocationRelativeTo(null);// 设置JFrame默认居中
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 只关闭当前一个进程
	}

	// 注册事件处理
	protected void regesterActionPerformed(ActionEvent event) {
		String userName = this.text_name.getText();// 获取用户名
		String passWord = new String(this.text_pass.getPassword());// 获取密码
		String repassWord = new String(this.text_repass.getPassword());// 获取确认密码
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");// 提示信息
			return;
		}
		if (StringUtil.isEmpty(passWord)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");// 提示信息
			return;
		}
		if (StringUtil.isEmpty(repassWord)) {
			JOptionPane.showMessageDialog(null, "确认密码不能为空！");// 提示信息
			return;
		}
		if (passWord.equals(repassWord)) {
			Connection c = null;
			c = DbUtil.getConnection();
			try {
				User user = new User(userName, passWord);
				int addNum = userDao.addUser(c, user);
				if (addNum == 1) {
					JOptionPane.showMessageDialog(null, "注册成功！");// 提示信息
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "注册失败！");// 提示信息
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DbUtil.close(c, null, null);
			}
		} else {
			JOptionPane.showMessageDialog(null, "两次密码不一致！");// 提示信息
			return;
		}

	}

	// 重置事件处理
	private void resetActionPerformed(ActionEvent event) {
		this.text_name.setText("");// 清空用户名
		this.text_pass.setText("");// 清空密码
		this.text_repass.setText("");// 清空确认密码

	}
}
