package server;

import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Accounts;

public class UserController {
    
    // Phương thức đếm số lượng user
    public int countUser() {
        int userCount = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conn = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sqlQuery = "SELECT user_count FROM user_count_view";
            preparedStatement = conn.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                userCount = resultSet.getInt("user_count");
            }
            
            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userCount;
    }
    
    // Phương thức đếm số lượng admin
    public int countAdmin() {
        int adminCount = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conn = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sqlQuery = "SELECT user_count FROM admin_count_view";
            preparedStatement = conn.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                adminCount = resultSet.getInt("user_count");
            }
            
            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return adminCount;
    }
    
    // Phương thức đăng nhập
    public boolean login(String email, String password) {
        boolean loggedIn = false;
        Connection conn = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sql = "SELECT login(?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        loggedIn = resultSet.getBoolean(1);
                    }
                }
            }
            
            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return loggedIn;
    }
    
    // Phương thức lấy danh sách tất cả users
    public List<Accounts> getAllUsers() {
        List<Accounts> users = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sql = "SELECT * FROM tai_khoan";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");

                Accounts user = new Accounts(userId, username, phone, email, password, role);
                users.add(user);
            }
            
            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
    
    // Phương thức lấy danh sách tất cả admins
    public List<Accounts> getAllAdmin() {
        List<Accounts> admins = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sql = "SELECT * FROM tai_khoan WHERE role = 'ADMIN'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");

                Accounts admin = new Accounts(userId, username, phone, email, password, role);
                admins.add(admin);
            }
            
            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return admins;
    }
    
    // Phương thức xóa user
    public void deleteUser(int userId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sql = "DELETE FROM tai_khoan WHERE user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User with ID " + userId + " deleted successfully.");
            } else {
                System.out.println("Failed to delete User with ID " + userId + ".");
            }
            
            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Phương thức thêm user
    public void addUser(Accounts newUser) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sql = "INSERT INTO tai_khoan (username, phone, email, password, role) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPhone());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getPassword());
            pstmt.setString(5, newUser.getRole());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("Failed to add User.");
            }
            
            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Phương thức cập nhật thông tin user
    public void updateUser(int userId, String username, String phone, String email, String password, String role) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sql = "UPDATE tai_khoan SET username = ?, phone = ?, email = ?, password = ?, role = ? WHERE user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.setString(5, role);
            pstmt.setInt(6, userId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("Failed to update User.");
            }
            
            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
