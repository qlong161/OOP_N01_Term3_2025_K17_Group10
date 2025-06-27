package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Database.OrderAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final OrderAiven orderDB = new OrderAiven();

    @GetMapping("/")
    public String showHome(Model model) {
        double totalRevenue = orderDB.calculateTotalRevenueAllUsers();
        model.addAttribute("totalRevenue", totalRevenue);
        return "home";
    }
}
