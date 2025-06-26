package com.example.servingwebcontent.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private String orderId;
    private Customer customer;
    private User user;  // 🔹 thêm user (người xử lý đơn hàng)
    private Map<Product, Integer> items;
    private String status;
    private LocalDateTime date;

    // Constructor đơn giản (mặc định user là null)
    public Order(String orderId, Customer customer) {
        try {
            this.orderId = orderId;
            this.customer = customer;
            this.user = null;
            this.items = new HashMap<>();
            this.status = "chưa xử lý";
            this.date = LocalDateTime.now();
        } catch (Exception e) {
            System.err.println("Lỗi khởi tạo đơn hàng: " + e.getMessage());
        } finally {
            System.out.println("Khởi tạo order đơn giản hoàn tất.");
        }
    }

    // Constructor đầy đủ
    public Order(String orderId, Customer customer, User user, String status, LocalDateTime date) {
        try {
            this.orderId = orderId;
            this.customer = customer;
            this.user = user;
            this.items = new HashMap<>();
            this.status = status;
            this.date = date;
        } catch (Exception e) {
            System.err.println("Lỗi khởi tạo đơn hàng với thông tin đầy đủ: " + e.getMessage());
        } finally {
            System.out.println("Khởi tạo order đầy đủ hoàn tất.");
        }
    }

    public void addProduct(Product product, int quantity) {
        try {
            if (product == null || quantity <= 0) {
                throw new IllegalArgumentException("Sản phẩm không hợp lệ hoặc số lượng <= 0");
            }
            items.put(product, items.getOrDefault(product, 0) + quantity);
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm sản phẩm vào đơn hàng: " + e.getMessage());
        } finally {
            System.out.println("Thêm sản phẩm hoàn tất.");
        }
    }

    // Getter & Setter cho user
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        try {
            if (user == null) throw new IllegalArgumentException("Người dùng không được null");
            this.user = user;
        } catch (Exception e) {
            System.err.println("Lỗi cập nhật người dùng xử lý đơn hàng: " + e.getMessage());
        } finally {
            System.out.println("Cập nhật người dùng hoàn tất.");
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        try {
            if (customer == null) throw new IllegalArgumentException("Khách hàng không được null");
            this.customer = customer;
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật khách hàng: " + e.getMessage());
        } finally {
            System.out.println("Cập nhật khách hàng hoàn tất.");
        }
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Product, Integer> items) {
        try {
            if (items == null) throw new IllegalArgumentException("Danh sách sản phẩm không được null");
            this.items = items;
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật danh sách sản phẩm: " + e.getMessage());
        } finally {
            System.out.println("Cập nhật danh sách sản phẩm hoàn tất.");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        try {
            this.status = status;
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật trạng thái đơn hàng: " + e.getMessage());
        } finally {
            System.out.println("Cập nhật trạng thái đơn hàng hoàn tất.");
        }
    }
    public Order(String orderId, Customer customer, String status, LocalDateTime date) {
        this.orderId = orderId;
        this.customer = customer;
        this.status = status;
        this.date = date;
        this.items = new HashMap<>(); // nếu chưa khởi tạo
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getFormattedDate() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return date.format(formatter);
        } catch (Exception e) {
            System.err.println("Lỗi định dạng ngày: " + e.getMessage());
            return "Không xác định";
        } finally {
            System.out.println("Định dạng ngày hoàn tất.");
        }
    }

    public double calculateTotalPrice() {
        double total = 0;
        try {
            for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                total += entry.getKey().getPrice() * entry.getValue();
            }
        } catch (Exception e) {
            System.err.println("Lỗi tính tổng tiền: " + e.getMessage());
        } finally {
            System.out.println("Tính tổng tiền hoàn tất.");
        }
        return total;
    }

    public void displayOrder() {
        try {
            System.out.println("Mã đơn hàng: " + orderId);
            System.out.println("Khách hàng: ");
            customer.displayInfo();

            System.out.println("Người xử lý đơn: " + (user != null ? user.getName() : "Chưa có"));

            System.out.println("Danh sách sản phẩm:");
            for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                Product p = entry.getKey();
                int qty = entry.getValue();
                System.out.printf("- %s | SL: %d | Đơn giá: %.2f | Thành tiền: %.2f\n",
                        p.getName(), qty, p.getPrice(), p.getPrice() * qty);
            }

            System.out.println("Tổng tiền: " + calculateTotalPrice());
            System.out.println("Trạng thái đơn hàng: " + status);
            System.out.println("Ngày đặt: " + getFormattedDate());
        } catch (Exception e) {
            System.err.println("Lỗi hiển thị đơn hàng: " + e.getMessage());
        } finally {
            System.out.println("Kết thúc xử lý hiển thị đơn hàng.");
        }
    }
}
