package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Database.OrderAiven;
import com.example.servingwebcontent.Database.CustomerAiven;
import com.example.servingwebcontent.Database.ProductAiven;
import com.example.servingwebcontent.Database.UserAiven;
import com.example.servingwebcontent.Model.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderAiven orderDB = new OrderAiven();
    private final CustomerAiven customerDB = new CustomerAiven();
    private final ProductAiven productDB = new ProductAiven();
    private final UserAiven userDB = new UserAiven();

    // Giao diện chọn user để quản lý đơn hàng
    @GetMapping("/selectUser")
    public String selectUserForm() {
        return "order_select_user";
    }

    @PostMapping("/selectUser")
    public String submitUser(@RequestParam("userId") String userId) {
        return "redirect:/order/user/" + userId;
    }

    // Hiển thị danh sách đơn hàng của 1 user
    @GetMapping("/user/{userId}")
    public String showOrdersForUser(@PathVariable("userId") String userId, Model model) {
        List<Order> orders = orderDB.getOrdersByUserId(userId);
        double totalRevenue = orderDB.calculateTotalRevenueByUserId(userId);
        User user = userDB.findById(userId);

        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("products", productDB.findAll());
        model.addAttribute("customers", customerDB.getAllCustomers()); // ✅ Sửa chỗ này
        return "order_user_list";
    }

    // Thêm đơn hàng mới
    @PostMapping("/add")
    public String addOrder(@RequestParam("orderId") String orderId,
                           @RequestParam("customerId") String customerId,
                           @RequestParam("userId") String userId,
                           @RequestParam Map<String, String> allParams) {

        Customer customer = customerDB.findById(customerId);
        if (customer == null) return "redirect:/order/user/" + userId;

        User user = userDB.findById(userId);
        Order order = new Order(orderId, customer, user, "New", LocalDateTime.now());

        for (String key : allParams.keySet()) {
            if (key.startsWith("product_")) {
                String productId = key.substring("product_".length());
                try {
                    int quantity = Integer.parseInt(allParams.get(key));
                    if (quantity > 0) {
                        Product product = productDB.findById(productId);
                        if (product != null) {
                            order.addProduct(product, quantity);
                        }
                    }
                } catch (NumberFormatException ignored) {}
            }
        }

        orderDB.insertOrder(order, userId);
        return "redirect:/order/user/" + userId;
    }

    // Cập nhật trạng thái đơn hàng
    @PostMapping("/update")
    public String updateOrderStatus(@RequestParam("orderId") String orderId,
                                    @RequestParam("status") String status,
                                    @RequestParam("userId") String userId) {
        orderDB.updateStatus(orderId, status);
        return "redirect:/order/user/" + userId;
    }

    // Xoá đơn hàng
    @PostMapping("/delete")
    public String deleteOrder(@RequestParam("orderId") String orderId,
                              @RequestParam("userId") String userId) {
        orderDB.deleteOrder(orderId);
        return "redirect:/order/user/" + userId;
    }

    // Lọc đơn hàng theo trạng thái
    @GetMapping("/user/{userId}/filter")
    public String filterOrdersByStatus(@PathVariable("userId") String userId,
                                       @RequestParam("status") String status,
                                       Model model) {
        List<Order> filteredOrders = orderDB.getOrdersByUserId(userId).stream()
                .filter(o -> status.equalsIgnoreCase(o.getStatus()))
                .toList();

        double totalRevenue = orderDB.calculateTotalRevenueByUserId(userId);
        User user = userDB.findById(userId);

        model.addAttribute("user", user);
        model.addAttribute("orders", filteredOrders);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("products", productDB.findAll());
        model.addAttribute("customers", customerDB.getAllCustomers()); // ✅ Sửa chỗ này
        return "order_user_list";
    }
}
