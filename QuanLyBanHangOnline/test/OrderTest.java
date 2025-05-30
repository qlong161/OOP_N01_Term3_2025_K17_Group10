public class OrderTest {
    public static void test() {
        Customer customer = new Customer("C001", "Nguyen Van A", "a@gmail.com","mua tai quan");
        Product product = new Product("P001", "Chuột Gaming", 499000.0, 10);
        Order order = new Order("O001", customer, product, 2);

        order.displayOrder();
    }
}
