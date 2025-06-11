public class OrderListTest {
    public static void test() {
        Product p1 = new Product("P001", "Iphone 13", 20000000, 10);
        Product p2 = new Product("P002", "Laptop Dell", 15000000, 5);
        Customer c1 = new Customer("C001", "Nguyen Van A", "a@gmail.com", "Mua trực tiếp");
        Customer c2 = new Customer("C002", "Tran Thi B", "b@gmail.com", "Đặt Online");

        OrderList orderList = new OrderList();
        Order o1 = new Order("O001", c1, p1, 2, "đã xử lý"); // Đơn hàng 1
        Order o2 = new Order("O002", c2, p2, 1, "đã xử lý"); // Đơn hàng 2
        Order o3 = new Order("O003", c2, p2, 1, "chưa xử lý");

        orderList.addOrder(o1);
        orderList.addOrder(o2);

        System.out.println("Đơn hàng sau khi thêm:");
        orderList.displayOrderList();

        System.out.println("\nCập nhật đơn hàng O001:");
        orderList.editOrder("O001", c1, p2, 3, "đang xử lý");

        System.out.println("\nĐơn hàng sau khi cập nhật:");
        orderList.displayOrderList();

        System.out.println("\nXoá đơn hàng O002:");
        orderList.deleteOrder("O002");

        orderList.displayOrderList();

        System.out.println("\nThông tin đơn hàng O001:");
        o1.displayOrder();
       // o1.getProcessedOrderByDate();

        // Test hàm lọc đơn hàng theo ngày nếu có
        // System.out.println(orderList.getProcessedOrderByDate("2025-06-10"));
    }

    public static void main(String[] args) {
        test(); // Gọi test() từ main
    }
}
