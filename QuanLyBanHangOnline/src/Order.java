import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private String orderId;
    private Customer customer;
    private Map<Product, Integer> items;
    private String status;
    private LocalDateTime date;

    public Order(String orderId, Customer customer) {
        try {
            this.orderId = orderId;
            this.customer = customer;
            this.items = new HashMap<>();
            this.status = "chưa xử lý";
            this.date = LocalDateTime.now();
        } catch (Exception e) {
            System.err.println("Lỗi khởi tạo đơn hàng: " + e.getMessage());
        }
    }

    public Order(String orderId, Customer customer, String status, LocalDateTime date) {
        try {
            this.orderId = orderId;
            this.customer = customer;
            this.items = new HashMap<>();
            this.status = status;
            this.date = date;
        } catch (Exception e) {
            System.err.println("Lỗi khởi tạo đơn hàng với thông tin đầy đủ: " + e.getMessage());
        }
    }

    public void addProduct(Product product, int quantity) {
        try {
            if (product == null || quantity <= 0) {
                throw new IllegalArgumentException("Sản phẩm không hợp lệ hoặc số lượng <= 0");
            }
            if (items.containsKey(product)) {
                items.put(product, items.get(product) + quantity);
            } else {
                items.put(product, quantity);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm sản phẩm vào đơn hàng: " + e.getMessage());
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
            if (customer == null) {
                throw new IllegalArgumentException("Khách hàng không được null");
            }
            this.customer = customer;
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật khách hàng: " + e.getMessage());
        }
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Product, Integer> items) {
        try {
            if (items == null) {
                throw new IllegalArgumentException("Danh sách sản phẩm không được null");
            }
            this.items = items;
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật danh sách sản phẩm: " + e.getMessage());
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
        }
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
        }
        return total;
    }

    public void displayOrder() {
        try {
            System.out.println("Mã đơn hàng: " + orderId);
            System.out.println("Khách hàng: ");
            customer.displayInfo();

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
