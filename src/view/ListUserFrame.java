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
import servives.BookServices;
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

public class ListUserFrame extends JFrame {

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
					ListUserFrame frame = new ListUserFrame();
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
	public ListUserFrame() {
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
		
		JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("Quản lý tải khoản");
		tglbtnNewToggleButton_1.setSelected(true);
		tglbtnNewToggleButton_1.setEnabled(false);
		menuBar.add(tglbtnNewToggleButton_1);
		
		JToggleButton BorrowBookFrameBtn = new JToggleButton("Quản lý mượn sách");
		menuBar.add(BorrowBookFrameBtn);
		BorrowBookFrameBtn.addActionListener(new ActionListener() {
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
		contentPane.setBackground(new Color(128, 255, 255));
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 94, 890, 382);
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
        model.setColumnIdentifiers(new Object[] { "ID", "Username", "Phone", "Email", "Role" });

        UserService userServices = new UserService();
        List<Accounts> accounts = userServices.getAllUsers();
        
        for (Accounts account : accounts) {
            model.addRow(new Object[] { account.getUserId(), account.getUsername(), account.getPhone(), account.getEmail(), account.getRole()});
        }
        TableColumn idColumn = userListTable.getColumnModel().getColumn(0);
        idColumn.setPreferredWidth(30); // Set preferred width to 100 pixels

        // Center align "ID" column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        idColumn.setCellRenderer(centerRenderer);
		scrollPane.setViewportView(userListTable);
		
		TableColumn namCol = userListTable.getColumnModel().getColumn(4);
		namCol.setPreferredWidth(30); // Set preferred width to 100 pixels

        // Center align "ID" column
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        namCol.setCellRenderer(centerRenderer);
		scrollPane.setViewportView(userListTable);
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(128, 64, 0));
		separator.setBounds(-24, 46, 949, 2);
		contentPane.add(separator);
		
		txtTmKim = new JTextField();
		txtTmKim.setText("Nhập tên tài khoản cần tìm kiếm");
		txtTmKim.setBounds(10, 10, 797, 28);
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
		JButton searchBtn = new JButton("Tìm kiếm");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String searchText = txtTmKim.getText().trim(); // Lấy dữ liệu từ textField timkim
				 UserService userService = new UserService();
				 DefaultTableModel model = (DefaultTableModel) userListTable.getModel();
				    model.setRowCount(0); // Xóa dữ liệu hiện có trong bảng
				    List<Accounts> foundBooks = userService.searchUsersByUsername(searchText);
				    for (Accounts book : foundBooks) {
				        model.addRow(new Object[] { book.getUserId(), book.getUsername(), book.getPhone(), book.getEmail(), book.getRole()});
				    }
			}
		});
		searchBtn.setBounds(815, 10, 85, 27);
		contentPane.add(searchBtn);
		
		JButton addBookBtn = new JButton("Thêm tài khoản");
		addBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUserFrame addUserFrame = null;
				 if (addUserFrame == null) {
					 addUserFrame = new AddUserFrame();
					 addUserFrame.setVisible(true);
			        } 
			}
		});
		addBookBtn.setBounds(135, 487, 151, 48);
		contentPane.add(addBookBtn);
		
		JButton updateUserBtn = new JButton("Sửa tài khoản");
		updateUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Get the selected row index
		        int selectedRowIndex = userListTable.getSelectedRow();
		        
		        // Ensure a row is selected
		        if (selectedRowIndex >= 0) {
		            // Get the book object from the selected row
		            DefaultTableModel model = (DefaultTableModel) userListTable.getModel();
		            int selectedBookID = (int) model.getValueAt(selectedRowIndex, 0); // Assuming ID is in the first column
		            
		            // Retrieve the book object based on its ID
		            UserService userServices = new UserService();
		            Accounts selectedBook = userServices.getUserByID(selectedBookID);
		            
		            // Create and show the UpdateBookFrame with the selected book data
		            UpdateUserFrame updateUserFrame = new UpdateUserFrame();
		      
		            updateUserFrame.updateUserData(selectedBook);
		            updateUserFrame.setVisible(true);
		        } else {
		            // If no row is selected, show a message dialog
//		            JOptionPane.showMessageDialog(ListBookFrame.this, "Vui lòng chọn một cuốn sách để cập nhật.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
			}
		});
		updateUserBtn.setBounds(296, 487, 151, 48);
		contentPane.add(updateUserBtn);
		
		JButton deleteUserBtn = new JButton("Xóa tài khoản");
		deleteUserBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Lấy chỉ mục của hàng đang được chọn
		        int selectedRow = userListTable.getSelectedRow();
		        
		        // Kiểm tra xem có hàng được chọn không
		        if (selectedRow != -1) {
		            // Lấy ID của người dùng được chọn
		            int userID = (int) userListTable.getValueAt(selectedRow, 0); // Giả sử cột đầu tiên là ID
		            
		            // Gọi phương thức xóa người dùng từ service
		            UserService userService = new UserService();
		            userService.deleteUser(userID);
		            
		            // Sau khi xóa thành công, làm mới dữ liệu trong bảng người dùng
		            refreshTable();
		        }
		    }
		});

		deleteUserBtn.setBounds(455, 487, 151, 48);
		contentPane.add(deleteUserBtn);
		
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
		
		JRadioButton maSachSort = new JRadioButton("Sắp xếp theo ID");
		maSachSort.setBackground(new Color(128, 255, 255));
		maSachSort.setFont(new Font("Tahoma", Font.PLAIN, 10));
		maSachSort.setSelected(true);
		maSachSort.setBounds(28, 54, 103, 34);
		contentPane.add(maSachSort);
		
		JRadioButton bookNameSort = new JRadioButton("Sắp xếp theo Username");
		bookNameSort.setBackground(new Color(128, 255, 255));
		bookNameSort.setFont(new Font("Tahoma", Font.PLAIN, 10));
		bookNameSort.setBounds(184, 55, 156, 34);
		contentPane.add(bookNameSort);
		
		JRadioButton authorSort = new JRadioButton("Sắp xếp theo SDT");
		authorSort.setBackground(new Color(128, 255, 255));
		authorSort.setFont(new Font("Tahoma", Font.PLAIN, 10));
		authorSort.setBounds(395, 56, 115, 34);
		contentPane.add(authorSort);
		
		JRadioButton categorySort = new JRadioButton("Sắp xếp theo Email");
		categorySort.setFont(new Font("Tahoma", Font.PLAIN, 10));
		categorySort.setBackground(new Color(128, 255, 255));
		categorySort.setBounds(575, 55, 131, 34);
		contentPane.add(categorySort);
		
		JRadioButton namXuatBanSort = new JRadioButton("Sắp xếp theo ROLE");
		namXuatBanSort.setFont(new Font("Tahoma", Font.PLAIN, 10));
		namXuatBanSort.setBackground(new Color(128, 255, 255));
		namXuatBanSort.setBounds(771, 56, 115, 34);
		contentPane.add(namXuatBanSort);
		ButtonGroup btnGroupSort = new ButtonGroup();
		btnGroupSort.add(maSachSort);
		btnGroupSort.add(bookNameSort);
		btnGroupSort.add(authorSort);
		btnGroupSort.add(namXuatBanSort);
		btnGroupSort.add(categorySort);
		
		maSachSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortTable(0); // Sort by ID
            }
        });

        bookNameSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortTable(1); // Sort by Book Name
            }
        });

        authorSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortTable(2); // Sort by Author
            }
        });

        categorySort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortTable(3); // Sort by Category
            }
        });

        namXuatBanSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortTable(4); // Sort by Year
            }
        });

	}
	
	
	public void refreshTable() {
	    DefaultTableModel model = (DefaultTableModel) userListTable.getModel();
	    model.setRowCount(0); // Xóa tất cả các dòng hiện có trong bảng

	    // Thêm lại dữ liệu từ danh sách sách mới nhất
	    UserService userService = new UserService();

		    List<Accounts> foundBooks = userService.getAllUsers();
		    for (Accounts book : foundBooks) {
		        model.addRow(new Object[] { book.getUserId(), book.getUsername(), book.getPhone(), book.getEmail(), book.getRole()});
		    }
	}





	
	private void sortTable(int columnIndex) {
        DefaultTableModel model = (DefaultTableModel) userListTable.getModel();
        UserService userService = new UserService();// Assuming a static method to get all books
        List<Accounts> accounts = userService.getAllUsers();
        accounts.sort((Comparator<? super Accounts>) new Comparator<Accounts>() {
            @Override
            public int compare(Accounts acc1, Accounts acc2) {
                switch (columnIndex) {
                    case 0: // Sort by ID
                        return Integer.compare(acc1.getUserId(), acc2.getUserId());
                    case 1: // Sort by Book Name
                        return acc1.getUsername().compareTo(acc2.getUsername());
                    case 2: // Sort by Author
                        return acc1.getPhone().compareTo(acc2.getPhone());
                    case 3: // Sort by Category
                        return acc1.getEmail().compareTo(acc2.getEmail());
                    case 4: // Sort by Publisher
                        return acc1.getRole().compareTo(acc2.getRole());
                    default:
                        return 0;
                }
            }
        });

        // Clear current table data
        model.setRowCount(0);
	    for (Accounts book : accounts) {
	        model.addRow(new Object[] { book.getUserId(), book.getUsername(), book.getPhone(), book.getEmail(), book.getRole()});
	    }
    }
}
