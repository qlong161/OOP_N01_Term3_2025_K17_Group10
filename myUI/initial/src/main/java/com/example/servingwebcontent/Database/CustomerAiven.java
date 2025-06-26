package com.example.servingwebcontent.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.servingwebcontent.Model.Customer;

public class CustomerAiven {

    private static final String DB_URL = "jdbc:mysql://avnadmin:AVNS_5W0mNiZFLxdUEn9oYby@mysql-1a39bf8a-leducthuong17022005.c.aivencloud.com:19020/OOP?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_5W0mNiZFLxdUEn9oYby";

    public CustomerAiven() {}

    // Lấy tất cả khách hàng
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                customers.add(new Customer(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("type")
                ));
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách khách hàng: " + e.getMessage());
        }
        return customers;
    }


    // Tìm khách hàng theo ID
    public Customer findById(String id) {
        String sql = "SELECT * FROM customer WHERE id=?";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("type")
                );
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm khách hàng: " + e.getMessage());
        }
        return null;
    }

    // Thêm khách hàng mới
    public void insertCustomer(Customer c) {
        String sql = "INSERT INTO customer (id, name, email, type) VALUES (?, ?, ?, ?)";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, c.getId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getType());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm khách hàng: " + e.getMessage());
        }
    }

    // Cập nhật thông tin khách hàng
    public void updateCustomer(Customer c) {
        String sql = "UPDATE customer SET name=?, email=?, type=? WHERE id=?";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getType());
            ps.setString(4, c.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật khách hàng: " + e.getMessage());
        }
    }

    // Xoá khách hàng
    public void deleteCustomer(String id) {
        String sql = "DELETE FROM customer WHERE id=?";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi khi xoá khách hàng: " + e.getMessage());
        }
    }
}
