public class App {
    public static void main(String[] args) {
        Customer customer = new Customer("C001", "Nguyen Van A", "nguyenvana@example.com","mua truc tiep");
        Product product = new Product("P001", "Laptop", 1500.00, 10);
        Order order = new Order("O001", customer, product, 2);

        System.out.println("Thông tin khách hàng:");
        customer.displayInfo();
        System.out.println();
        System.out.println("Thông tin sản phẩm:");
        product.displayInfo();
        System.out.println();
        System.out.println("Thông tin đơn hàng:");
        order.displayOrder();
    }
}