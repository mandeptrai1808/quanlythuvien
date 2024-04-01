package servives;

import java.util.ArrayList;
import java.util.List;

import models.Book;
import server.BookController;

public class BookServices {
	private BookController bookController;
	public BookServices() {
		bookController = new BookController();
	}
	
	public List<Book> getAllBook(){
		return bookController.getAllBooks();
	}
	
	public void addBook(Book newBook) {
		bookController.addBook(newBook);
	}
	
	public void deleteBook(int maSach) {
		bookController.deleteBook(maSach);
	}
	
	public Book getBookByID(int bookID) {
		List<Book> allBooks = getAllBook();
        
        // Iterate through the list to find the book with the specified ID
        for (Book book : allBooks) {
            if (book.getmaSach() == bookID) {
                return book; // Return the book if found
            }
        }
		return null;
	}
	
	public void updateBook(int maSach, String tenSach, String tenTacGia, String tenTheLoai, String tenNXB, int namXuatBan) {
		bookController.updateBook(maSach, tenSach, tenTacGia, tenTheLoai, tenNXB, namXuatBan);
	}
	
	
	public List<Book> searchBooksByName(String keyword) {
        List<Book> searchResult = new ArrayList<>();
        List<Book> bookList = getAllBook();
        // Loop through the list of books to find matches by name
        for (Book book : bookList) {
            if (book.getTenSach().toLowerCase().contains(keyword.toLowerCase())) {
                searchResult.add(book);
            }
        }
        
        return searchResult;
    }

    // Method to search for books by category
    public List<Book> searchBooksByCategory(String keyword) {
        List<Book> searchResult = new ArrayList<>();
        List<Book> bookList = getAllBook();
        // Loop through the list of books to find matches by category
        for (Book book : bookList) {
            if (book.getTenTheLoai().toLowerCase().contains(keyword.toLowerCase())) {
                searchResult.add(book);
            }
        }
        
        return searchResult;
    }

    // Method to search for books by author
    public List<Book> searchBooksByAuthor(String keyword) {
        List<Book> searchResult = new ArrayList<>();
        List<Book> bookList = getAllBook();
        // Loop through the list of books to find matches by author
        for (Book book : bookList) {
            if (book.getTenTacGia().toLowerCase().contains(keyword.toLowerCase())) {
                searchResult.add(book);
            }
        }
        
        return searchResult;
    }
    

    public int countTotalBooks() {
        // Sử dụng hàm countTotalBooks của BookController để đếm số lượng sách
        return bookController.countTotalBooks();
    }
    

    
	
}
