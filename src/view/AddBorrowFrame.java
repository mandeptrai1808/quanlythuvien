package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import models.Accounts;
import models.Book;
import servives.BookServices;
import servives.BorrowService;
import servives.UserService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBorrowFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable UserListTable;
	private JTable bookListTable;
	private JTable BooksTable;
	private JTextField managerNameTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBorrowFrame frame = new AddBorrowFrame();
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
	public AddBorrowFrame() {
		setTitle("ĐĂNG KÍ MƯƠN SÁCH");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Chọn người mượn:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 10, 251, 21);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 290, 138);
		contentPane.add(scrollPane);
		
		UserListTable = new JTable();
		scrollPane.setViewportView(UserListTable);
		

		UserListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		UserListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		UserListTable.setRowHeight(30);
		DefaultTableModel model = (DefaultTableModel) UserListTable.getModel();
        model.setColumnIdentifiers(new Object[] { "ID", "Username"});

        UserService userServices = new UserService();
        List<Accounts> accounts = userServices.getAllUsers();
        
        for (Accounts account : accounts) {
            model.addRow(new Object[] { account.getUserId(), account.getUsername()});
        }
        
        TableColumn idColumn = UserListTable.getColumnModel().getColumn(0);
        idColumn.setPreferredWidth(10); // Set preferred width to 100 pixels

        // Center align "ID" column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        idColumn.setCellRenderer(centerRenderer);
		scrollPane.setViewportView(UserListTable);
		
		JLabel lblChnSch = new JLabel("Chọn sách:");
		lblChnSch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChnSch.setBounds(330, 10, 251, 21);
		contentPane.add(lblChnSch);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(330, 41, 290, 138);
		contentPane.add(scrollPane_1);
		
		BooksTable = new JTable();
		scrollPane_1.setViewportView(BooksTable);
		BooksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		BooksTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		BooksTable.setRowHeight(30);
		
		JLabel cc = new JLabel("Nội dung:");
		cc.setFont(new Font("Tahoma", Font.BOLD, 15));
		cc.setBounds(10, 189, 133, 28);
		contentPane.add(cc);
		
		JTextArea contentTxt = new JTextArea();
		contentTxt.setBounds(10, 227, 616, 122);
		contentPane.add(contentTxt);
		
		JLabel contentTxt_1_1 = new JLabel("Tên nhân viên:");
		contentTxt_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentTxt_1_1.setBounds(10, 359, 133, 28);
		contentPane.add(contentTxt_1_1);
		
		JButton btnNewButton = new JButton("ĐĂNG KÍ MƯỢN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Lấy dữ liệu id User từ bảng UserListTable
		        int selectedUserRowIndex = UserListTable.getSelectedRow();
		        int selectedUserID = -1;
		        if (selectedUserRowIndex != -1) {
		            selectedUserID = (int) UserListTable.getValueAt(selectedUserRowIndex, 0); // Giả sử cột đầu tiên là ID
		        }
		        
		        // Lấy dữ liệu id Book từ bảng BooksTable
		        int selectedBookRowIndex = BooksTable.getSelectedRow();
		        int selectedBookID = -1;
		        if (selectedBookRowIndex != -1) {
		            selectedBookID = (int) BooksTable.getValueAt(selectedBookRowIndex, 0); // Giả sử cột đầu tiên là ID
		        }
		        
		        // Lấy dữ liệu từ contentTxt và managerNameTxt
		        String content = contentTxt.getText();
		        String managerName = managerNameTxt.getText();
		        
		        // Kiểm tra xem đã chọn User và Book chưa
		        if (selectedUserID == -1 || selectedBookID == -1) {
		            // Hiển thị thông báo nếu User hoặc Book chưa được chọn
		            JOptionPane.showMessageDialog(AddBorrowFrame.this, "Vui lòng chọn người mượn và sách cần mượn.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
		        // Kiểm tra xem content và managerName có dữ liệu không
		        if (content.isEmpty() || managerName.isEmpty()) {
		            // Hiển thị thông báo nếu content hoặc managerName không được nhập
		            JOptionPane.showMessageDialog(AddBorrowFrame.this, "Vui lòng nhập đầy đủ nội dung và tên nhân viên.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
		        // Thực hiện thao tác đăng ký mượn sách trong BorrowService
		        BorrowService borrowService = new BorrowService();
		        borrowService.addBorrow(selectedUserID, selectedBookID, content, managerName);
		        
		        // Hiển thị thông báo thành công
		        JOptionPane.showMessageDialog(AddBorrowFrame.this, "Đăng ký mượn sách thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(10, 473, 616, 50);
		contentPane.add(btnNewButton);
		
		managerNameTxt = new JTextField();
		managerNameTxt.setBounds(10, 397, 610, 53);
		contentPane.add(managerNameTxt);
		managerNameTxt.setColumns(10);
		DefaultTableModel modelTable = (DefaultTableModel) BooksTable.getModel();
		modelTable.setColumnIdentifiers(new Object[] { "ID", "Tên sách"});

        BookServices bookServices = new BookServices();
        List<Book> books = bookServices.getAllBook();
        
        for (Book book : books) {
        	modelTable.addRow(new Object[] { book.getmaSach(), book.getTenSach()});
        }
        
        TableColumn idColumnn = BooksTable.getColumnModel().getColumn(0);
        idColumnn.setPreferredWidth(10); // Set preferred width to 100 pixels
        
        for (Accounts account : accounts) {
            model.addRow(new Object[] { account.getUserId(), account.getUsername()});
        }
	       
	}
}
