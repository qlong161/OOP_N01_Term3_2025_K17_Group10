import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderList {
    private ArrayList<Order> ords = new ArrayList<>();

    public void addOrder(Order order) {
        try {
            ords.add(order);
            System.out.println("Đã thêm đơn hàng với ID: " + order.getOrderId());
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm đơn hàng: " + e.getMessage());
        }
    }

    public double getRevenueOfToday() {
        double total = 0;
        try {
            LocalDate today = LocalDate.now();
            for (Order o : ords) {
                if (o.getStatus().equalsIgnoreCase("đã xử lý") &&
                    o.getDate().toLocalDate().equals(today)) {
                    total += o.calculateTotalPrice();
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi tính doanh thu hôm nay: " + e.getMessage());
        }
        return total;
    }

    public boolean editOrder(String orderId, Customer customer, String status) {
        try {
            for (Order o : ords) {
                if (o.getOrderId().equals(orderId)) {
                    o.setCustomer(customer);
                    o.setStatus(status);
                    System.out.println("Đã cập nhật đơn hàng với ID: " + orderId);
                    return true;
                }
            }
            System.out.println("Không tìm thấy đơn hàng với ID: " + orderId);
        } catch (Exception e) {
            System.err.println("Lỗi khi sửa đơn hàng: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteOrder(String orderId) {
        try {
            for (int i = 0; i < ords.size(); i++) {
                if (ords.get(i).getOrderId().equals(orderId)) {
                    ords.remove(i);
                    System.out.println("Đã xoá đơn hàng với ID: " + orderId);
                    return true;
                }
            }
            System.out.println("Không tìm thấy đơn hàng để xoá với ID: " + orderId);
        } catch (Exception e) {
            System.err.println("Lỗi khi xoá đơn hàng: " + e.getMessage());
        }
        return false;
    }

    public List<Order> getProcessedOrderByDate(String date) {
        List<Order> filtered = new ArrayList<>();
        try {
            for (Order o : ords) {
                if (o.getStatus().equalsIgnoreCase("đã xử lý") &&
                    (date.isEmpty() || o.getFormattedDate().equals(date))) {
                    filtered.add(o);
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lọc đơn hàng theo ngày: " + e.getMessage());
        }
        return filtered;
    }

    public double calculateTotalRevenue(List<Order> orders) {
        double total = 0;
        try {
            for (Order o : orders) {
                total += o.calculateTotalPrice();
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi tính doanh thu: " + e.getMessage());
        }
        return total;
    }

    public double calculateTotalRevenue() {
        return calculateTotalRevenue(ords);
    }

    public void printDailySummary(String date) {
        try {
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
        } catch (Exception e) {
            System.err.println("Lỗi khi in tổng kết ngày: " + e.getMessage());
        } finally {
            System.out.println("Đã xử lý xong tổng kết ngày.");
        }
    }

    public Order getOrderById(String id) {
        try {
            for (Order o : ords) {
                if (o.getOrderId().equals(id)) {
                    return o;
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi tìm đơn hàng theo ID: " + e.getMessage());
        }
        return null;
    }

    public void displayOrdersByStatus(String status) {
        boolean found = false;
        try {
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
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị đơn hàng theo trạng thái: " + e.getMessage());
        }
    }

    public void displayOrderList() {
        try {
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
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị danh sách đơn hàng: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất hiển thị danh sách đơn hàng.");
        }
    }
}
