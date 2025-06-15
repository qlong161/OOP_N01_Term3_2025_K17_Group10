import java.util.ArrayList;
import java.util.List;

public class OrderList {
    private ArrayList<Order> ords = new ArrayList<>();
    public void addOrder(Order order) {
        ords.add(order);
        System.out.println("Đã thêm đơn hàng với ID: " + order.getOrderId());
    }

    public boolean editOrder(String orderId, Customer customer, Product product, int quantity, String status) {
        for (Order o : ords) {
            if (o.getOrderId().equals(orderId)) {
                o.setCustomer(customer);
                o.setProduct(product);
                o.setQuantity(quantity);
                o.setStatus(status);
                System.out.println("Cập nhật đơn hàng thành công với ID: " + orderId);
                return true;
            }
        }
        System.out.println("Không tìm thấy đơn hàng với ID: " + orderId);
        return false;
    }

    public boolean deleteOrder(String orderId) {
        for (int i = 0; i < ords.size(); i++) {
            if (ords.get(i).getOrderId().equals(orderId)) {
                ords.remove(i);
                System.out.println("Đã xoá đơn hàng với ID: " + orderId);
                return true;
            }
        }
        System.out.println("Không tìm thấy đơn hàng để xoá với ID: " + orderId);
        return false;
    }

    public List<Order> getProcessedOrderByDate(String date){
    List<Order> filtered = new ArrayList<>();
    for (Order o : ords) {
        if (o.getStatus().equalsIgnoreCase("đã xử lý") && o.getFormattedDate().equals(date)) {
            filtered.add(o);
        }
    }
    return filtered;
}


    public double calculateTotalRevenue(List<Order>orders) {
        double total = 0;
        for (Order o : orders){
            total += o.calculateTotalPrice();
        }
        return total;
    }

    public void printDailySummary(String date) {
        List<Order> orders = getProcessedOrderByDate(date); 
        if (orders.isEmpty()) {
            System.out.println("Không có đơn hàng đã xử lý trong ngày " + date);
        } else {
            for (Order o : orders) {
                o.displayOrder();
            }
            double revenue = calculateTotalRevenue(orders); 
            System.out.println("Tổng doanh thu ngày " + date + ": " + revenue);
        }
    }

    public Order getOrderById(String id) {
    for (Order o : ords) {
        if (o.getOrderId().equals(id)) {
            return o;
        }
    }
    return null;
}

    // cập nhật chức năng hiển thị đơn hàng lọc theo trạng thái đơn hàng
    public void displayOrdersByStatus(String status) {
    boolean found = false;
    for (Order o : ords) {
        if (o.getStatus().equalsIgnoreCase(status)) {
            o.displayOrder();
            System.out.println("Tổng tiền: " + o.calculateTotalPrice());
            System.out.println("---------------------------");
            found = true;
        }
    }
    if (!found) {
        System.out.println("Không có đơn hàng nào với trạng thái: " + status);
    }
}

    public void displayOrderList() {
        if (ords.isEmpty()) {
            System.out.println("Danh sách đơn hàng trống.");
        } else {
            System.out.println("===== Danh sách đơn hàng =====");
            for (Order order : ords) {
                order.displayOrder();
                System.out.println("Tổng tiền: " + order.calculateTotalPrice());
                System.out.println("-------------------------------");
            }
        }
    }
}