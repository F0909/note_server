package com.java.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.java.bean.Book;
import com.java.bean.BookType;
import com.java.dao.BookDao;
import com.java.dao.BookTypeDao;
import com.java.util.DbUtil;
import com.java.util.StringUtil;
import com.mysql.jdbc.Connection;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookManager extends JInternalFrame {
	private JTable bookTable;
	private JTextField s_nameTxt;
	private JTextField s_authorTxt;

	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();

	private JComboBox s_bookTypeJcb = null;
	private JTextField text_id;
	private JTextField text_name;
	private JTextField text_author;
	private JTextField text_price;
	private JTextField text_bookDesc;
	private JRadioButton text_woman;
	private JRadioButton text_man;
	private JComboBox text_bookType;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManager frame = new BookManager();
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
	public BookManager() {
		setIconifiable(true);
		setClosable(true);
		setTitle("\u56FE\u4E66\u7EF4\u62A4");
		setBounds(100, 100, 1097, 970);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BookManager.class.getResource("/images/\u672A\u6807\u9898-1.jpg")));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 行单击事件
				try {
					bookMousePressed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "\u641C\u7D20\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
								.addGroup(groupLayout.createSequentialGroup().addGap(48)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 982,
														Short.MAX_VALUE)
												.addComponent(scrollPane, Alignment.LEADING)
												.addComponent(panel_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE))))
						.addGap(1)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addComponent(lblNewLabel).addGap(48)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE).addGap(45)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE).addGap(64)
				.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(83, Short.MAX_VALUE)));

		JLabel label_3 = new JLabel("\u56FE\u4E66\u7F16\u53F7\uFF1A");
		label_3.setFont(new Font("SimSun", Font.PLAIN, 18));

		text_id = new JTextField();
		text_id.setFont(new Font("SimSun", Font.PLAIN, 18));
		text_id.setColumns(10);

		JLabel label_4 = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		label_4.setFont(new Font("SimSun", Font.PLAIN, 18));

		text_name = new JTextField();
		text_name.setFont(new Font("SimSun", Font.PLAIN, 18));
		text_name.setColumns(10);

		JLabel label_5 = new JLabel("\u56FE\u4E66\u4F5C\u8005\uFF1A");
		label_5.setFont(new Font("SimSun", Font.PLAIN, 18));

		text_author = new JTextField();
		text_author.setFont(new Font("SimSun", Font.PLAIN, 18));
		text_author.setColumns(10);

		JLabel label_6 = new JLabel("\u4F5C\u8005\u6027\u522B\uFF1A");
		label_6.setFont(new Font("SimSun", Font.PLAIN, 18));

		text_man = new JRadioButton("\u7537");
		buttonGroup.add(text_man);
		text_man.setSelected(true);
		text_man.setFont(new Font("SimSun", Font.PLAIN, 18));

		text_woman = new JRadioButton("\u5973");
		buttonGroup.add(text_woman);
		text_woman.setFont(new Font("SimSun", Font.PLAIN, 18));

		JLabel label_9 = new JLabel("\u56FE\u4E66\u5355\u4EF7\uFF1A");
		label_9.setFont(new Font("SimSun", Font.PLAIN, 18));

		text_bookType = new JComboBox();
		text_bookType.setFont(new Font("SimSun", Font.PLAIN, 18));

		JLabel label_10 = new JLabel("\u56FE\u4E66\u7C7B\u522B\uFF1A");
		label_10.setFont(new Font("SimSun", Font.PLAIN, 18));

		text_price = new JTextField();
		text_price.setFont(new Font("SimSun", Font.PLAIN, 18));
		text_price.setColumns(10);

		JLabel label_11 = new JLabel("\u56FE\u4E66\u63CF\u8FF0\uFF1A");
		label_11.setFont(new Font("SimSun", Font.PLAIN, 18));

		text_bookDesc = new JTextField();
		text_bookDesc.setFont(new Font("SimSun", Font.PLAIN, 18));
		text_bookDesc.setColumns(10);

		JButton btnNewButton = new JButton("\u4FEE\u6539");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBookActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 20));

		JButton button_1 = new JButton("\u5220\u9664");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBookActionPerformed(e);
			}
		});
		button_1.setFont(new Font("SimSun", Font.PLAIN, 20));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(40)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
								.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(label_6, Alignment.TRAILING)
										.addComponent(label_3, Alignment.TRAILING))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(text_id,
														GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup().addGap(2).addComponent(text_man)
												.addGap(18).addComponent(text_woman)))
								.addGap(61)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup().addComponent(label_4)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(text_name,
														GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup().addComponent(label_9)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(text_price)))
								.addGap(63)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(label_5)
										.addComponent(label_10))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addComponent(text_bookType, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(text_author, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)))
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(label_11)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(text_bookDesc, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)))
						.addGap(64))
						.addGroup(gl_panel_1.createSequentialGroup().addComponent(btnNewButton).addGap(146)
								.addComponent(button_1).addGap(349)))));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1
						.createSequentialGroup().addGap(
								29)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(label_3)
								.addComponent(text_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_4)
								.addComponent(text_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_5).addComponent(text_author, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(32)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(label_6)
								.addComponent(text_man).addComponent(text_woman).addComponent(label_9)
								.addComponent(label_10)
								.addComponent(text_price, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(text_bookType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(38)
						.addGroup(
								gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(text_bookDesc, GroupLayout.PREFERRED_SIZE, 90,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label_11))
						.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGap(32)));
		panel_1.setLayout(gl_panel_1);

		JLabel label = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		label.setFont(new Font("SimSun", Font.PLAIN, 18));

		s_nameTxt = new JTextField();
		s_nameTxt.setFont(new Font("SimSun", Font.PLAIN, 18));
		s_nameTxt.setColumns(10);

		JLabel label_1 = new JLabel("\u56FE\u4E66\u4F5C\u8005\uFF1A");
		label_1.setFont(new Font("SimSun", Font.PLAIN, 18));

		s_authorTxt = new JTextField();
		s_authorTxt.setFont(new Font("SimSun", Font.PLAIN, 18));
		s_authorTxt.setColumns(10);

		JLabel label_2 = new JLabel("\u56FE\u4E66\u7C7B\u522B\uFF1A");
		label_2.setFont(new Font("SimSun", Font.PLAIN, 18));

		s_bookTypeJcb = new JComboBox();
		s_bookTypeJcb.setFont(new Font("SimSun", Font.PLAIN, 18));

		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSearchActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(BookManager.class.getResource("/images/Zoom.png")));
		button.setFont(new Font("SimSun", Font.PLAIN, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(label)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(s_nameTxt, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE).addGap(34)
						.addComponent(label_1).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addGap(33).addComponent(label_2).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addGap(64).addComponent(button).addContainerGap(43, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel.createSequentialGroup().addContainerGap()
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label)
												.addComponent(s_nameTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(label_1).addComponent(label_2)
												.addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(button))
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		bookTable = new JTable();
		bookTable.setFont(new Font("SimSun", Font.PLAIN, 18));
		bookTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u56FE\u4E66\u4F5C\u8005",
						"\u4F5C\u8005\u6027\u522B", "\u56FE\u4E66\u5355\u4EF7", "\u56FE\u4E66\u7C7B\u522B",
						"\u56FE\u4E66\u63CF\u8FF0" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTable.getColumnModel().getColumn(1).setPreferredWidth(93);
		bookTable.getColumnModel().getColumn(2).setPreferredWidth(95);
		bookTable.getColumnModel().getColumn(3).setPreferredWidth(94);
		bookTable.getColumnModel().getColumn(4).setPreferredWidth(89);
		bookTable.getColumnModel().getColumn(5).setPreferredWidth(90);
		bookTable.getColumnModel().getColumn(6).setPreferredWidth(90);
		scrollPane.setViewportView(bookTable);
		getContentPane().setLayout(groupLayout);

		this.fillBookType("search");
		this.fillTable(new Book());
		this.fillBookType("Main");

	}

	// 修改图书信息
	private void updateBookActionPerformed(ActionEvent evt) {
		String id = this.text_id.getText();
		String bookName = this.text_name.getText();
		String author = this.text_author.getText();
		String price = this.text_price.getText();
		String bookDesc = this.text_bookDesc.getText();

		String sex = "";
		if (text_man.isSelected()) {
			sex = "男";
		} else {
			sex = "女";
		}
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录！");
			return;
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
		BookType bookType = (BookType) text_bookType.getSelectedItem();
		int bookTypeId = bookType.getId();
		Book book = new Book(Integer.parseInt(id), bookName, author, sex, Float.parseFloat(price), bookDesc,
				bookTypeId);
		Connection c = null;
		try {
			c = DbUtil.getConnection();
			int updateNum = bookDao.update(c, book);
			if (updateNum == 1) {
				JOptionPane.showMessageDialog(null, "修改成功！");
				// 1.刷新表格
				fillTable(new Book());
				// 2.表单置空
				resetNull();
			} else {
				JOptionPane.showMessageDialog(null, "修改失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(c, null, null);
		}

	}

	// 删除事件处理
	private void deleteBookActionPerformed(ActionEvent evt) {
		String id = text_id.getText();
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录！");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "您确定要删除的记录吗？");
		if (n == 0) {
			Connection c = null;
			c = DbUtil.getConnection();
			try {
				int deleteNum = bookDao.delete(c, id);
				if (deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "删除成功！");
					// 1.刷新表格
					fillTable(new Book());
					// 2.表单置空
					resetNull();
				} else {
					JOptionPane.showMessageDialog(null, "删除失败！");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DbUtil.close(c, null, null);
			}
		}

	}

	// 表单置空操作
	public void resetNull() {
		this.s_nameTxt.setText("");
		this.s_authorTxt.setText("");
		if (this.s_bookTypeJcb.getItemCount() > 0) {
			this.s_bookTypeJcb.setSelectedIndex(0);
		}
		this.text_id.setText("");
		this.text_name.setText("");
		this.text_author.setText("");
		this.text_price.setText("");
		this.text_bookDesc.setText("");
	}

	// 表格行单击事件
	private void bookMousePressed(MouseEvent evt) throws Exception {
		int row = bookTable.getSelectedRow();
		text_id.setText((String) bookTable.getValueAt(row, 0));
		text_name.setText((String) bookTable.getValueAt(row, 1));
		text_author.setText((String) bookTable.getValueAt(row, 2));
		String sex = (String) bookTable.getValueAt(row, 3);
		if ("男".equals(sex)) {
			this.text_man.setSelected(true);
		} else if ("女".equals(sex)) {
			this.text_woman.setSelected(true);
		}
		text_price.setText((Float) bookTable.getValueAt(row, 4) + "");

		String bookTypeName = (String) this.bookTable.getValueAt(row, 5);
		int n = this.text_bookType.getItemCount();
		for (int i = 0; i < n; i++) {
			BookType item = (BookType) this.text_bookType.getItemAt(i);
			if (item.getBookTypeName().equals(bookTypeName)) {
				this.text_bookType.setSelectedIndex(i);
			}
		}
		text_bookDesc.setText((String) bookTable.getValueAt(row, 6));
	}

	// 图书查询事件处理
	private void bookSearchActionPerformed(ActionEvent evt) {
		String bookName = this.s_nameTxt.getText();
		String author = this.s_authorTxt.getText();
		BookType bookType = (BookType) this.s_bookTypeJcb.getSelectedItem();
		int bookTypeId = bookType.getId();

		Book book = new Book(bookName, author, bookTypeId);
		this.fillTable(book);
	}

	// 初始化下拉框
	private void fillBookType(String type) {
		Connection c = null;
		BookType bookType = null;
		try {
			c = DbUtil.getConnection();
			ResultSet rs = bookTypeDao.list(c, new BookType());
			if ("search".equals(type)) {
				bookType = new BookType();
				bookType.setBookTypeName("请选择...");
				bookType.setId(-1);
				this.s_bookTypeJcb.addItem(bookType);
			}
			while (rs.next()) {
				bookType = new BookType();
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				bookType.setId(rs.getInt("id"));
				if ("search".equals(type)) {
					this.s_bookTypeJcb.addItem(bookType);
				} else if ("Main".equals(type)) {
					this.text_bookType.addItem(bookType);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(c, null, null);
		}
	}

	// 初始化表格数据
	private void fillTable(Book book) {
		DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
		dtm.setRowCount(0);// 清空表格
		Connection c = null;
		try {
			c = DbUtil.getConnection();
			ResultSet rSet = bookDao.list(c, book);
			while (rSet.next()) {
				Vector v = new Vector();
				v.add(rSet.getString("id"));
				v.add(rSet.getString("bookName"));
				v.add(rSet.getString("author"));
				v.add(rSet.getString("sex"));
				v.add(rSet.getFloat("price"));
				v.add(rSet.getString("bookTypeName"));
				v.add(rSet.getString("bookDesc"));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(c, null, null);
		}
	}
}
