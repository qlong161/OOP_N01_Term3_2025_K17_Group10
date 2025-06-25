package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Database.ProductAiven;
import com.example.servingwebcontent.Model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productlist")
public class ProductController {

    private final ProductAiven db = new ProductAiven();

    // 1. Hiển thị toàn bộ sản phẩm + form thêm
    @GetMapping("")
    public String showAllProducts(Model model) {
        model.addAttribute("ListOfProduct", db.getAllProducts());
        model.addAttribute("newProduct", new Product());
        return "productlist";
    }

    // 2. Thêm sản phẩm mới
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("newProduct") Product product) {
        db.insertProduct(product); // chỉ thêm mới, không cập nhật
        return "redirect:/productlist";
    }

    // 3. Xoá sản phẩm
    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("id") String id) {
        db.deleteProduct(id);
        return "redirect:/productlist";
    }

    // 4. Sửa sản phẩm (hiển thị lại trang với form đã điền sẵn)
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") String id, Model model) {
        Product product = db.findById(id);
        model.addAttribute("newProduct", product); // gán sản phẩm vào form
        model.addAttribute("ListOfProduct", db.getAllProducts()); // load danh sách
        return "productlist";
    }

    // 5. Hiển thị sản phẩm hết hàng
    @GetMapping("/outofstock")
    public String showOutOfStock(Model model) {
        model.addAttribute("ListOfProduct", db.getOutOfStockProducts());
        model.addAttribute("newProduct", new Product());
        return "productlist";
    }

    // 6. Hiển thị sản phẩm gần hết hàng
    @GetMapping("/lowstock")
    public String showLowStock(Model model) {
        model.addAttribute("ListOfProduct", db.getLowStockProducts());
        model.addAttribute("newProduct", new Product());
        return "productlist";
    }
}
