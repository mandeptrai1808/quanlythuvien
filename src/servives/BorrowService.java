package servives;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import models.Borrow;
import server.BorrowController;

public class BorrowService {
    private BorrowController borrowController;

    public BorrowService() {
        this.borrowController = new BorrowController();
    }

    // Phương thức để lấy toàn bộ dữ liệu mượn/trả sách
    public List<Borrow> getAllBorrows() {
        return borrowController.getAllBorrowsWithDetails();
    }

    // Phương thức để mượn sách
    public void borrowBook(Borrow borrow) {
        borrowController.borrowBook(borrow);
    }

    // Phương thức để trả sách
    public void returnBook(int borrowId) {
        borrowController.returnBook(borrowId);
    }

    // Phương thức để xóa mượn/trả sách
    public void deleteBorrow(int id) {
        borrowController.deleteBorrow(id);
    }
    
    public List<Borrow> searchBorrowsByBorrowerName(String borrowerName) {
        List<Borrow> foundBorrows = new ArrayList<>();
        List<Borrow> borrowList = borrowController.getAllBorrowsWithDetails();
        for (Borrow borrow : borrowList) {
            // Assuming Borrow has a method getBorrowerName() to retrieve the borrower name
            if (borrow.getBorrowerName().toLowerCase().contains(borrowerName)) {
                foundBorrows.add(borrow);
            }
        }
        return foundBorrows;
    }

    // Method to search borrows by book title
    public List<Borrow> searchBorrowsByBookTitle(String bookTitle) {
        List<Borrow> foundBorrows = new ArrayList<>();
        List<Borrow> borrowList = borrowController.getAllBorrowsWithDetails();
        for (Borrow borrow : borrowList) {
            // Assuming Borrow has a method getBookTitle() to retrieve the book title
            if (borrow.getBookTitle().toLowerCase().contains(bookTitle)) {
                foundBorrows.add(borrow);
            }
        }
        return foundBorrows;
    }

    public void addBorrow(int selectedUserID, int selectedBookID, String content, String managerName) {
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();

        // Định dạng ngày hiện tại thành chuỗi theo định dạng "yyyy-MM-dd"
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Tạo đối tượng Borrow với ngày hiện tại và thông tin khác
        Borrow borrow = new Borrow(0, selectedUserID, selectedBookID, content, formattedDate, false, managerName);

        // Gọi phương thức borrowBook từ borrowController để thêm borrow vào hệ thống
        borrowController.borrowBook(borrow);
    }
    
    public int soSachChuaTra(){
    	return borrowController.countUnreturnedBooks();
    }
    public int soSachDaTra(){
    	return borrowController.countReturnedBooks();
    }
}

