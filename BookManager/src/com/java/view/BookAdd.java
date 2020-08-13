package com.java.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.java.bean.Book;
import com.java.bean.BookType;
import com.java.dao.BookDao;
import com.java.dao.BookTypeDao;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class BookAdd extends JInternalFrame {

	private JPanel contentPane;
	private JTextField text_name;
	private JTextField text_author;
	private JTextField text_price;
	private JTextField text_desc;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox combo_typeid;
	private JRadioButton text_girl;
	private JRadioButton text_boy;

	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookAdd frame = new BookAdd();
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
	public BookAdd() {
		setIconifiable(true);
		setClosable(true);
		setResizable(false);
		setTitle("\u56FE\u4E66\u4FE1\u606F\u6DFB\u52A0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 682);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel label = new JLabel("");
		label.setFont(new Font("SimSun", Font.PLAIN, 18));
		label.setIcon(new ImageIcon(BookAdd.class.getResource("/images/1.jpg")));

		JLabel label_1 = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		label_1.setFont(new Font("SimSun", Font.PLAIN, 20));

		text_name = new JTextField();
		text_name.setFont(new Font("SimSun", Font.PLAIN, 20));
		text_name.setColumns(10);

		JLabel label_2 = new JLabel("\u56FE\u4E66\u4F5C\u8005\uFF1A");
		label_2.setFont(new Font("SimSun", Font.PLAIN, 20));

		text_author = new JTextField();
		text_author.setFont(new Font("SimSun", Font.PLAIN, 20));
		text_author.setColumns(10);

		JLabel label_3 = new JLabel("\u4F5C\u8005\u6027\u522B\uFF1A");
		label_3.setFont(new Font("SimSun", Font.PLAIN, 20));

		text_girl = new JRadioButton("\u5973");
		buttonGroup.add(text_girl);
		text_girl.setSelected(true);
		text_girl.setFont(new Font("SimSun", Font.PLAIN, 20));

		text_boy = new JRadioButton("\u7537");
		buttonGroup.add(text_boy);
		text_boy.setFont(new Font("SimSun", Font.PLAIN, 20));

		JLabel label_4 = new JLabel("\u56FE\u4E66\u5355\u4EF7\uFF1A");
		label_4.setFont(new Font("SimSun", Font.PLAIN, 20));

		text_price = new JTextField();
		text_price.setFont(new Font("SimSun", Font.PLAIN, 20));
		text_price.setColumns(10);

		JLabel label_5 = new JLabel("\u56FE\u4E66\u7C7B\u522B\uFF1A");
		label_5.setFont(new Font("SimSun", Font.PLAIN, 20));

		combo_typeid = new JComboBox();
		combo_typeid.setFont(new Font("SimSun", Font.PLAIN, 20));

		JLabel label_6 = new JLabel("\u56FE\u4E66\u63CF\u8FF0\uFF1A");
		label_6.setFont(new Font("SimSun", Font.PLAIN, 20));

		text_desc = new JTextField();
		text_desc.setColumns(10);

		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBookActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(BookAdd.class.getResource("/images/Add.png")));
		button.setFont(new Font("SimSun", Font.PLAIN, 20));

		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(BookAdd.class.getResource("/images/button_rotate.png")));
		button_1.setFont(new Font("SimSun", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(label)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(44).addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup().addComponent(label_3)
														.addGap(18).addComponent(text_girl)
														.addPreferredGap(ComponentPlacement.RELATED, 46,
																Short.MAX_VALUE)
														.addComponent(text_boy))
												.addGroup(gl_contentPane.createSequentialGroup().addComponent(label_1)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(text_name, GroupLayout.PREFERRED_SIZE, 148,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPane.createSequentialGroup().addComponent(label_5)
														.addGap(18).addComponent(combo_typeid, 0,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
										.addGap(52)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_contentPane.createSequentialGroup().addComponent(label_2)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(text_author, GroupLayout.PREFERRED_SIZE, 149,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPane.createSequentialGroup().addComponent(label_4)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(text_price))))
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(label_6)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(text_desc))))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(193)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addGap(111)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(3, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addComponent(label).addGap(48)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(label_1)
								.addComponent(text_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2).addComponent(text_author, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(38)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(label_3)
								.addComponent(text_girl).addComponent(text_boy).addComponent(label_4)
								.addComponent(text_price, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(31)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(label_5)
								.addComponent(combo_typeid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(46).addComponent(label_6))
								.addGroup(gl_contentPane.createSequentialGroup().addGap(35).addComponent(text_desc,
										GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
						.addGap(43)));
		contentPane.setLayout(gl_contentPane);
		fillBookType();
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);// 关闭JFrame
	}

	// 添加图书信息
	private void addBookActionPerformed(ActionEvent e) {
		String bookName = this.text_name.getText();
		String author = this.text_author.getText();
		String price = this.text_price.getText();
		String bookDesc = this.text_desc.getText();

		String sex = "";
		if (text_girl.isSelected()) {
			sex = "女";
		} else {
			sex = "男";
		}
		if (StringUtil.isEmpty(bookName)) {
			JOptionPane.showMessageDialog(null, "图书名称不能为空！");
			return;
		}
		if (StringUtil.isEmpty(author)) {
			JOptionPane.showMessageDialog(null, "图书作者不能为空！");
			return;
		}
		if (StringUtil.isEmpty(price)) {
			JOptionPane.showMessageDialog(null, "图书单价不能为空！");
			return;
		}
		if (StringUtil.isEmpty(bookDesc)) {
			JOptionPane.showMessageDialog(null, "图书描述不能为空！");
			return;
		}

		BookType bookType = (BookType) combo_typeid.getSelectedItem();
		int bookTypeId = bookType.getId();
		Book book = new Book(bookName, author, sex, Float.parseFloat(price), bookDesc, bookTypeId);
		Connection c = null;
		c = DbUtil.getConnection();
		try {
			int addNum = bookDao.add(c, book);
			if (addNum == 1) {
				JOptionPane.showMessageDialog(null, "添加成功！");
				resetNull();
			} else {
				JOptionPane.showMessageDialog(null, "添加失败！");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			DbUtil.close(c, null, null);
		}
	}

	// 填充图书类别
	@SuppressWarnings("unchecked")
	private void fillBookType() {
		Connection c = null;
		BookType bookType = null;
		c = DbUtil.getConnection();
		try {
			ResultSet rSet = bookTypeDao.list(c, new BookType());
			while (rSet.next()) {
				bookType = new BookType();
				bookType.setId(rSet.getInt("id"));
				bookType.setBookTypeName(rSet.getString("bookTypeName"));
				this.combo_typeid.addItem(bookType);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(c, null, null);
		}

	}

	// 重置事件处理
	private void resetActionPerformed(ActionEvent evt) {
		resetNull();
	}

	public void resetNull() {
		this.text_name.setText("");
		this.text_author.setText("");
		this.text_price.setText("");
		this.text_desc.setText("");
	}
}
