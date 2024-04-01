package server;

import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Book;
public class BookController {

    // Phương thức đếm tổng số sách
    public int countTotalBooks() {
        int totalCount = 0;
        Connection conn = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sql = "SELECT tong_so_sach FROM tong_so_sach_view";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        totalCount = resultSet.getInt("tong_so_sach");
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
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Đặt lại auto commit
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return totalCount;
    }

    // Phương thức lấy danh sách tất cả sách
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sql = "SELECT Sach.Ma_sach, Sach.Ten_sach, Tac_gia.Ten_tac_gia, The_loai.Ten_the_loai, Nha_xuat_ban.Ten_NXB, Sach.Nam_xuat_ban " +
                         "FROM Sach " +
                         "INNER JOIN Tac_gia ON Sach.Ma_tac_gia = Tac_gia.Ma_tac_gia " +
                         "INNER JOIN The_loai ON Sach.Ma_the_loai = The_loai.Ma_the_loai " +
                         "INNER JOIN Nha_xuat_ban ON Sach.Ma_NXB = Nha_xuat_ban.Ma_NXB";
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int maSach = rs.getInt("Ma_sach");
                    String tenSach = rs.getString("Ten_sach");
                    String tenTacGia = rs.getString("Ten_tac_gia");
                    String tenTheLoai = rs.getString("Ten_the_loai");
                    String tenNXB = rs.getString("Ten_NXB");
                    int namXuatBan = rs.getInt("Nam_xuat_ban");

                    Book book = new Book(maSach, tenSach, tenTacGia, tenTheLoai, tenNXB, namXuatBan);
                    books.add(book);
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
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Đặt lại auto commit
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return books;
    }

    // Phương thức xóa sách
    public void deleteBook(int maSach) {
        Connection conn = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sql = "DELETE FROM Sach WHERE Ma_sach = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, maSach);
                
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Book with ID " + maSach + " deleted successfully.");
                } else {
                    System.out.println("Failed to delete Book with ID " + maSach + ".");
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
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Đặt lại auto commit
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Phương thức thêm sách
    public void addBook(Book newBook) {
        Connection conn = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sql = "{CALL AddNewBook(?, ?, ?, ?, ?)}";
            try (CallableStatement cstmt = conn.prepareCall(sql)) {
                cstmt.setString(1, newBook.getTenSach());
                cstmt.setString(2, newBook.getTenTacGia());
                cstmt.setString(3, newBook.getTenTheLoai());
                cstmt.setString(4, newBook.getTenNXB());
                cstmt.setInt(5, newBook.getNamXuatBan());
                
                boolean hasResult = cstmt.execute();
                if (!hasResult) {
                    System.out.println("Book added successfully.");
                } else {
                    System.out.println("Failed to add Book.");
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
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Đặt lại auto commit
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Phương thức cập nhật sách
    public void updateBook(int maSach, String tenSach, String tenTacGia, String tenTheLoai, String tenNXB, int namXuatBan) {
        Connection conn = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sql = "CALL updateBook(?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, maSach);
                pstmt.setString(2, tenSach);
                pstmt.setString(3, tenTacGia);
                pstmt.setString(4, tenTheLoai);
                pstmt.setString(5, tenNXB);
                pstmt.setInt(6, namXuatBan);
                
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Book updated successfully.");
                } else {
                    System.out.println("Failed to update Book.");
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
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Đặt lại auto commit
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Phương thức khác cũng được thêm transaction tương tự
}

