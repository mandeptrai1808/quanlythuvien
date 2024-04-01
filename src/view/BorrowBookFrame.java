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

public class BorrowBookFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable userListTable;
	private JTextField txtTmKim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrowBookFrame frame = new BorrowBookFrame();
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
	public BorrowBookFrame() {
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
		JToggleButton tglbtnNewToggleButton_1_2 = new JToggleButton("Quản lý mượn sách");
		tglbtnNewToggleButton_1_2.setSelected(true);
		tglbtnNewToggleButton_1_2.setEnabled(false);
		menuBar.add(tglbtnNewToggleButton_1_2);
		
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
		contentPane.setBackground(new Color(255, 208, 208));
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 890, 418);
		contentPane.add(scrollPane);
		
		userListTable = new JTable();
		userListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		userListTable.setRowHeight(30);
		DefaultTableModel model = (DefaultTableModel) userListTable.getModel();
        model.setColumnIdentifiers(new Object[] { "ID", "Người mượn", "Tên sách", "Nội dung", "Ngày mượn", "Đã trả", "Nhân viên"});

        BorrowService borrowService = new BorrowService();
        List<Borrow> borrows = borrowService.getAllBorrows();
        for (Borrow borrow : borrows) {
            model.addRow(new Object[] { borrow.getId(), borrow.getBorrowerName(), borrow.getBookTitle(), borrow.getContent(),
                    borrow.getBorrowDate(), borrow.isReturned(), borrow.getManager() });
        }
        TableColumn idColumn = userListTable.getColumnModel().getColumn(0);
        idColumn.setPreferredWidth(30); // Set preferred width to 100 pixels

        // Center align "ID" column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        idColumn.setCellRenderer(centerRenderer);
		scrollPane.setViewportView(userListTable);
		
		TableColumn namCol = userListTable.getColumnModel().getColumn(5);
		namCol.setPreferredWidth(30); // Set preferred width to 100 pixels

        // Center align "ID" column
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        namCol.setCellRenderer(centerRenderer);
		scrollPane.setViewportView(userListTable);
		
		TableColumn dateCol = userListTable.getColumnModel().getColumn(4);
		dateCol.setPreferredWidth(30); // Set preferred width to 100 pixels

        // Center align "ID" column
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        dateCol.setCellRenderer(centerRenderer);
		scrollPane.setViewportView(userListTable);
		
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(128, 64, 0));
		separator.setBounds(-24, 46, 949, 2);
		contentPane.add(separator);
		
		txtTmKim = new JTextField();
		txtTmKim.setText("Nhập tên tài khoản cần tìm kiếm");
		txtTmKim.setBounds(356, 10, 451, 28);
		contentPane.add(txtTmKim);
		txtTmKim.setColumns(10);
		txtTmKim.setForeground(Color.GRAY); // Màu văn bản mặc định là xám
        txtTmKim.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Xóa placeholder khi textField được chọn
                if (txtTmKim.getText().equals("Nhập tên tài khoản cần tìm kiếm")) {
                    txtTmKim.setText("");
                    txtTmKim.setForeground(Color.BLACK); // Đổi màu văn bản thành đen
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Hiển thị lại placeholder nếu không có văn bản và không được chọn
                if (txtTmKim.getText().isEmpty()) {
                    txtTmKim.setForeground(Color.GRAY);
                    txtTmKim.setText("Nhập nội dung tìm kiếm");
                }
            }
        });
        JRadioButton userNameRadio = new JRadioButton("Tên người mượn");
		userNameRadio.setSelected(true);
		userNameRadio.setFont(new Font("Tahoma", Font.PLAIN, 10));
		userNameRadio.setBackground(new Color(255, 208, 208));
		userNameRadio.setBounds(128, 6, 103, 34);
		contentPane.add(userNameRadio);
		
		JRadioButton bookNameRadio = new JRadioButton("Tên sách");
		bookNameRadio.setFont(new Font("Tahoma", Font.PLAIN, 10));
		bookNameRadio.setBackground(new Color(255, 208, 208));
		bookNameRadio.setBounds(233, 6, 103, 34);
		contentPane.add(bookNameRadio);
		
		btnGroup.add(userNameRadio);
		btnGroup.add(bookNameRadio);
		JButton searchBtn = new JButton("Tìm kiếm");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String searchText = txtTmKim.getText().trim(); // Lấy dữ liệu từ textField txtTmKim
		        BorrowService borrowService = new BorrowService();
		        DefaultTableModel model = (DefaultTableModel) userListTable.getModel();
		        model.setRowCount(0); // Xóa dữ liệu hiện có trong bảng
		        
		        // Kiểm tra xem người dùng đã chọn tìm kiếm theo tên người mượn hay tên sách
		        if (userNameRadio.isSelected()) {
		            // Tìm kiếm theo tên người mượn
		            List<Borrow> foundBorrows = borrowService.searchBorrowsByBorrowerName(searchText);
		            for (Borrow borrow : foundBorrows) {
		            	 System.out.println(borrow.toString());
		                model.addRow(new Object[] { borrow.getId(), borrow.getBorrowerName(), borrow.getBookTitle(), borrow.getContent(),
		                    borrow.getBorrowDate(), borrow.isReturned(), borrow.getManager() });
		            }
		        } else if (bookNameRadio.isSelected()) {
		            // Tìm kiếm theo tên sách
		            List<Borrow> foundBorrows = borrowService.searchBorrowsByBookTitle(searchText);
		            for (Borrow borrow : foundBorrows) {
		                model.addRow(new Object[] { borrow.getId(), borrow.getBorrowerName(), borrow.getBookTitle(), borrow.getContent(),
		                    borrow.getBorrowDate(), borrow.isReturned(), borrow.getManager() });
		            }
		        }
		    }
		});
		searchBtn.setBounds(815, 10, 85, 27);
		contentPane.add(searchBtn);
		
		JButton addBookBtn = new JButton("Đăng kí mươn sách");
		addBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBorrowFrame addBorrowFrame = null;
				 if (addBorrowFrame == null) {
					 addBorrowFrame = new AddBorrowFrame();
					 addBorrowFrame.setVisible(true);
			        } 
			}
		});
		addBookBtn.setBounds(135, 487, 151, 48);
		contentPane.add(addBookBtn);
		
		JButton returnedBtn = new JButton("Đánh dấu trả sách");
		returnedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Lấy chỉ mục của hàng được chọn
		        int selectedRowIndex = userListTable.getSelectedRow();
		        
		        // Đảm bảo một hàng được chọn
		        if (selectedRowIndex >= 0) {
		            // Lấy ID của mượn sách được chọn từ cột đầu tiên (giả sử ID là cột đầu tiên)
		            int selectedBorrowID = (int) userListTable.getValueAt(selectedRowIndex, 0);
		            
		            // Gọi phương thức returnBook từ BorrowService với ID được chọn
		            BorrowService borrowService = new BorrowService();
		            borrowService.returnBook(selectedBorrowID);
		            
		            // Cập nhật lại bảng để hiển thị các thay đổi
		            refreshTable();
		        } else {
		            // Nếu không có hàng nào được chọn, hiển thị một hộp thoại thông báo
		            JOptionPane.showMessageDialog(BorrowBookFrame.this, "Vui lòng chọn một mục để đánh dấu đã trả sách.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
			}
		});
		returnedBtn.setBounds(296, 486, 151, 48);
		contentPane.add(returnedBtn);
		
		JButton deleteBorrowBtn = new JButton("Xóa");
		deleteBorrowBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	 // Lấy chỉ mục của hàng được chọn
		        int selectedRow = userListTable.getSelectedRow();
		        
		        // Kiểm tra xem có hàng được chọn không
		        if (selectedRow != -1) {
		            // Lấy ID của mượn sách được chọn từ cột đầu tiên (giả sử ID là cột đầu tiên)
		            int selectedBorrowID = (int) userListTable.getValueAt(selectedRow, 0);
		            
		            // Gọi phương thức deleteBorrow từ BorrowService với ID được chọn
		            BorrowService borrowService = new BorrowService();
		            borrowService.deleteBorrow(selectedBorrowID);
		            
		            // Sau khi xóa thành công, làm mới dữ liệu trong bảng mượn sách
		            refreshTable();
		        } else {
		            // Nếu không có hàng nào được chọn, hiển thị một hộp thoại thông báo
		            JOptionPane.showMessageDialog(BorrowBookFrame.this, "Vui lòng chọn một mượn sách để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});

		deleteBorrowBtn.setBounds(455, 487, 151, 48);
		contentPane.add(deleteBorrowBtn);
		
		JButton refreshBook = new JButton("Refresh");
		refreshBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		refreshBook.setBounds(616, 487, 151, 48);
		contentPane.add(refreshBook);
		
		JLabel lblNewLabel_1 = new JLabel("Được làm bởi nhóm 04 - CT46702");
		lblNewLabel_1.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(283, 545, 343, 28);
		contentPane.add(lblNewLabel_1);
		ButtonGroup btnGroupSort = new ButtonGroup();
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm theo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 17, 142, 13);
		contentPane.add(lblNewLabel);
		
		


	}
	
	
	public void refreshTable() {
	    DefaultTableModel model = (DefaultTableModel) userListTable.getModel();
	    model.setRowCount(0); // Xóa tất cả các dòng hiện có trong bảng

	    // Thêm lại dữ liệu từ danh sách sách mới nhất
	    BorrowService borrowService = new BorrowService();

	    List<Borrow> borrows = borrowService.getAllBorrows();
        for (Borrow borrow : borrows) {
            model.addRow(new Object[] { borrow.getId(), borrow.getBorrowerName(), borrow.getBookTitle(), borrow.getContent(),
                    borrow.getBorrowDate(), borrow.isReturned(), borrow.getManager() });
        }
	}
}
