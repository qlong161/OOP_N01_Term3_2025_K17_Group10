import models.*;

public class TestMain {
    public static void main(String[] args) {
        Product p = new Product("P01", "Laptop", 1500.0, 10);
        Customer c = new Customer("C01", "Nguyen Van A", "a@example.com");
        Order o = new Order("O01", c, p, 2);

        System.out.println("=== Product Info ===");
        p.displayInfo();
        System.out.println("=== Customer Info ===");
        c.displayInfo();
        System.out.println("=== Order Info ===");
        o.displayOrder();
    }
}