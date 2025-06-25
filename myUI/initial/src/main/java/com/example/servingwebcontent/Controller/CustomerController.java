package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.Customer;
import com.example.servingwebcontent.Database.CustomerAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customerlist")
public class CustomerController {

    private final CustomerAiven customerDB = new CustomerAiven();

    // Hiển thị danh sách khách hàng
    @GetMapping
    public String showCustomerList(Model model) {
        model.addAttribute("ListOfCustomer", customerDB.getAllCustomers());
        model.addAttribute("newCustomer", new Customer());
        return "customerlist";
    }

    // Thêm khách hàng mới
    @PostMapping("/add")
    public String addCustomer(@ModelAttribute("newCustomer") Customer customer) {
        customerDB.insertCustomer(customer);
        return "redirect:/customerlist";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String editCustomerForm(@PathVariable String id, Model model) {
        Customer existing = customerDB.findById(id);
        if (existing != null) {
            model.addAttribute("editCustomer", existing);
            return "edit-customer"; // Bạn cần tạo file HTML cho việc chỉnh sửa
        }
        return "redirect:/customerlist";
    }

    // Cập nhật sau khi chỉnh sửa
    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute("editCustomer") Customer updated) {
        customerDB.updateCustomer(updated);
        return "redirect:/customerlist";
    }

    // Xoá khách hàng
    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam String id) {
        customerDB.deleteCustomer(id);
        return "redirect:/customerlist";
    }
}
