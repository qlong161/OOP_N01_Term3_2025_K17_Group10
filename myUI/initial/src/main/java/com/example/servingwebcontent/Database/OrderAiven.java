package com.example.servingwebcontent.Database;

import com.example.servingwebcontent.Model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class OrderAiven {

    private static final String DB_URL = "jdbc:mysql://avnadmin:AVNS_5W0mNiZFLxdUEn9oYby@mysql-1a39bf8a-leducthuong17022005.c.aivencloud.com:19020/OOP?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_5W0mNiZFLxdUEn9oYby";

    public OrderAiven() {}

    // ✅ PATCH: Overload để hỗ trợ truyền userId trực tiếp
    public void insertOrder(Order order, String userId) {
        if (order.getUser() == null || order.getUser().getId() == null) {
            order.setUser(new User(userId, ""));
        }
        insertOrder(order); // Gọi lại hàm chính
    }

    // Thêm đơn hàng mới cho user
    public void insertOrder(Order order) {
        String insertOrderSQL = "INSERT INTO orders (order_id, customer_id, user_id, status, date) VALUES (?, ?, ?, ?, ?)";
        String insertItemSQL = "INSERT INTO order_item (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL);
            PreparedStatement itemStmt = conn.prepareStatement(insertItemSQL)
        ) {
            conn.setAutoCommit(false);

            orderStmt.setString(1, order.getOrderId());
            orderStmt.setString(2, order.getCustomer().getId());
            orderStmt.setString(3, order.getUser().getId());
            orderStmt.setString(4, order.getStatus());
            orderStmt.setTimestamp(5, Timestamp.valueOf(order.getDate()));
            orderStmt.executeUpdate();

            for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();

                itemStmt.setString(1, order.getOrderId());
                itemStmt.setString(2, product.getId());
                itemStmt.setInt(3, quantity);
                itemStmt.setDouble(4, product.getPrice());
                itemStmt.addBatch();
            }
            itemStmt.executeBatch();

            conn.commit();
            System.out.println("✔ Thêm đơn hàng thành công.");

        } catch (Exception e) {
            System.err.println("❌ Lỗi khi thêm đơn hàng: " + e.getMessage());
        }
    }

    public List<Order> getOrdersByUserId(String userId) {
        List<Order> orders = new ArrayList<>();
        String orderSQL = "SELECT * FROM orders WHERE user_id = ?";
        String itemSQL = "SELECT * FROM order_item WHERE order_id = ?";

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement orderStmt = conn.prepareStatement(orderSQL)
        ) {
            orderStmt.setString(1, userId);
            ResultSet rs = orderStmt.executeQuery();

            CustomerAiven customerAiven = new CustomerAiven();
            ProductAiven productAiven = new ProductAiven();

            while (rs.next()) {
                String orderId = rs.getString("order_id");
                String customerId = rs.getString("customer_id");
                String status = rs.getString("status");
                LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();

                Customer customer = customerAiven.findById(customerId);
                User user = new User(userId, "");
                Order order = new Order(orderId, customer, user, status, date);

                try (PreparedStatement itemStmt = conn.prepareStatement(itemSQL)) {
                    itemStmt.setString(1, orderId);
                    ResultSet itemRs = itemStmt.executeQuery();

                    while (itemRs.next()) {
                        String productId = itemRs.getString("product_id");
                        int quantity = itemRs.getInt("quantity");
                        double price = itemRs.getDouble("price");

                        Product product = productAiven.findById(productId);
                        product.setPrice(price);
                        order.addProduct(product, quantity);
                    }
                }

                orders.add(order);
            }

        } catch (Exception e) {
            System.err.println("❌ Lỗi khi lấy đơn hàng theo user: " + e.getMessage());
        }

        return orders;
    }

    public void deleteOrder(String orderId) {
        String deleteItemsSQL = "DELETE FROM order_item WHERE order_id = ?";
        String deleteOrderSQL = "DELETE FROM orders WHERE order_id = ?";

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement itemStmt = conn.prepareStatement(deleteItemsSQL);
            PreparedStatement orderStmt = conn.prepareStatement(deleteOrderSQL)
        ) {
            conn.setAutoCommit(false);

            itemStmt.setString(1, orderId);
            itemStmt.executeUpdate();

            orderStmt.setString(1, orderId);
            orderStmt.executeUpdate();

            conn.commit();
            System.out.println("✔ Xoá đơn hàng thành công.");

        } catch (Exception e) {
            System.err.println("❌ Lỗi xoá đơn hàng: " + e.getMessage());
        }
    }

    public void updateStatus(String orderId, String newStatus) {
        String sql = "UPDATE orders SET status = ? WHERE order_id = ?";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, newStatus);
            stmt.setString(2, orderId);
            stmt.executeUpdate();
            System.out.println("✔ Cập nhật trạng thái thành công.");
        } catch (Exception e) {
            System.err.println("❌ Lỗi cập nhật trạng thái: " + e.getMessage());
        }
    }

    public double calculateTotalRevenueByUserId(String userId) {
        double total = 0;
        String sql = "SELECT SUM(oi.price * oi.quantity) as revenue " +
                     "FROM orders o JOIN order_item oi ON o.order_id = oi.order_id " +
                     "WHERE o.user_id = ?";

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("revenue");
            }
        } catch (Exception e) {
            System.err.println("❌ Lỗi tính doanh thu: " + e.getMessage());
        }

        return total;
    }
}
