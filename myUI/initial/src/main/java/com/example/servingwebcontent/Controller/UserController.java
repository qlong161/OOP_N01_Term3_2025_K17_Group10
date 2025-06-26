package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.User;
import com.example.servingwebcontent.Database.UserAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/userlist")
public class UserController {

    private final UserAiven userDB = new UserAiven();

    // Hiển thị danh sách user
    @GetMapping
    public String showUserList(Model model) {
        List<User> users = userDB.getAllUsers();
        model.addAttribute("users", users);
        return "userlist";
    }

    // Thêm user mới
    @PostMapping("/add")
    public String addUser(@RequestParam("id") String id,
                          @RequestParam("name") String name) {
        User user = new User(id, name);
        userDB.insertUser(user);
        return "redirect:/userlist";
    }

    // Cập nhật user
    @PostMapping("/update")
    public String updateUser(@RequestParam("id") String id,
                             @RequestParam("name") String name) {
        User user = new User(id, name);
        userDB.updateUser(user);
        return "redirect:/userlist";
    }

    // Xoá user
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") String id) {
        userDB.deleteUser(id);
        return "redirect:/userlist";
    }
}
