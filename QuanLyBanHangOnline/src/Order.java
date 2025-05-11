public class Order {
    private String orderId;
    private Customer customer;
    private Product product;
    private int quantity;

    public Order(String orderId, Customer customer, Product product, int quantity) {
        this.orderId = orderId;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }

    public void displayOrder() {
        System.out.println("Ma don hang: " + orderId);
        customer.displayInfo();
        product.displayInfo();
        System.out.println("So luong hang dat: " + quantity);
    }
}