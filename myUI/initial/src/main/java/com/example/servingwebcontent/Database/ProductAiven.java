package com.example.servingwebcontent.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.servingwebcontent.Model.Product;

public class ProductAiven {

    private static final String DB_URL = "jdbc:mysql://avnadmin:AVNS_5W0mNiZFLxdUEn9oYby@mysql-1a39bf8a-leducthuong17022005.c.aivencloud.com:19020/defaultdb?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_5W0mNiZFLxdUEn9oYby";

    public ProductAiven() {}

    // Lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        List<Product> items = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                Product p = new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")
                );
                items.add(p);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
        }
        return items;
    }

    // Tìm theo ID
    public Product findById(String id) {
        String sql = "SELECT * FROM Product WHERE id=?";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")
                );
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm sản phẩm: " + e.getMessage());
        }
        return null;
    }

    // Thêm mới sản phẩm
    public void insertProduct(Product p) {
        String sql = "INSERT INTO Product (id, name, price, quantity) VALUES (?, ?, ?, ?)";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, p.getId());
            ps.setString(2, p.getName());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getQuantity());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
        }
    }

    // Cập nhật thông tin sản phẩm
    public void updateProduct(Product p) {
        String sql = "UPDATE Product SET name=?, price=?, quantity=? WHERE id=?";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getQuantity());
            ps.setString(4, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
    }

    // Xoá sản phẩm
    public void deleteProduct(String id) {
        String sql = "DELETE FROM Product WHERE id=?";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa sản phẩm: " + e.getMessage());
        }
    }

    // Sản phẩm đã hết (quantity = 0)
    public List<Product> getOutOfStockProducts() {
        List<Product> items = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE quantity = 0";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                items.add(new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")
                ));
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách hết hàng: " + e.getMessage());
        }
        return items;
    }

    // Sản phẩm gần hết (quantity <= 5)
    public List<Product> getLowStockProducts() {
        List<Product> items = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE quantity > 0 AND quantity <= 5";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                items.add(new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")
                ));
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách gần hết: " + e.getMessage());
        }
        return items;
    }
}
