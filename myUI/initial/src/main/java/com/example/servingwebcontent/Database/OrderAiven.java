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

    public void insertOrder(Order order, String userId) {
        if (order.getUser() == null || order.getUser().getId() == null) {
            order.setUser(new User(userId, ""));
        }
        insertOrder(order);
    }

    public void insertOrder(Order order) {
        String insertOrderSQL = "INSERT INTO orders (id, customer_id, user_id, status, date) VALUES (?, ?, ?, ?, ?)";
        String insertItemSQL = "INSERT INTO order_item (order_id, product_id, quantity) VALUES (?, ?, ?)";
        String updateStockSQL = "UPDATE product SET quantity = quantity - ? WHERE id = ?";

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL);
            PreparedStatement itemStmt = conn.prepareStatement(insertItemSQL);
            PreparedStatement updateStockStmt = conn.prepareStatement(updateStockSQL)
        ) {
            conn.setAutoCommit(false);

            // 1. Insert Order
            orderStmt.setString(1, order.getOrderId());
            orderStmt.setString(2, order.getCustomer().getId());
            orderStmt.setString(3, order.getUser().getId());
            orderStmt.setString(4, order.getStatus());
            orderStmt.setTimestamp(5, Timestamp.valueOf(order.getDate()));
            orderStmt.executeUpdate();

            // 2. Insert Items + Update Stock
            for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
                String productId = entry.getKey().getId();
                int quantity = entry.getValue();

                // insert order item
                itemStmt.setString(1, order.getOrderId());
                itemStmt.setString(2, productId);
                itemStmt.setInt(3, quantity);
                itemStmt.addBatch();

                // update stock
                updateStockStmt.setInt(1, quantity);
                updateStockStmt.setString(2, productId);
                updateStockStmt.addBatch();
            }

            itemStmt.executeBatch();
            updateStockStmt.executeBatch();

            conn.commit();
            System.out.println("✔ Thêm đơn hàng và cập nhật tồn kho thành công.");
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi thêm đơn hàng: " + e.getMessage());
        }
    }

    public List<Order> getOrdersByUserId(String userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.id, o.customer_id, o.user_id, o.status, o.date, " +
                    "oi.product_id, oi.quantity, p.name AS product_name, p.price, p.quantity AS product_quantity, " +
                    "c.name AS customer_name, c.email, c.type " +
                    "FROM orders o " +
                    "LEFT JOIN order_item oi ON o.id = oi.order_id " +
                    "LEFT JOIN product p ON oi.product_id = p.id " +
                    "LEFT JOIN customer c ON o.customer_id = c.id " +
                    "WHERE o.user_id = ?";

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            Map<String, Order> orderMap = new HashMap<>();

            while (rs.next()) {
                String orderId = rs.getString("id");
                Order order = orderMap.get(orderId);

                if (order == null) {
                    String customerId = rs.getString("customer_id");
                    String status = rs.getString("status");
                    LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
                    Customer customer = new Customer(customerId, rs.getString("customer_name"), 
                                                    rs.getString("email"), rs.getString("type"));
                    User user = new User(userId, "");
                    order = new Order(orderId, customer, user, status, date);
                    orderMap.put(orderId, order);
                    orders.add(order);
                }

                String productId = rs.getString("product_id");
                if (productId != null) {
                    Product product = new Product(productId, rs.getString("product_name"), 
                                                 rs.getDouble("price"), rs.getInt("product_quantity"));
                    int quantity = rs.getInt("quantity");
                    order.addProduct(product, quantity);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi lấy đơn hàng theo user: " + e.getMessage());
        }

        return orders;
    }

    public void deleteOrder(String orderId) {
        String deleteItemsSQL = "DELETE FROM order_item WHERE order_id = ?";
        String deleteOrderSQL = "DELETE FROM orders WHERE id = ?";

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
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
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
        String sql = "SELECT SUM(p.price * oi.quantity) AS revenue " +
                     "FROM orders o " +
                     "JOIN order_item oi ON o.id = oi.order_id " +
                     "JOIN product p ON oi.product_id = p.id " +
                     "WHERE o.user_id = ? AND o.status = 'Completed'";

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

    public Order findById(String orderId) {
        String sql = "SELECT o.id, o.customer_id, o.user_id, o.status, o.date, " +
                    "oi.product_id, oi.quantity, p.name AS product_name, p.price, p.quantity AS product_quantity, " +
                    "c.name AS customer_name, c.email, c.type " +
                    "FROM orders o " +
                    "LEFT JOIN order_item oi ON o.id = oi.order_id " +
                    "LEFT JOIN product p ON oi.product_id = p.id " +
                    "LEFT JOIN customer c ON o.customer_id = c.id " +
                    "WHERE o.id = ?";

        Order order = null;

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (order == null) {
                    String customerId = rs.getString("customer_id");
                    String userId = rs.getString("user_id");
                    String status = rs.getString("status");
                    LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
                    Customer customer = new Customer(customerId, rs.getString("customer_name"), 
                                                    rs.getString("email"), rs.getString("type"));
                    User user = new User(userId, "");
                    order = new Order(orderId, customer, user, status, date);
                }

                String productId = rs.getString("product_id");
                if (productId != null) {
                    Product product = new Product(productId, rs.getString("product_name"), 
                                                 rs.getDouble("price"), rs.getInt("product_quantity"));
                    int quantity = rs.getInt("quantity");
                    order.addProduct(product, quantity);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi tìm đơn hàng theo ID: " + e.getMessage());
        }

        return order;
    }

    public double calculateTotalRevenueAllUsers() {
        double total = 0;
        String sql = "SELECT SUM(p.price * oi.quantity) AS revenue " +
                     "FROM orders o " +
                     "JOIN order_item oi ON o.id = oi.order_id " +
                     "JOIN product p ON oi.product_id = p.id " +
                     "WHERE o.status = 'Completed'";

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("revenue");
            }
        } catch (Exception e) {
            System.err.println("❌ Lỗi tính tổng doanh thu toàn bộ đơn hàng: " + e.getMessage());
        }

        return total;
    }
}