package server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Borrow;
public class BorrowController {
	public int countReturnedBooks() {
	    int returnedBooksCount = 0;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Connection conn = null;
	    try {
	        conn = DBConnection.getJDBC();
	        String sqlQuery = "SELECT count_returned_books FROM sach_da_tra_count_view";
	        preparedStatement = conn.prepareStatement(sqlQuery);
	        resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            returnedBooksCount = resultSet.getInt("count_returned_books");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	    } finally {
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (preparedStatement != null) {
	            try {
	                preparedStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return returnedBooksCount;
	}

	public int countUnreturnedBooks() {
	    int unreturnedCount = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        conn = DBConnection.getJDBC();
	        String sql = "SELECT sach_chua_tra_count FROM sach_chua_tra_count_view";
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            unreturnedCount = rs.getInt("sach_chua_tra_count");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            System.out.println("Error while closing resources: " + e.getMessage());
	        }
	    }
	    return unreturnedCount;
	}

    // Phương thức lấy danh sách mượn sách với thông tin chi tiết
    public List<Borrow> getAllBorrowsWithDetails() {
        List<Borrow> borrows = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sql = "SELECT muon_tra.muon_tra_id as id, muon_tra.user_id, muon_tra.book_id, muon_tra.noi_dung, muon_tra.ngay_muon, muon_tra.da_tra, muon_tra.nguoi_quan_ly, tai_khoan.username AS nguoi_muon, sach.ten_sach " +
                         "FROM muon_tra " +
                         "INNER JOIN tai_khoan ON muon_tra.user_id = tai_khoan.user_id " +
                         "INNER JOIN sach ON muon_tra.book_id = sach.Ma_sach";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                int bookId = rs.getInt("book_id");
                String content = rs.getString("noi_dung");
                String borrowDate = rs.getString("ngay_muon");
                boolean returned = rs.getBoolean("da_tra");
                String manager = rs.getString("nguoi_quan_ly");
                String borrowerName = rs.getString("nguoi_muon");
                String bookTitle = rs.getString("ten_sach");

                Borrow borrow = new Borrow(id, userId, bookId, content, borrowDate, returned, manager);
                borrow.setBorrowerName(borrowerName);
                borrow.setBookTitle(bookTitle);
                borrows.add(borrow);
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
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing resources: " + e.getMessage());
            }
        }
        return borrows;
    }
    
    // Phương thức mượn sách
    public void borrowBook(Borrow borrow) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sql = "{CALL BorrowBook(?, ?, ?, ?, ?)}";
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, borrow.getUserId());
            cstmt.setInt(2, borrow.getBookId());
            cstmt.setString(3, borrow.getContent());
            cstmt.setString(4, borrow.getBorrowDate());
            cstmt.setString(5, borrow.getManager());
            
            boolean hasResult = cstmt.execute();
            if (!hasResult) {
                System.out.println("Book borrowed successfully.");
            } else {
                System.out.println("Failed to borrow Book.");
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
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (cstmt != null) cstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing resources: " + e.getMessage());
            }
        }
    }
    
    // Phương thức trả sách
    public void returnBook(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sql = "UPDATE muon_tra SET da_tra = true WHERE muon_tra_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Failed to return Book.");
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
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing resources: " + e.getMessage());
            }
        }
    }
    
    // Phương thức xóa thông tin mượn sách
    public void deleteBorrow(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnection.getJDBC();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            String sql = "DELETE FROM muon_tra WHERE muon_tra_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Borrow with ID " + id + " deleted successfully.");
            } else {
                System.out.println("Failed to delete Borrow with ID " + id + ".");
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
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing resources: " + e.getMessage());
            }
        }
    }
}
