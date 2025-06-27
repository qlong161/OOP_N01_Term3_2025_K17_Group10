package com.example.servingwebcontent.Database;

import com.example.servingwebcontent.Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductAiven {

    private static final String DB_URL = "jdbc:mysql://avnadmin:AVNS_5W0mNiZFLxdUEn9oYby@mysql-1a39bf8a-leducthuong17022005.c.aivencloud.com:19020/OOP?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_5W0mNiZFLxdUEn9oYby";

    public ProductAiven() {}

    // 1. Lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        List<Product> items = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                items.add(mapResultSetToProduct(rs));
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
        }
        return items;
    }

    public List<Product> findAll() {
        return getAllProducts();
    }

    // 2. Tìm sản phẩm theo ID
    public Product findById(String id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToProduct(rs);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm sản phẩm: " + e.getMessage());
        }
        return null;
    }

    // 3. Thêm sản phẩm mới
    public void insertProduct(Product p) {
        String sql = "INSERT INTO product (id, name, price, quantity) VALUES (?, ?, ?, ?)";
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

    // 4. Cập nhật sản phẩm
    public void updateProduct(Product p) {
        String sql = "UPDATE product SET name = ?, price = ?, quantity = ? WHERE id = ?";
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

    // 5. Xóa sản phẩm
    public void deleteProduct(String id) {
        String sql = "DELETE FROM product WHERE id = ?";
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

    // 6. Lấy sản phẩm hết hàng (quantity = 0)
    public List<Product> getOutOfStockProducts() {
        List<Product> items = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE quantity = 0";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                items.add(mapResultSetToProduct(rs));
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách hết hàng: " + e.getMessage());
        }
        return items;
    }

    // 7. Lấy sản phẩm gần hết hàng (quantity > 0 AND quantity <= 5)
    public List<Product> getLowStockProducts() {
        List<Product> items = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE quantity > 0 AND quantity <= 5";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                items.add(mapResultSetToProduct(rs));
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách gần hết: " + e.getMessage());
        }
        return items;
    }

    // Hàm hỗ trợ để chuyển ResultSet thành Product
    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        return new Product(
            rs.getString("id"),
            rs.getString("name"),
            rs.getDouble("price"),
            rs.getInt("quantity")
        );
    }
}
