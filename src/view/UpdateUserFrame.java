package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Accounts;
import servives.UserService;

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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class UpdateUserFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameTxt;
    private JTextField phoneTxt;
    private JTextField emailTxt;
    private JTextField passwordTxt;
    private JComboBox<String> roleComboBox;
    public int userID = 0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateUserFrame frame = new UpdateUserFrame();
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
    public UpdateUserFrame() {
        setTitle("CHỈNH SỬA TÀI KHOẢN");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 484, 517);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setBounds(17, 89, 135, 36);
        contentPane.add(lblNewLabel);

        usernameTxt = new JTextField();
        usernameTxt.setBounds(162, 90, 283, 36);
        contentPane.add(usernameTxt);
        usernameTxt.setColumns(10);

        JLabel lblTcGi = new JLabel("Phone:");
        lblTcGi.setForeground(Color.BLACK);
        lblTcGi.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblTcGi.setBounds(17, 137, 77, 36);
        contentPane.add(lblTcGi);

        phoneTxt = new JTextField();
        phoneTxt.setColumns(10);
        phoneTxt.setBounds(162, 137, 283, 36);
        contentPane.add(phoneTxt);

        emailTxt = new JTextField();
        emailTxt.setColumns(10);
        emailTxt.setBounds(162, 183, 283, 36);
        contentPane.add(emailTxt);

        JLabel lblThLoi = new JLabel("Email:");
        lblThLoi.setForeground(Color.BLACK);
        lblThLoi.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblThLoi.setBounds(17, 183, 77, 36);
        contentPane.add(lblThLoi);

        passwordTxt = new JTextField();
        passwordTxt.setColumns(10);
        passwordTxt.setBounds(162, 230, 283, 36);
        contentPane.add(passwordTxt);

        JLabel lblNxb = new JLabel("Password:");
        lblNxb.setForeground(Color.BLACK);
        lblNxb.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNxb.setBounds(17, 229, 135, 36);
        contentPane.add(lblNxb);

        JLabel lblNmXutBn = new JLabel("Role:");
        lblNmXutBn.setForeground(Color.BLACK);
        lblNmXutBn.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNmXutBn.setBounds(17, 276, 135, 36);
        contentPane.add(lblNmXutBn);

        JButton updateBtn = new JButton("LƯU");
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get data from text fields
                String username = usernameTxt.getText();
                String phone = phoneTxt.getText();
                String email = emailTxt.getText();
                String password = passwordTxt.getText();
                String role = (String) roleComboBox.getSelectedItem();

                // Call updateUser method in UserService
                UserService userService = new UserService();
                userService.updateUser(userID, username, phone, email, password, role);

                // Show success message
                JOptionPane.showMessageDialog(UpdateUserFrame.this, "Cập nhật thông tin tài khoản thành công!");

                // Close the update user frame
                dispose();
            }
        });
        updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        updateBtn.setBounds(30, 329, 415, 49);
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
        cancelBtn.setBounds(30, 388, 415, 49);
        contentPane.add(cancelBtn);

        JLabel lblNewLabel_1 = new JLabel("CHỈNH SỬA THÔNG TIN TÀI KHOẢN");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(10, 10, 435, 48);
        contentPane.add(lblNewLabel_1);

        JSeparator separator = new JSeparator();
        separator.setBounds(20, 68, 425, 11);
        contentPane.add(separator);

        roleComboBox = new JComboBox<>();
        roleComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
        roleComboBox.setModel(new DefaultComboBoxModel(new String[] {"ADMIN", "USER"}));
        roleComboBox.setBounds(162, 276, 283, 40);
        contentPane.add(roleComboBox);
    }

    public void updateUserData(Accounts user) {
        // Set text for each JTextField based on the user data
        userID = user.getUserId();
        usernameTxt.setText(user.getUsername());
        phoneTxt.setText(user.getPhone());
        emailTxt.setText(user.getEmail());
        passwordTxt.setText(user.getPassword());
        roleComboBox.setSelectedItem(user.getRole());
    }
}
