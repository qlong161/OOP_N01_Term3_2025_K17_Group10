import java.util.ArrayList;

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

