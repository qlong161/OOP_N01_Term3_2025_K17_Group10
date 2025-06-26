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

    // 1. Hiển thị danh sách và form (mặc định form trống để thêm mới)
    @GetMapping("")
    public String showAllProducts(Model model) {
        model.addAttribute("ListOfProduct", db.getAllProducts());
        model.addAttribute("newProduct", new Product()); // dùng để thêm mới hoặc cập nhật
        return "productlist";
    }

    // 2. Thêm hoặc cập nhật sản phẩm
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("newProduct") Product product) {
        Product existing = db.findById(product.getId());
        if (existing == null) {
            db.insertProduct(product); // sản phẩm chưa có → thêm mới
        } else {
            db.updateProduct(product); // sản phẩm đã có → cập nhật
        }
        return "redirect:/productlist";
    }

    // 3. Xoá sản phẩm
    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("id") String id) {
        db.deleteProduct(id);
        return "redirect:/productlist";
    }

    // 4. Sửa sản phẩm (form hiển thị lại dữ liệu sản phẩm cần sửa)
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") String id, Model model) {
        Product product = db.findById(id);
        model.addAttribute("newProduct", product);
        model.addAttribute("ListOfProduct", db.getAllProducts());
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
