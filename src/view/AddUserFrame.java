package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Accounts;
//import services.UserService;
import servives.UserService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddUserFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField passwordField;
    private UserService userService;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddUserFrame frame = new AddUserFrame();
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
    public AddUserFrame() {
        setTitle("THÊM NGƯỜI DÙNG");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 456, 513);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(21, 95, 85, 40);
        contentPane.add(lblNewLabel);
        
        usernameField = new JTextField();
        usernameField.setBounds(116, 92, 307, 40);
        contentPane.add(usernameField);
        usernameField.setColumns(10);
        
        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPhone.setBounds(21, 139, 85, 40);
        contentPane.add(lblPhone);
        
        phoneField = new JTextField();
        phoneField.setColumns(10);
        phoneField.setBounds(116, 142, 307, 40);
        contentPane.add(phoneField);
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmail.setBounds(21, 194, 85, 40);
        contentPane.add(lblEmail);
        
        emailField = new JTextField();
        emailField.setColumns(10);
        emailField.setBounds(116, 191, 307, 40);
        contentPane.add(emailField);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setBounds(21, 240, 85, 40);
        contentPane.add(lblPassword);
        
        passwordField = new JTextField();
        passwordField.setColumns(10);
        passwordField.setBounds(116, 241, 307, 40);
        contentPane.add(passwordField);
        
        JComboBox roleField = new JComboBox();
        roleField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        roleField.setModel(new DefaultComboBoxModel(new String[] {"ADMIN", "USER"}));
        roleField.setBounds(116, 291, 304, 40);
        contentPane.add(roleField);
        
        JLabel lblRole = new JLabel("Role:");
        lblRole.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblRole.setBounds(21, 290, 85, 40);
        contentPane.add(lblRole);
        
        JButton btnAdd = new JButton("Thêm");
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                String role = (String) roleField.getSelectedItem(); 
                
                // Tạo một đối tượng Account từ dữ liệu vừa lấy được
                Accounts newUser = new Accounts(1,username, phone, email, password, role);
                
                // Gọi hàm addUser trong service và truyền đối tượng Account vừa tạo
                userService = new UserService();
                userService.addUser(newUser);
                
                // Thông báo cho người dùng và đóng frame AddUser
                JOptionPane.showMessageDialog(AddUserFrame.this, "Thêm người dùng thành công!");
                dispose(); // Đóng frame AddUser
            }
        });
        btnAdd.setBounds(21, 350, 402, 40);
        contentPane.add(btnAdd);
        
        JButton btnCancel = new JButton("Hủy");
        btnCancel.setBackground(new Color(255, 128, 0));
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng frame AddUser
            }
        });
        btnCancel.setBounds(21, 401, 402, 39);
        contentPane.add(btnCancel);
        
        JLabel lblNewLabel_1 = new JLabel("NHẬP DỮ LIỆU NGƯỜI DÙNG MỚI");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(21, 20, 399, 41);
        contentPane.add(lblNewLabel_1);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(31, 71, 392, 2);
        contentPane.add(separator);
        
        
    }
}
