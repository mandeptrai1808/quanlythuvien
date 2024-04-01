package server;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import models.Accounts;
import models.Borrow;
import servives.BorrowService;

public class test {
    public static void main(String[] args) {
    	 Scanner scanner = new Scanner(System.in);

         // Yêu cầu người dùng nhập email và mật khẩu
         System.out.print("Nhập email: ");
         String email = scanner.nextLine();

         System.out.print("Nhập mật khẩu: ");
         String password = scanner.nextLine();

         // Tạo một đối tượng UserController
         UserController controller = new UserController();

         // Kiểm tra đăng nhập
         boolean loggedIn = controller.login(email, password);

         // Xử lý kết quả
         if (loggedIn) {
             System.out.println("Đăng nhập thành công!");
         } else {
             System.out.println("Đăng nhập không thành công. Vui lòng kiểm tra lại email và mật khẩu.");
         }

         // Đóng đối tượng Scanner
         scanner.close();
    }
}
