import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private Map<String, Product> products = new HashMap<>();

    public boolean addProduct(Product p) {
        try {
            if (p == null || p.getId() == null || p.getId().trim().isEmpty()) {
                throw new IllegalArgumentException("Sản phẩm hoặc ID không hợp lệ.");
            }
            if (products.containsKey(p.getId())) {
                System.out.println("Sản phẩm với ID " + p.getId() + " đã tồn tại.");
                return false;
            }
            products.put(p.getId(), p);
            System.out.println("Thêm sản phẩm thành công.");
            return true;
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
            return false;
        }
        finally {
            System.out.println("Kết thúc xử lý thêm sản phẩm.");
        }
    }

    public boolean removeProduct(String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("ID không hợp lệ.");
            }
            if (products.containsKey(id)) {
                products.remove(id);
                System.out.println("Đã xoá sản phẩm với ID: " + id);
                return true;
            } else {
                System.out.println("Không tìm thấy sản phẩm để xóa với ID: " + id);
                return false;
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi xoá sản phẩm: " + e.getMessage());
            return false;
        }
        finally {
            System.out.println("Kết thúc xử lý xóa sản phẩm.");
        }
    }

    public Product getProductById(String id) {
        try {
            return products.get(id);
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy sản phẩm theo ID: " + e.getMessage());
            return null;
        }
        finally {
            System.out.println("Kết thúc xử lý sản phẩm theo ID.");
        }
    }

    public void displayAllProducts() {
        try {
            if (products.isEmpty()) {
                System.out.println("Danh sách sản phẩm rỗng.");
            } else {
                for (Product p : products.values()) {
                    p.displayInfo();
                    System.out.println("-------------------------");
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị danh sách sản phẩm: " + e.getMessage());
        } finally {
            System.out.println("Kết thúc xử lý hiển thị sản phẩm.");
        }
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public void displayOutOfStockProducts() {
        try {
            boolean found = false;
            for (Product p : products.values()) {
                if (p.getQuantity() == 0) {
                    p.displayInfo();
                    System.out.println("-------------------------");
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Không có sản phẩm nào hết hàng.");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị sản phẩm hết hàng: " + e.getMessage());
        }
        finally {
            System.out.println("Kết thúc xử lý tất cả sản phẩm.");
        }
    }

    public void displayLowStockProducts() {
        try {
            boolean found = false;
            for (Product p : products.values()) {
                if (p.getQuantity() > 0 && p.getQuantity() <= 5) {
                    p.displayInfo();
                    System.out.println("-------------------------");
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Không có sản phẩm nào sắp hết hàng.");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị sản phẩm sắp hết hàng: " + e.getMessage());
        }
        finally {
            System.out.println("Kết thúc xử lý hiển thị sản phẩm hết hàng.");
        }
    }
}
