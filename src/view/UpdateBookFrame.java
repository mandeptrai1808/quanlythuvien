package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Book;
import servives.BookServices;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateBookFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField bookName;
	private JTextField authorName;
	private JTextField category;
	private JTextField nhaXuatBan;
	private JTextField namXuatBan;
	public 	int maSachCurrent = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateBookFrame frame = new UpdateBookFrame();
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
	public UpdateBookFrame() {
		setTitle("CHỈNH SỬA SÁCH");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 413, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên sách:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(17, 89, 77, 36);
		contentPane.add(lblNewLabel);
		
		bookName = new JTextField();
		bookName.setBounds(104, 90, 279, 36);
		contentPane.add(bookName);
		bookName.setColumns(10);
		
		JLabel lblTcGi = new JLabel("Tác giả:");
		lblTcGi.setForeground(Color.BLACK);
		lblTcGi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTcGi.setBounds(17, 137, 77, 36);
		contentPane.add(lblTcGi);
		
		authorName = new JTextField();
		authorName.setColumns(10);
		authorName.setBounds(104, 137, 279, 36);
		contentPane.add(authorName);
		
		category = new JTextField();
		category.setColumns(10);
		category.setBounds(104, 183, 279, 36);
		contentPane.add(category);
		
		JLabel lblThLoi = new JLabel("Thể loại:");
		lblThLoi.setForeground(Color.BLACK);
		lblThLoi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblThLoi.setBounds(17, 183, 77, 36);
		contentPane.add(lblThLoi);
		
		nhaXuatBan = new JTextField();
		nhaXuatBan.setColumns(10);
		nhaXuatBan.setBounds(162, 230, 221, 36);
		contentPane.add(nhaXuatBan);
		
		JLabel lblNxb = new JLabel("Nhà xuất bản:");
		lblNxb.setForeground(Color.BLACK);
		lblNxb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNxb.setBounds(17, 229, 135, 36);
		contentPane.add(lblNxb);
		
		namXuatBan = new JTextField();
		namXuatBan.setColumns(10);
		namXuatBan.setBounds(162, 277, 221, 36);
		contentPane.add(namXuatBan);
		
		JLabel lblNmXutBn = new JLabel("Năm xuất bản:");
		lblNmXutBn.setForeground(Color.BLACK);
		lblNmXutBn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNmXutBn.setBounds(17, 276, 135, 36);
		contentPane.add(lblNmXutBn);
		

		
		JButton updateBtn = new JButton("LƯU");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(maSachCurrent);
				 // Get data from text fields
                String tenSach = bookName.getText();
                String tenTacGia = authorName.getText();
                String theLoai = category.getText();
                String nhaXB = nhaXuatBan.getText();
                int namXb = Integer.parseInt(namXuatBan.getText());


                // Call updateBook method in BookServices
                BookServices bookServices = new BookServices();
                bookServices.updateBook(maSachCurrent, tenSach, tenTacGia, theLoai, nhaXB, namXb);

                // Show success message
                JOptionPane.showMessageDialog(UpdateBookFrame.this, "Cập nhật thông tin sách thành công!");

                // Close the update book frame
                dispose();
			}
		});
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		updateBtn.setBounds(30, 329, 353, 49);
		contentPane.add(updateBtn);
		
		JButton cancelBtn = new JButton("Hủy");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setForeground(Color.WHITE);
		cancelBtn.setBackground(new Color(255, 0, 0));
		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cancelBtn.setBounds(30, 388, 353, 49);
		contentPane.add(cancelBtn);
		
		JLabel lblNewLabel_1 = new JLabel("CHỈNH SỬA THÔNG TIN SÁCH");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 10, 357, 48);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 68, 363, 2);
		contentPane.add(separator);
	}
	
	public void updateBookData(Book book) {
	    // Set text for each JTextField based on the book data
		maSachCurrent = book.getmaSach();
	    bookName.setText(book.getTenSach());
	    authorName.setText(book.getTenTacGia());
	    category.setText(book.getTenTheLoai());
	    nhaXuatBan.setText(book.getTenNXB());
	    namXuatBan.setText(String.valueOf(book.getNamXuatBan())); // Convert int to String
	}

}
