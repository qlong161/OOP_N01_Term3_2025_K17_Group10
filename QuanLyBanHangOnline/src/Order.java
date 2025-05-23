public class Order {
    private String orderId;
    private Customer customer;
    private Product product;
    private int quantity;
    private String status;

    public Order(String orderId, Customer customer, Product product, int quantity) {
        this.orderId = orderId;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.status = "chua xu ly";
    }

    public void displayOrder() {
        System.out.println("Ma don hang: " + orderId);
        customer.displayInfo();
        product.displayInfo();
        System.out.println("So luong hang dat: " + quantity);
        System.out.println("Tinh trang don hang: " + status);
    }
    public double calculateTotalPrice(){
        return quantity*product.getPrice();
    }
}