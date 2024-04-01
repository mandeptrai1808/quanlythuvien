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

import models.Book;
import servives.BookServices;
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

public class ListBookFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable bookListtable;
	private JTextField txtTmKim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListBookFrame frame = new ListBookFrame();
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
	public ListBookFrame() {
		setTitle("QUẢN LÝ THƯ VIỆN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 924, 652);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setJMenuBar(menuBar);
		
		JToggleButton tglbtnNewToggleButton_1_1 = new JToggleButton("Quản lý sách");
		tglbtnNewToggleButton_1_1.setEnabled(false);
		tglbtnNewToggleButton_1_1.setSelected(true);
		menuBar.add(tglbtnNewToggleButton_1_1);
		
		JToggleButton ListUserFrameBtn = new JToggleButton("Quản lý tài khoản");
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
		contentPane.setBackground(new Color(255, 255, 217));
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 105, 890, 371);
		contentPane.add(scrollPane);
		
		bookListtable = new JTable();
		bookListtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bookListtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		bookListtable.setRowHeight(30);
		DefaultTableModel model = (DefaultTableModel) bookListtable.getModel();
        model.setColumnIdentifiers(new Object[] { "ID", "Tên sách", "Tên tác giả", "Thể loại", "Nhà xuất bản", "Năm xuất bản" });

        BookServices bookServices = new BookServices();
        List<Book> books = bookServices.getAllBook();
        
        for (Book book : books) {
            model.addRow(new Object[] { book.getmaSach(), book.getTenSach(), book.getTenTacGia(), book.getTenTheLoai(), book.getTenNXB(), book.getNamXuatBan() });
        }
        TableColumn idColumn = bookListtable.getColumnModel().getColumn(0);
        idColumn.setPreferredWidth(30); // Set preferred width to 100 pixels

        // Center align "ID" column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        idColumn.setCellRenderer(centerRenderer);
		scrollPane.setViewportView(bookListtable);
		
		TableColumn namCol = bookListtable.getColumnModel().getColumn(5);
		namCol.setPreferredWidth(30); // Set preferred width to 100 pixels

        // Center align "ID" column
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        namCol.setCellRenderer(centerRenderer);
		scrollPane.setViewportView(bookListtable);
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm theo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 0, 151, 48);
		contentPane.add(lblNewLabel);
		
		JRadioButton bookNameRadio = new JRadioButton("Tên sách");
		bookNameRadio.setSelected(true);
		bookNameRadio.setBounds(120, 6, 103, 34);
		contentPane.add(bookNameRadio);
		
		JRadioButton authorRadio = new JRadioButton("Tên tác giả");
		authorRadio.setBounds(225, 6, 103, 34);
		contentPane.add(authorRadio);
		
		JRadioButton categoryRadio = new JRadioButton("Thể loại");
		categoryRadio.setBounds(330, 6, 78, 34);
		contentPane.add(categoryRadio);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(bookNameRadio);
		btnGroup.add(authorRadio);
		btnGroup.add(categoryRadio);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(128, 64, 0));
		separator.setBounds(-24, 46, 949, 2);
		contentPane.add(separator);
		
		txtTmKim = new JTextField();
		txtTmKim.setText("Nhập nội dung tìm kiếm");
		txtTmKim.setBounds(414, 10, 393, 28);
		contentPane.add(txtTmKim);
		txtTmKim.setColumns(10);
		txtTmKim.setForeground(Color.GRAY); // Màu văn bản mặc định là xám
        txtTmKim.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Xóa placeholder khi textField được chọn
                if (txtTmKim.getText().equals("Nhập nội dung tìm kiếm")) {
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

			        // Kiểm tra radio button nào đang được chọn
			        if (bookNameRadio.isSelected()) {
			            searchBooksByTitle(searchText);
			        } else if (authorRadio.isSelected()) {
			            searchBooksByAuthor(searchText);
			        } else if (categoryRadio.isSelected()) {
			            searchBooksByCategory(searchText);
			        } else {
			            // Nếu không có radio button nào được chọn, thông báo cho người dùng
			            JOptionPane.showMessageDialog(ListBookFrame.this, "Vui lòng chọn một loại tìm kiếm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
			        }
			}
		});
		searchBtn.setBounds(815, 10, 85, 27);
		contentPane.add(searchBtn);
		
		JButton addBookBtn = new JButton("Thêm sách");
		addBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 AddBookFrame addBookFrame = null;
				 if (addBookFrame == null) {
			            addBookFrame = new AddBookFrame();
			            addBookFrame.setVisible(true);
			        } 
			}
		});
		addBookBtn.setBounds(135, 487, 151, 48);
		contentPane.add(addBookBtn);
		
		JButton updateBookBtn = new JButton("Sửa sách");
		updateBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Get the selected row index
		        int selectedRowIndex = bookListtable.getSelectedRow();
		        
		        // Ensure a row is selected
		        if (selectedRowIndex >= 0) {
		            // Get the book object from the selected row
		            DefaultTableModel model = (DefaultTableModel) bookListtable.getModel();
		            int selectedBookID = (int) model.getValueAt(selectedRowIndex, 0); // Assuming ID is in the first column
		            
		            // Retrieve the book object based on its ID
		            BookServices bookServices = new BookServices();
		            Book selectedBook = bookServices.getBookByID(selectedBookID);
		            
		            // Create and show the UpdateBookFrame with the selected book data
		            UpdateBookFrame updateBookFrame = new UpdateBookFrame();
		      
		            updateBookFrame.updateBookData(selectedBook);
		            updateBookFrame.setVisible(true);
		        } else {
		            // If no row is selected, show a message dialog
		            JOptionPane.showMessageDialog(ListBookFrame.this, "Vui lòng chọn một cuốn sách để cập nhật.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
			}
		});
		updateBookBtn.setBounds(296, 487, 151, 48);
		contentPane.add(updateBookBtn);
		
		JButton deleteBookBtn = new JButton("Xóa sách");
		deleteBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Lấy chỉ mục của hàng đang được chọn
		        int selectedRow = bookListtable.getSelectedRow();
		        
		        // Kiểm tra xem có hàng được chọn không
		        if (selectedRow != -1) {
		            // Lấy mã sách của cuốn sách được chọn
		            int maSach = (int) bookListtable.getValueAt(selectedRow, 0); // Giả sử cột đầu tiên là mã sách
		            
		            // Gọi phương thức xóa sách từ controller hoặc service
		            bookServices.deleteBook(maSach);
		            
		            // Sau khi xóa thành công, làm mới dữ liệu trong bảng sách
		            refreshTable();
		        }
			}
		});
		deleteBookBtn.setBounds(455, 487, 151, 48);
		contentPane.add(deleteBookBtn);
		
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
		
		JRadioButton maSachSort = new JRadioButton("Sắp xếp mã sách");
		maSachSort.setBackground(new Color(255, 255, 217));
		maSachSort.setFont(new Font("Tahoma", Font.PLAIN, 10));
		maSachSort.setSelected(true);
		maSachSort.setBounds(20, 54, 103, 34);
		contentPane.add(maSachSort);
		
		JRadioButton bookNameSort = new JRadioButton("Sắp xếp tên sách");
		bookNameSort.setBackground(new Color(255, 255, 217));
		bookNameSort.setFont(new Font("Tahoma", Font.PLAIN, 10));
		bookNameSort.setBounds(152, 55, 115, 34);
		contentPane.add(bookNameSort);
		
		JRadioButton authorSort = new JRadioButton("Sắp xếp tên tác giả");
		authorSort.setBackground(new Color(255, 255, 217));
		authorSort.setFont(new Font("Tahoma", Font.PLAIN, 10));
		authorSort.setBounds(310, 55, 115, 34);
		contentPane.add(authorSort);
		
		JRadioButton categorySort = new JRadioButton("Sắp xếp tên thể loại");
		categorySort.setFont(new Font("Tahoma", Font.PLAIN, 10));
		categorySort.setBackground(new Color(255, 255, 217));
		categorySort.setBounds(468, 55, 131, 34);
		contentPane.add(categorySort);
		
		JRadioButton nhaXuatBanSort = new JRadioButton("Sắp xếp nhà xuất bản");
		nhaXuatBanSort.setFont(new Font("Tahoma", Font.PLAIN, 10));
		nhaXuatBanSort.setBackground(new Color(255, 255, 217));
		nhaXuatBanSort.setBounds(628, 55, 131, 34);
		contentPane.add(nhaXuatBanSort);
		
		JRadioButton namXuatBanSort = new JRadioButton("Sắp xếp theo năm");
		namXuatBanSort.setFont(new Font("Tahoma", Font.PLAIN, 10));
		namXuatBanSort.setBackground(new Color(255, 255, 217));
		namXuatBanSort.setBounds(785, 55, 115, 34);
		contentPane.add(namXuatBanSort);
		ButtonGroup btnGroupSort = new ButtonGroup();
		btnGroupSort.add(maSachSort);
		btnGroupSort.add(bookNameSort);
		btnGroupSort.add(authorSort);
		btnGroupSort.add(nhaXuatBanSort);
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

        nhaXuatBanSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortTable(4); // Sort by Publisher
            }
        });

        namXuatBanSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortTable(5); // Sort by Year
            }
        });

	}
	
	
	public void refreshTable() {
	    DefaultTableModel model = (DefaultTableModel) bookListtable.getModel();
	    model.setRowCount(0); // Xóa tất cả các dòng hiện có trong bảng

	    // Thêm lại dữ liệu từ danh sách sách mới nhất
	    BookServices bookServices = new BookServices();
	    List<Book> books = bookServices.getAllBook();
	    for (Book book : books) {
	        model.addRow(new Object[] { book.getmaSach(), book.getTenSach(), book.getTenTacGia(), book.getTenTheLoai(), book.getTenNXB(), book.getNamXuatBan() });
	    }
	}
	
	// Phương thức tìm kiếm sách theo tiêu đề
	private void searchBooksByTitle(String title) {
	    DefaultTableModel model = (DefaultTableModel) bookListtable.getModel();
	    model.setRowCount(0); // Xóa dữ liệu hiện có trong bảng
	    BookServices bookServices = new BookServices();
	    List<Book> foundBooks = bookServices.searchBooksByName(title);
	    for (Book book : foundBooks) {
	        model.addRow(new Object[] { book.getmaSach(), book.getTenSach(), book.getTenTacGia(), book.getTenTheLoai(), book.getTenNXB(), book.getNamXuatBan() });
	    }
	}

	// Phương thức tìm kiếm sách theo tác giả
	private void searchBooksByAuthor(String author) {
	    DefaultTableModel model = (DefaultTableModel) bookListtable.getModel();
	    model.setRowCount(0); // Xóa dữ liệu hiện có trong bảng
	    BookServices bookServices = new BookServices();
	    List<Book> foundBooks = bookServices.searchBooksByAuthor(author);
	    for (Book book : foundBooks) {
	        model.addRow(new Object[] { book.getmaSach(), book.getTenSach(), book.getTenTacGia(), book.getTenTheLoai(), book.getTenNXB(), book.getNamXuatBan() });
	    }
	}

	// Phương thức tìm kiếm sách theo thể loại
	private void searchBooksByCategory(String category) {
	    DefaultTableModel model = (DefaultTableModel) bookListtable.getModel();
	    model.setRowCount(0); // Xóa dữ liệu hiện có trong bảng
	    BookServices bookServices = new BookServices();
	    List<Book> foundBooks = bookServices.searchBooksByCategory(category);
	    for (Book book : foundBooks) {
	        model.addRow(new Object[] { book.getmaSach(), book.getTenSach(), book.getTenTacGia(), book.getTenTheLoai(), book.getTenNXB(), book.getNamXuatBan() });
	    }
	}
	
	private void sortTable(int columnIndex) {
        DefaultTableModel model = (DefaultTableModel) bookListtable.getModel();
        BookServices bookServices = new BookServices();
        List<Book> books = bookServices.getAllBook(); // Assuming a static method to get all books
        books.sort((Comparator<? super Book>) new Comparator<Book>() {
            @Override
            public int compare(Book book1, Book book2) {
                switch (columnIndex) {
                    case 0: // Sort by ID
                        return Integer.compare(book1.getMaSach(), book2.getMaSach());
                    case 1: // Sort by Book Name
                        return book1.getTenSach().compareTo(book2.getTenSach());
                    case 2: // Sort by Author
                        return book1.getTenTacGia().compareTo(book2.getTenTacGia());
                    case 3: // Sort by Category
                        return book1.getTenTheLoai().compareTo(book2.getTenTheLoai());
                    case 4: // Sort by Publisher
                        return book1.getTenNXB().compareTo(book2.getTenNXB());
                    case 5: // Sort by Year
                        return Integer.compare(book1.getNamXuatBan(), book2.getNamXuatBan());
                    default:
                        return 0;
                }
            }
        });

        // Clear current table data
        model.setRowCount(0);

        // Add sorted data to table
        for (Book book : books) {
            model.addRow(new Object[] { book.getMaSach(), book.getTenSach(), book.getTenTacGia(), book.getTenTheLoai(),
                    book.getTenNXB(), book.getNamXuatBan() });
        }
    }
}
