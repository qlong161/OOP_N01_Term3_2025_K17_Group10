import java.util.HashMap;
import java.util.Map;
public class ProductList {
    private Map<String, Product> products = new HashMap<>();
    
    public boolean addProduct(Product p) {
        if (products.containsKey(p.getId())) {
            System.out.println("Sản phẩm với ID " + p.getId() + " đã tồn tại.");
            return false;
        }
        products.put(p.getId(), p);
        return true;
    }
      

    public boolean removeProduct(String id) {
        if (products.containsKey(id)) {
            products.remove(id);
            return true;
        }
        System.out.println("Không tìm thấy sản phẩm để xóa với ID: " + id);
        return false;
    }
    public Product getProductById(String id) {
        return products.get(id);
    }

    public void displayAllProducts() {
        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm rỗng.");
        } else {
            for (Product p : products.values()) {
                p.displayInfo();  
            }
        }
    }
}
