package servives;

import java.util.ArrayList;
import java.util.List;

import models.Accounts;
import server.UserController;

public class UserService {
    private UserController userController;

    public UserService() {
        userController = new UserController();
    }

    public List<Accounts> getAllUsers() {
        return userController.getAllUsers();
    }
    

    public void addUser(Accounts newUser) {
        userController.addUser(newUser);
    }

    public void deleteUser(int userId) {
        userController.deleteUser(userId);
    }

    public Accounts getUserByID(int userId) {
        List<Accounts> allUsers = getAllUsers();

        // Lặp qua danh sách để tìm người dùng có ID cụ thể
        for (Accounts user : allUsers) {
            if (user.getUserId() == userId) {
                return user; // Trả về người dùng nếu tìm thấy
            }
        }
        return null;
    }

    public void updateUser(int userId, String username, String phone, String email, String password, String role) {
        userController.updateUser(userId, username, phone, email, password, role);
    }

    public List<Accounts> searchUsersByUsername(String keyword) {
        List<Accounts> searchResult = new ArrayList<>();
        List<Accounts> userList = getAllUsers();
        // Lặp qua danh sách để tìm người dùng theo tên người dùng
        for (Accounts user : userList) {
            if (user.getUsername().toLowerCase().contains(keyword.toLowerCase())) {
                searchResult.add(user);
            }
        }

        return searchResult;
    }

	public List<Accounts> getAdminUsers() {
		
		 return userController.getAllAdmin();
	}

	public boolean Login(String email, String pass) {
		return userController.login(email, pass);
	}
	
	public int CountUser() {
		return userController.countUser();
	}
	
	public int CountAdmin() {
		return userController.countAdmin();
	}
    // Các phương thức tìm kiếm theo phone, email, role có thể được thêm vào tương tự như searchUsersByUsername
}
