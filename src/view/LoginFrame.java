package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import servives.UserService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setTitle("Đăng nhập");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ĐĂNG NHẬP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(200, 24, 158, 48);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(94, 82, 369, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(33, 107, 55, 40);
		contentPane.add(lblNewLabel_1);
		
		emailField = new JTextField();
		emailField.setBounds(142, 112, 386, 40);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(33, 192, 91, 40);
		contentPane.add(lblNewLabel_1_1);
		
		JButton loginBtn = new JButton("ĐĂNG NHẬP");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String email = emailField.getText().trim();
	                String password = new String(passwordField.getPassword());
	                UserService userServices = new UserService();
	                // Kiểm tra đăng nhập
	                boolean loggedIn = userServices.Login(email, password);
	                
	                if (loggedIn) {
	                    // Đăng nhập thành công, chuyển sang ListBookFrame
	                    ListBookFrame listBookFrame = new ListBookFrame();
	                    listBookFrame.setVisible(true);
	                    dispose(); // Đóng frame hiện tại
	                } else {
	                    // Đăng nhập không thành công, hiển thị thông báo
	                    JOptionPane.showMessageDialog(LoginFrame.this, "Email hoặc mật khẩu không đúng!", "Đăng nhập không thành công", JOptionPane.ERROR_MESSAGE);
	                }
			}
		});
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loginBtn.setBounds(33, 275, 495, 40);
		contentPane.add(loginBtn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(142, 192, 386, 38);
		contentPane.add(passwordField);
	}
}
