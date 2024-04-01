package server;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getJDBC() {
        try {
            System.out.println("Connected");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/quanlythuvien", "root", "");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
