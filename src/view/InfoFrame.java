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

public class InfoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoFrame frame = new InfoFrame();
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
	public InfoFrame() {
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
		
		JToggleButton ThongKeBtn = new JToggleButton("Thống kê");
		menuBar.add(ThongKeBtn);
		ThongKeBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Ẩn ListBookFrame
		        setVisible(false);

		        // Hiện ListUserFrame
		        ThongKeFrame thongKeFrame = new ThongKeFrame();
		        thongKeFrame.setVisible(true);
		    }
		});
		JToggleButton tglbtnNewToggleButton_1_2_1_1 = new JToggleButton("Thông tin tác giả");
		tglbtnNewToggleButton_1_2_1_1.setSelected(true);
		tglbtnNewToggleButton_1_2_1_1.setEnabled(false);
		menuBar.add(tglbtnNewToggleButton_1_2_1_1);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 255));
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
       

		
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		JLabel lblNewLabel_1 = new JLabel("Được làm bởi nhóm 04 - CT46702");
		lblNewLabel_1.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(283, 545, 343, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN TÁC GIẢ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(296, 10, 318, 127);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Nguyễn Văn Mặn - B2105551 - manb2105551@student.ctu.edu.vn");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(155, 139, 599, 50);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Lý Quốc Hùng - B2105544 - hungb2105544@student.ctu.edu.vn");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(155, 221, 599, 50);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Nguyễn Dương Kim Anh - B2113326 - anhb2113326@student.ctu.edu.vn");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(155, 298, 599, 50);
		contentPane.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Ong Tố Hiền - B2105542 - hienb2105542@student.ctu.edu.vn");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_1_1.setBounds(168, 370, 573, 50);
		contentPane.add(lblNewLabel_2_1_1_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(136, 116, 641, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(136, 456, 641, 2);
		contentPane.add(separator_1);
		ButtonGroup btnGroupSort = new ButtonGroup();
		
		BookServices bookService = new BookServices();
		List<Book> books = bookService.getAllBook();
		int numberOfBooks = books.size();

		// Lấy số lượng độc giả
		UserService userService = new UserService();
		List<Accounts> users = userService.getAllUsers();
		int numberOfUsersValue = users.size();
		List<Accounts> admin = userService.getAdminUsers();
		int numberOfAdminValue= admin.size();

		// Lấy số lượng quản trị viên
		List<Accounts> admins = userService.getAdminUsers(); // Giả sử có một phương thức riêng để lấy quản trị viên
		numberOfAdminValue = admins.size();

		// Lấy số lượng sách được mượn
		BorrowService borrowService = new BorrowService();
		List<Borrow> borrows = borrowService.getAllBorrows();
		int numberOfBorrowsValue = borrows.size();


	}
}
