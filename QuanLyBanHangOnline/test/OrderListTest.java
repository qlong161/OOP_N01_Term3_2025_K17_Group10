import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderListTest {
    public static void test(){
        // Tạo sản phẩm
        Product p1 = new Product("P001", "Iphone 13", 20000000, 10);
        Product p2 = new Product("P002", "Laptop Dell", 15000000, 5);

        // Tạo khách hàng
        Customer c1 = new Customer("C001", "Nguyen Van A", "a@gmail.com", "Mua trực tiếp");
        Customer c2 = new Customer("C002", "Tran Thi B", "b@gmail.com", "Đặt online");

        // Tạo danh sách đơn hàng
        OrderList orderList = new OrderList();

        // Tạo đơn hàng
        Order o1 = new Order("O001", c1, p1, 2, "đã xử lý");
        Order o2 = new Order("O002", c2, p2, 1, "đã xử lý");
        Order o3 = new Order("O003", c2, p2, 1, "chưa xử lý");

        // Thêm đơn hàng
        orderList.addOrder(o1);
        orderList.addOrder(o2);
        orderList.addOrder(o3);

        System.out.println("\n>>> Sau khi thêm đơn hàng:");
        orderList.displayOrderList();

        // Sửa đơn hàng O001
        System.out.println("\n>>> Cập nhật đơn hàng O001:");
        orderList.editOrder("O001", c1, p2, 3, "đã xử lý");
        orderList.displayOrderList();

        // Xoá đơn hàng O002
        System.out.println("\n>>> Xoá đơn hàng O002:");
        orderList.deleteOrder("O002");
        orderList.displayOrderList();

        // In thống kê theo ngày
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("\n>>> Thống kê đơn hàng đã xử lý ngày hôm nay: " + today);
        orderList.printDailySummary(today);
    }
}
