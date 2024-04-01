package models;

public class Book {
    private int maSach;
    private String tenSach;
    private String tenTacGia;
    private String tenTheLoai;
    private String tenNXB;
    private int namXuatBan;

    // Constructor
    public Book(int maSach, String tenSach, String tenTacGia, String tenTheLoai, String tenNXB, int namXuatBan) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tenTacGia = tenTacGia;
        this.tenTheLoai = tenTheLoai;
        this.tenNXB = tenNXB;
        this.namXuatBan = namXuatBan;
    }

    // Getters and setters
    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }
    
    public int getmaSach() {
    	return maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    // toString method to display Book information
    @Override
    public String toString() {
        return "Book{" +
                "maSach=" + maSach +
                ", tenSach='" + tenSach + '\'' +
                ", tenTacGia='" + tenTacGia + '\'' +
                ", tenTheLoai='" + tenTheLoai + '\'' +
                ", tenNXB='" + tenNXB + '\'' +
                ", namXuatBan=" + namXuatBan +
                '}';
    }
}
