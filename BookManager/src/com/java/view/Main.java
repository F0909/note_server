package com.java.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Main extends JFrame {
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("\u56FE\u793A\u7BA1\u7406\u7CFB\u7EDF\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1036, 841);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("\u7CFB\u7EDF\u83DC\u5355");
		mnNewMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		mnNewMenu.setIcon(new ImageIcon(Main.class.getResource("/images/settings.png")));
		menuBar.add(mnNewMenu);

		JMenuItem menuItem = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PassUpdate passUpdate = new PassUpdate();
				passUpdate.setVisible(true);
				desktopPane.add(passUpdate);
			}
		});
		menuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		mnNewMenu.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("\u5B89\u5168\u9000\u51FA");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "您确定要退出系统吗？");
				if (result == 0) {
					dispose();
				}
			}
		});
		menuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		mnNewMenu.add(menuItem_1);

		JMenu mnNewMenu_1 = new JMenu("\u56FE\u4E66\u7BA1\u7406");
		mnNewMenu_1.setIcon(new ImageIcon(Main.class.getResource("/images/arzo.png")));
		mnNewMenu_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmjava = new JMenuItem("\u56FE\u4E66\u6DFB\u52A0");
		mntmjava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookAdd bookAdd = new BookAdd();
				bookAdd.setVisible(true);
				desktopPane.add(bookAdd);
			}
		});
		mntmjava.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		mnNewMenu_1.add(mntmjava);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u56FE\u4E66\u7EF4\u62A4");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookManager bookManager = new BookManager();
				bookManager.setVisible(true);
				desktopPane.add(bookManager);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenu mnNewMenu_4 = new JMenu("\u7C7B\u522B\u7BA1\u7406");
		mnNewMenu_4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		mnNewMenu_4.setIcon(new ImageIcon(Main.class.getResource("/images/bookcase.png")));
		menuBar.add(mnNewMenu_4);

		JMenuItem menuItem_5 = new JMenuItem("\u7C7B\u522B\u6DFB\u52A0");
		menuItem_5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookTypeAdd bookTypeAdd = new BookTypeAdd();
				bookTypeAdd.setVisible(true);
				desktopPane.add(bookTypeAdd);
			}
		});
		mnNewMenu_4.add(menuItem_5);

		JMenuItem menuItem_6 = new JMenuItem("\u7C7B\u522B\u7EF4\u62A4");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookTypeManager bookTypeManager = new BookTypeManager();
				bookTypeManager.setVisible(true);
				desktopPane.add(bookTypeManager);
			}
		});
		menuItem_6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		mnNewMenu_4.add(menuItem_6);

		JMenu menu = new JMenu("\u5173\u4E8E");
		menu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		menu.setIcon(new ImageIcon(Main.class.getResource("/images/page_star.png")));
		menuBar.add(menu);

		JMenuItem menuItem_4 = new JMenuItem("\u5E2E\u52A9");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.setVisible(true);
				desktopPane.add(about);
			}
		});
		menuItem_4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		menu.add(menuItem_4);

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
				gl_desktopPane.createParallelGroup(Alignment.LEADING).addGap(0, 1024, Short.MAX_VALUE));
		gl_desktopPane.setVerticalGroup(
				gl_desktopPane.createParallelGroup(Alignment.LEADING).addGap(0, 768, Short.MAX_VALUE));
		desktopPane.setLayout(gl_desktopPane);

		// this.setExtendedState(JFrame.MAXIMIZED_BOTH);// 最大化
		this.setLocationRelativeTo(null);// 设置JFrame默认居中
	}

}
