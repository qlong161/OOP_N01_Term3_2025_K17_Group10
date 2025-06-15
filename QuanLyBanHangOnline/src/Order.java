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
        this.orderId = orderId;
        this.customer = customer;
        this.items = new HashMap<>();
        this.status = "chưa xử lý";
        this.date = LocalDateTime.now();
    }

    public Order(String orderId, Customer customer, String status, LocalDateTime date) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new HashMap<>();
        this.status = status;
        this.date = date;
    }

    public void addProduct(Product product, int quantity) {
        if (items.containsKey(product)) {
            items.put(product, items.get(product) + quantity);
        } else {
            items.put(product, quantity);
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void displayOrder() {
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
    }
}
