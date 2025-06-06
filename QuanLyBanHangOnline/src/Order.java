import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private String orderId;
    private Customer customer;
    private Product product;
    private int quantity;
    private String status;
    private LocalDateTime date;

    public Order(String orderId, Customer customer, Product product, int quantity) {
        this.orderId = orderId;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.status = "chua xu ly";
        this.date = LocalDateTime.now();
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public String getFormattedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        return date.format(formatter); // Trả về thời gian đã định dạng
    }

    public void displayOrder() {
        System.out.println("Ma don hang: " + orderId);
        customer.displayInfo();
        product.displayInfo();
        System.out.println("So luong hang dat: " + quantity);
        System.out.println("Tinh trang don hang: " + status);
        System.out.println("Thoi gian dat hang: " + getFormattedDate());
    }

    public double calculateTotalPrice() {
        return quantity * product.getPrice();
    }
}
