package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Database.ProductAiven;
import com.example.servingwebcontent.Model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class ProductController {

    private final ProductAiven db = new ProductAiven();

    // 1. Hiển thị toàn bộ sản phẩm + form thêm/sửa
    @GetMapping("/")
    public String showAllProducts(Model model) {
        model.addAttribute("ListOfProduct", db.getAllProducts());
        if (!model.containsAttribute("newProduct")) {
            model.addAttribute("newProduct", new Product());
        }
        return "productlist";
    }

    // 2. Lưu sản phẩm (thêm mới hoặc cập nhật nếu đã tồn tại)
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("newProduct") Product product) {
        Product existing = db.findById(product.getId());
        if (existing != null) {
            db.updateProduct(product); // nếu có ID => cập nhật
        } else {
            db.insertProduct(product); // nếu không có => thêm mới
        }
        return "redirect:/";
    }

    // 3. Xoá sản phẩm
    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("id") String id) {
        db.deleteProduct(id);
        return "redirect:/";
    }

    // 4. Nhấn nút "Sửa" -> hiển thị lại trang với form được điền sẵn dữ liệu
    @PostMapping("/edit")
    public String editProduct(@RequestParam("id") String id, Model model) {
        Product product = db.findById(id);
        model.addAttribute("newProduct", product); // đổ dữ liệu vào form
        model.addAttribute("ListOfProduct", db.getAllProducts()); // load lại danh sách
        return "productlist"; // render lại trang với form có dữ liệu
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
