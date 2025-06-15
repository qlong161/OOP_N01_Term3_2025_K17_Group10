import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserList {
    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        if (users.containsKey(user.getMaUser())) {
            System.out.println("Người dùng với ID " + user.getMaUser() + " đã tồn tại.");
            return;
        }
        users.put(user.getMaUser(), user);
        System.out.println("Đã thêm người dùng: " + user.getTenUser());
    }

    public boolean removeUser(String id) {
        if (users.remove(id) != null) {
            System.out.println("Đã xóa người dùng có ID: " + id);
            return true;
        } else {
            System.out.println("Không tìm thấy người dùng để xóa.");
            return false;
        }
    }

    public User getUserById(String id) {
        return users.get(id);
    }

    public double getTotalRevenue() {
    double total = 0;
    for (User user : users.values()) {
        OrderList orders = user.getOrderList();
        List<Order> processedOrders = orders.getProcessedOrderByDate(""); // truyền "" để lấy toàn bộ đã xử lý
        total += orders.calculateTotalRevenue(processedOrders);
    }
    return total;
}

    public void printTotalRevenue() {
    double total = getTotalRevenue();
    System.out.println("=== TỔNG DOANH THU TOÀN BỘ CỬA HÀNG ===");
    System.out.println("Tổng doanh thu từ tất cả người bán (đơn đã xử lý): " + total);
}    

    public void printAllUsers() {
        if (users.isEmpty()) {
            System.out.println("Danh sách người bán trống.");
            return;
        }
        for (User user : users.values()) {
            System.out.println("Mã: " + user.getMaUser());
            System.out.println("Tên: " + user.getTenUser());
            System.out.println("----------------------");
        }
    }
}
