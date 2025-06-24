package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Database.ProductAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.servingwebcontent.Model.Product;

@Controller
public class ProductController {

    private final ProductAiven db = new ProductAiven();

    // 1. Hiển thị toàn bộ sản phẩm
    @GetMapping("/productlist")
    public String showAllProducts(Model model) {
        model.addAttribute("ListOfProduct", db.getAllProducts());
        model.addAttribute("newProduct", new Product());
        return "productlist";
    }

    // 2. Thêm sản phẩm mới
    @PostMapping("/productlist/add")
    public String addProduct(@ModelAttribute("newProduct") Product product) {
        db.insertProduct(product);
        return "redirect:/productlist";
    }

    // 3. Xoá sản phẩm
    @PostMapping("/productlist/delete")
    public String deleteProduct(@RequestParam("id") String id) {
        db.deleteProduct(id);
        return "redirect:/productlist";
    }

    // 4. Cập nhật sản phẩm
    @PostMapping("/productlist/update")
    public String updateProduct(@ModelAttribute Product product) {
        db.updateProduct(product);
        return "redirect:/productlist";
    }

    // 5. Hiển thị sản phẩm đã hết hàng (quantity = 0)
    @GetMapping("/productlist/outofstock")
    public String showOutOfStock(Model model) {
        model.addAttribute("ListOfProduct", db.getOutOfStockProducts());
        return "productlist";
    }

    // 6. Hiển thị sản phẩm gần hết hàng (quantity <= 5)
    @GetMapping("/productlist/lowstock")
    public String showLowStock(Model model) {
        model.addAttribute("ListOfProduct", db.getLowStockProducts());
        return "productlist";
    }

    // (Tùy chọn) Lấy thông tin sản phẩm theo ID để hiển thị trong form sửa
    @GetMapping("/productlist/edit/{id}")
    public String editProductForm(@PathVariable("id") String id, Model model) {
        Product product = db.findById(id);
        model.addAttribute("productToEdit", product);
        return "editproduct"; // Tạo file HTML riêng nếu bạn cần form sửa riêng biệt
    }
}
