public class OrderListTest {
    public static void test() {
        Product p1 = new Product("P001", "Iphone 13", 20000000, 10);
        Product p2 = new Product("P002", "Laptop Dell", 15000000, 5);
        Customer c1 = new Customer("C001", "Nguyen Van A", "a@gmail.com", "VIP");
        Customer c2 = new Customer("C002", "Tran Thi B", "b@gmail.com", "Thường");

        OrderList orderList = new OrderList();
        Order o1 = new Order("O001", c1, p1, 2); // Đơn hàng 1
        Order o2 = new Order("O002", c2, p2, 1); // Đơn hàng 2
        orderList.addOrder(o1);
        orderList.addOrder(o2);
        
        System.out.println("đơn hàng sau khi thêm:");
        orderList.displayOrderList();
        System.out.println("\nCập nhật đơn hàng O001:");
        orderList.editOrder("O001", c1, p2, 3, "dang xu ly");
        System.out.println("\nđơn hàng sau khi cập nhật:");
        orderList.displayOrderList();

        System.out.println("\nXoá đơn hàng O002:");
        orderList.deleteOrder("O002");
        orderList.displayOrderList();
    }
}
