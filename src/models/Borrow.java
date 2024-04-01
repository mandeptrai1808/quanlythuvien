package models;

public class Borrow {
	private int id;
    private int userId;
    private int bookId;
    private String content;
    private String borrowDate;
    private boolean returned;
    private String manager;
    private String borrowerName; // Tên người mượn
    private String bookTitle; // Tên sách

    // Constructor
    public Borrow(int id, int userId, int bookId, String content, String borrowDate, boolean returned, String manager) {
    	this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.content = content;
        this.borrowDate = borrowDate;
        this.returned = returned;
        this.manager = manager;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String toString() {
        return "Borrow ID: " + id +
                ", User ID: " + userId +
                ", Book ID: " + bookId +
                ", Borrow Date: " + borrowDate +
                ", Borrower Name: " + borrowerName +
                ", Book Title: " + bookTitle;
    }
}
