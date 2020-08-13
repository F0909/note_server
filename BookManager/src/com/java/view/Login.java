package com.java.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.java.bean.User;
import com.java.dao.UserDao;
import com.java.util.DbUtil;
import com.java.util.StringUtil;
import com.mysql.jdbc.Connection;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passWordTxt;

	private UserDao userDao = new UserDao();
	private static int login_id;
	private static String login_username;
	private static String login_password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		// 改变默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, value);
			}
		}

		setResizable(false);
		setTitle("\u7BA1\u7406\u5458\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 25));
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/Books.png")));

		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setFont(new Font("SimSun", Font.PLAIN, 20));
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/images/user.png")));

		JLabel lblNewLabel_2 = new JLabel("\u5BC6  \u7801\uFF1A");
		lblNewLabel_2.setFont(new Font("SimSun", Font.PLAIN, 20));
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/images/lock_password.png")));

		userNameTxt = new JTextField();
		userNameTxt.setFont(new Font("SimSun", Font.PLAIN, 20));
		userNameTxt.setColumns(10);

		passWordTxt = new JPasswordField();
		passWordTxt.setFont(new Font("SimSun", Font.PLAIN, 20));

		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/images/login_dl.png")));
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 22));

		JButton btnNewButton_1 = new JButton("\u6CE8\u518C");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Regester regester = new Regester();
				regester.setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Login.class.getResource("/images/Add.png")));
		btnNewButton_1.setFont(new Font("SimSun", Font.PLAIN, 22));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(157).addGroup(gl_contentPane
										.createParallelGroup(Alignment.LEADING, false).addComponent(lblNewLabel_1)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(btnNewButton).addComponent(lblNewLabel_2,
																GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addComponent(passWordTxt).addComponent(userNameTxt,
																GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
												.addComponent(btnNewButton_1)))
								.addGroup(gl_contentPane.createSequentialGroup().addGap(203).addComponent(lblNewLabel)))
						.addGap(173)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(32).addComponent(lblNewLabel).addGap(82)
				.addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1).addComponent(userNameTxt,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(49)
				.addGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel_2).addComponent(passWordTxt,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(35)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		contentPane.setLayout(gl_contentPane);

		this.setLocationRelativeTo(null);// 设置JFrame默认居中
	}

	// 登录事件处理
	private void loginActionPerformed(ActionEvent evt) {
		String userName = this.userNameTxt.getText();// 获取用户名
		String passWord = new String(this.passWordTxt.getPassword());// 获取密码
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		if (StringUtil.isEmpty(passWord)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}

		User user = new User(userName, passWord);
		Connection c = null;
		c = DbUtil.getConnection();
		try {
			User cUser = userDao.login(c, user);
			if (cUser != null) {
				dispose();
				login_id = cUser.getId();
				login_username = cUser.getUserName();
				login_password = cUser.getPassWord();
				new Main().setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "用户名或密码错误！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static int getLogin_id() {
		return login_id;
	}

	public static void setLogin_id(int login_id) {
		Login.login_id = login_id;
	}

	public static String getLogin_username() {
		return login_username;
	}

	public static void setLogin_username(String login_username) {
		Login.login_username = login_username;
	}

	public static String getLogin_password() {
		return login_password;
	}

	public static void setLogin_password(String login_password) {
		Login.login_password = login_password;
	}

}
