package view;

import java.awt.EventQueue;
import java.util.Comparator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import javax.swing.JMenu;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import models.Accounts;
import models.Book;
import models.Borrow;
import server.BookController;
import servives.BookServices;
import servives.BorrowService;
import servives.UserService;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;

public class ThongKeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKeFrame frame = new ThongKeFrame();
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
	public ThongKeFrame() {
		setTitle("QUẢN LÝ THƯ VIỆN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 924, 652);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setJMenuBar(menuBar);
		
		JToggleButton ListBookFrameBtn = new JToggleButton("Quản lý sách");
		menuBar.add(ListBookFrameBtn);
		ListBookFrameBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Ẩn ListBookFrame
		        setVisible(false);

		        // Hiện ListUserFrame
		        ListBookFrame listUserFrame = new ListBookFrame();
		        listUserFrame.setVisible(true);
		    }
		});
		
		JToggleButton ListUserFrameBtn = new JToggleButton("Quản lý tải khoản");
		menuBar.add(ListUserFrameBtn);
		ListUserFrameBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Ẩn ListBookFrame
		        setVisible(false);

		        // Hiện ListUserFrame
		        ListUserFrame listUserFrame = new ListUserFrame();
		        listUserFrame.setVisible(true);
		    }
		});
		JToggleButton BorrowBtn = new JToggleButton("Quản lý mượn sách");
		menuBar.add(BorrowBtn);
		BorrowBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Ẩn ListBookFrame
		        setVisible(false);

		        // Hiện ListUserFrame
		        BorrowBookFrame borrowBookFrame = new BorrowBookFrame();
		        borrowBookFrame.setVisible(true);
		    }
		});
		JToggleButton tglbtnNewToggleButton_1_2_1 = new JToggleButton("Thống kê");
		tglbtnNewToggleButton_1_2_1.setSelected(true);
		tglbtnNewToggleButton_1_2_1.setEnabled(false);
		menuBar.add(tglbtnNewToggleButton_1_2_1);
		
		JToggleButton InfoBtn = new JToggleButton("Thông tin tác giả");
		menuBar.add(InfoBtn);
		InfoBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Ẩn ListBookFrame
		        setVisible(false);

		        // Hiện ListUserFrame
		        InfoFrame tnfoFrame = new InfoFrame();
		        tnfoFrame.setVisible(true);
		    }
		});
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
       

		
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		JLabel lblNewLabel_1 = new JLabel("Được làm bởi nhóm 04 - CT46702");
		lblNewLabel_1.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(283, 545, 343, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("THỐNG KÊ THƯ VIỆN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(136, 10, 318, 127);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Số lượng sách có trong thư viện:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(136, 139, 303, 50);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Số lượng độc giả có trong thư viện");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(136, 221, 318, 50);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Số lượng quản trị viên của thư viện:");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(136, 298, 343, 50);
		contentPane.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Số lượng sách chưa trả:");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_1_1.setBounds(136, 370, 253, 50);
		contentPane.add(lblNewLabel_2_1_1_1);
		
		JLabel numberOfBook = new JLabel("20");
		numberOfBook.setFont(new Font("Montserrat", Font.BOLD, 20));
		numberOfBook.setBounds(449, 140, 115, 49);
		contentPane.add(numberOfBook);
		
		JLabel numberOfUser = new JLabel("20");
		numberOfUser.setFont(new Font("Montserrat", Font.BOLD, 20));
		numberOfUser.setBounds(457, 222, 115, 49);
		contentPane.add(numberOfUser);
		
		JLabel numberOfAdmin = new JLabel("20");
		numberOfAdmin.setFont(new Font("Montserrat", Font.BOLD, 20));
		numberOfAdmin.setBounds(489, 298, 115, 49);
		contentPane.add(numberOfAdmin);
		
		JLabel numberOfBorrow = new JLabel("20");
		numberOfBorrow.setFont(new Font("Montserrat", Font.BOLD, 20));
		numberOfBorrow.setBounds(399, 371, 115, 49);
		contentPane.add(numberOfBorrow);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(136, 116, 490, 2);
		contentPane.add(separator);
		ButtonGroup btnGroupSort = new ButtonGroup();
		
		BookServices bookService = new BookServices();
		int numberOfBooks = bookService.countTotalBooks();
		numberOfBook.setText(String.valueOf(numberOfBooks));

		// Lấy số lượng độc giả
		UserService userService = new UserService();
		int numberOfUsersValue = userService.CountUser();
		numberOfUser.setText(String.valueOf(numberOfUsersValue));

		// Lấy số lượng quản trị viên // Giả sử có một phương thức riêng để lấy quản trị viên
		int numberOfAdminValue = userService.CountAdmin();
		numberOfAdmin.setText(String.valueOf(numberOfAdminValue));

		// Lấy số lượng sách chưa trả
		BorrowService borrowService = new BorrowService();
		int numberOfBorrowsValue = borrowService.soSachChuaTra();
		numberOfBorrow.setText(String.valueOf(numberOfBorrowsValue));
		
		JLabel numberOfBorrow_1 = new JLabel("10");
		numberOfBorrow_1.setFont(new Font("Montserrat", Font.BOLD, 20));
		numberOfBorrow_1.setBounds(399, 447, 115, 49);
		contentPane.add(numberOfBorrow_1);

		int numberOfBorrowsValue1 = borrowService.soSachDaTra();
		numberOfBorrow_1.setText(String.valueOf(numberOfBorrowsValue1));
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Số lượng sách đã trả:");
		lblNewLabel_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_1_1_1.setBounds(136, 446, 253, 50);
		contentPane.add(lblNewLabel_2_1_1_1_1);


	}
}
