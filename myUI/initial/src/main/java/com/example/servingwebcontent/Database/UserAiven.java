package com.example.servingwebcontent.Database;

import com.example.servingwebcontent.Model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAiven {

    private static final String DB_URL = "jdbc:mysql://avnadmin:AVNS_5W0mNiZFLxdUEn9oYby@mysql-1a39bf8a-leducthuong17022005.c.aivencloud.com:19020/OOP?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_5W0mNiZFLxdUEn9oYby";

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                list.add(new User(rs.getString("id"), rs.getString("name")));
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy danh sách user: " + e.getMessage());
        }

        return list;
    }

    public User findById(String id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("id"), rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("Lỗi tìm user: " + e.getMessage());
        }
        return null;
    }

    public void insertUser(User user) {
        String sql = "INSERT INTO user (id, name) VALUES (?, ?)";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi thêm user: " + e.getMessage());
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật user: " + e.getMessage());
        }
    }

    public void deleteUser(String id) {
        String sql = "DELETE FROM user WHERE id = ?";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi xoá user: " + e.getMessage());
        }
    }
}
