package com.java.view;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.java.bean.BookType;
import com.java.dao.BookDao;
import com.java.dao.BookTypeDao;
import com.java.util.DbUtil;
import com.java.util.StringUtil;
import com.mysql.jdbc.Connection;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookTypeManager extends JInternalFrame {
	private JTable bookTypeTable;

	private BookDao bookDao = new BookDao();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private JTextField s_bookTypeNameTxt;
	private JTextField text_id;
	private JTextField text_name;
	private JTextField text_desc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeManager frame = new BookTypeManager();
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
	public BookTypeManager() {
		setIconifiable(true);
		setClosable(true);
		setTitle("\u56FE\u4E66\u7C7B\u522B\u7BA1\u7406");
		setBounds(100, 100, 509, 690);

		JScrollPane scrollPane = new JScrollPane();

		JLabel label = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0\uFF1A");
		label.setFont(new Font("SimSun", Font.PLAIN, 18));

		s_bookTypeNameTxt = new JTextField();
		s_bookTypeNameTxt.setFont(new Font("SimSun", Font.PLAIN, 18));
		s_bookTypeNameTxt.setColumns(10);

		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeSearchActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(BookTypeManager.class.getResource("/images/Zoom.png")));
		button.setFont(new Font("SimSun", Font.PLAIN, 18));

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(BookTypeManager.class.getResource("/images/booktypemodify.jpg")));

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(label_1).addGroup(groupLayout
						.createSequentialGroup().addGap(19)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addComponent(label)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 186,
												GroupLayout.PREFERRED_SIZE)
										.addGap(41).addComponent(button))
								.addGroup(Alignment.LEADING,
										groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(scrollPane, Alignment.LEADING)))))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addComponent(label_1).addGap(39)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
						.addGap(29).addComponent(panel, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(34, Short.MAX_VALUE)));

		JLabel label_2 = new JLabel("\u7F16\u53F7\uFF1A");
		label_2.setFont(new Font("SimSun", Font.PLAIN, 18));

		text_id = new JTextField();
		text_id.setFont(new Font("SimSun", Font.PLAIN, 18));
		text_id.setColumns(10);

		JLabel label_3 = new JLabel("\u540D\u79F0\uFF1A");
		label_3.setFont(new Font("SimSun", Font.PLAIN, 18));

		text_name = new JTextField();
		text_name.setFont(new Font("SimSun", Font.PLAIN, 18));
		text_name.setColumns(10);

		JLabel label_4 = new JLabel("\u63CF\u8FF0\uFF1A");
		label_4.setFont(new Font("SimSun", Font.PLAIN, 18));

		text_desc = new JTextField();
		text_desc.setFont(new Font("SimSun", Font.PLAIN, 18));
		text_desc.setColumns(10);

		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBookTypeActionPerformed(e);
			}
		});
		button_1.setFont(new Font("SimSun", Font.PLAIN, 20));

		JButton button_2 = new JButton("\u5220\u9664");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBookTypeActionPerformed(e);
			}
		});
		button_2.setFont(new Font("SimSun", Font.PLAIN, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false).addGroup(gl_panel
								.createSequentialGroup().addComponent(label_2)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(text_id, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addGap(41).addComponent(label_3).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(text_name, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup().addComponent(label_4)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(text_desc))))
						.addGroup(gl_panel.createSequentialGroup().addGap(109).addComponent(button_1).addGap(54)
								.addComponent(button_2)))
				.addContainerGap(82, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(22)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_2)
								.addComponent(text_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3).addComponent(text_name, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(29)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_4).addComponent(
								text_desc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE).addGroup(gl_panel
								.createParallelGroup(Alignment.BASELINE).addComponent(button_1).addComponent(button_2))
						.addGap(35)));
		panel.setLayout(gl_panel);

		bookTypeTable = new JTable();
		bookTypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BookTypeMousePressed(e);
			}
		});
		bookTypeTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\u7F16\u53F7",
				"\u56FE\u4E66\u7C7B\u522B\u540D\u79F0", "\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0" }) {
			boolean[] columnEditables = new boolean[] { true, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTypeTable.getColumnModel().getColumn(0).setPreferredWidth(124);
		bookTypeTable.getColumnModel().getColumn(1).setPreferredWidth(118);
		bookTypeTable.getColumnModel().getColumn(2).setPreferredWidth(135);
		scrollPane.setViewportView(bookTypeTable);
		getContentPane().setLayout(groupLayout);

		this.fillTable(new BookType());
	}

	// 修改图书类别
	private void updateBookTypeActionPerformed(ActionEvent evt) {
		String id = text_id.getText();// 获取图书id
		String bookTypeName = text_name.getText();
		String bookTypeDesc = text_desc.getText();
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录！");
			return;
		}
		if (StringUtil.isEmpty(bookTypeName)) {
			JOptionPane.showMessageDialog(null, "图书类别名称不能为空！");
			return;
		}
		if (StringUtil.isEmpty(bookTypeDesc)) {
			JOptionPane.showMessageDialog(null, "图书类别描述不能为空！");
			return;
		}
		BookType bookType = new BookType(Integer.parseInt(id), bookTypeName, bookTypeDesc);
		Connection c = null;
		try {
			c = DbUtil.getConnection();
			int updateNum = bookTypeDao.update(c, bookType);
			if (updateNum == 1) {
				JOptionPane.showMessageDialog(null, "修改成功！");
				// 1.刷新表格
				fillTable(new BookType());
				// 2.表单置空
				resetValue();
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

	// 删除图书类别事件
	private void deleteBookTypeActionPerformed(ActionEvent evt) {
		String id = text_id.getText();
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录！");// 提示信息
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "您确定要删除的记录吗？");// 提示信息
		if (n == 0) {
			Connection c = null;
			c = DbUtil.getConnection();
			try {
				boolean flag = bookDao.existBookByBookType(c, id);
				if (flag) {
					JOptionPane.showMessageDialog(null, "当前图书类别有图书，请不要删除！");
					return;
				}
				int deleteNum = bookTypeDao.delete(c, id);
				if (deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "删除成功！");
					// 1.刷新表格
					fillTable(new BookType());
					// 2.表单置空
					resetValue();
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

	// 表格行单击事件
	private void BookTypeMousePressed(MouseEvent evt) {
		int row = bookTypeTable.getSelectedRow();
		text_id.setText((String) bookTypeTable.getValueAt(row, 0));
		text_name.setText((String) bookTypeTable.getValueAt(row, 1));
		text_desc.setText((String) bookTypeTable.getValueAt(row, 2));
	}

	// 图书类别搜索事件处理
	private void bookTypeSearchActionPerformed(ActionEvent evt) {
		String s_bookTypeName = this.s_bookTypeNameTxt.getText();
		BookType bookType = new BookType();
		bookType.setBookTypeName(s_bookTypeName);
		//BookType bookType = (BookType) this.s_bookTypeNameTxt.getSelectedItem();
		this.fillTable(bookType);
	}

	// 初始化表格数据
	private void fillTable(BookType bookType) {
		DefaultTableModel dtm = (DefaultTableModel) bookTypeTable.getModel();
		dtm.setRowCount(0);// 清空表格
		Connection c = null;
		try {
			c = DbUtil.getConnection();
			ResultSet rSet = bookTypeDao.list(c, bookType);
			while (rSet.next()) {
				Vector v = new Vector();
				v.add(rSet.getString("id"));
				v.add(rSet.getString("bookTypeName"));
				v.add(rSet.getString("bookTypeDesc"));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(c, null, null);
		}
	}

	// 表单置空操作
	public void resetValue() {
		this.text_id.setText("");
		this.text_name.setText("");
		this.text_desc.setText("");
	}
}
