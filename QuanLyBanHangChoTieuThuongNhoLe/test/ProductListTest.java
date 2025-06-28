import java.util.Scanner;

import ObjectGeneral.Product;
import ObjectList.ProductList;

public class ProductListTest {
    private static Scanner sc = new Scanner(System.in);
    private static ProductList productList = new ProductList();

    public static void runProductMenu() {
        int choice;
        try {
            do {
                showProductMenu();
                choice = getChoice();

                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        editProduct();
                        break;
                    case 3:
                        deleteProduct();
                        break;
                    case 4:
                        productList.displayAllProducts();
                        break;
                    case 5:
                        displayOutOfStockProducts();  // Hàng hết kho
                        break;
                    case 6:
                        displayLowStockProducts();   // Hàng gần hết
                        break;
                    case 0:
                        System.out.println("Quay lại menu trước...");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } while (choice != 0);
        } catch (Exception e) {
            System.err.println("Lỗi trong menu sản phẩm: " + e.getMessage());
        } finally {
            System.out.println("Đã thoát khỏi menu quản lý kho hàng.");
        }
    }

    private static void showProductMenu() {
        System.out.println("\n===== QUẢN LÝ KHO HÀNG =====");
        System.out.println("1. Nhập thêm hàng (thêm sản phẩm)");
        System.out.println("2. Cập nhật thông tin hàng");
        System.out.println("3. Xoá hàng khỏi kho");
        System.out.println("4. Hiển thị toàn bộ hàng trong kho");
        System.out.println("5. Xem hàng đã hết");
        System.out.println("6. Xem hàng gần hết");
        System.out.println("0. Quay lại");
        System.out.print("Nhập lựa chọn: ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void addProduct() {
        try {
            System.out.print("Nhập mã sản phẩm: ");
            String id = sc.nextLine();
            System.out.print("Nhập tên sản phẩm: ");
            String name = sc.nextLine();
            System.out.print("Nhập giá sản phẩm: ");
            double price = Double.parseDouble(sc.nextLine());
            System.out.print("Nhập số lượng nhập kho: ");
            int quantity = Integer.parseInt(sc.nextLine());

            Product product = new Product(id, name, price, quantity);
            if (productList.addProduct(product)) {
                System.out.println("Đã nhập hàng thành công.");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
        } finally {
            System.out.println("Kết thúc thao tác sản phẩm.");
        }
    }

    private static void editProduct() {
        try {
            System.out.print("Nhập ID sản phẩm cần cập nhật: ");
            String id = sc.nextLine();

            Product product = productList.getProductById(id);
            if (product == null) {
                System.out.println("Không tìm thấy sản phẩm với ID: " + id);
                return;
            }

            System.out.print("Tên mới: ");
            product.setName(sc.nextLine());

            System.out.print("Giá mới: ");
            product.setPrice(Double.parseDouble(sc.nextLine()));

            System.out.print("Số lượng mới (thay thế toàn bộ): ");
            product.setQuantity(Integer.parseInt(sc.nextLine()));

            System.out.println("Cập nhật thành công.");
        } catch (Exception e) {
            System.err.println("Lỗi khi chỉnh sửa sản phẩm: " + e.getMessage());
        } finally {
            System.out.println("Kết thúc thao tác chỉnh sửa sản phẩm.");
        }
    }

    private static void deleteProduct() {
        try {
            System.out.print("Nhập ID sản phẩm cần xoá: ");
            String id = sc.nextLine();

            if (productList.removeProduct(id)) {
                System.out.println("Đã xoá sản phẩm khỏi kho.");
            } else {
                System.out.println("Không tìm thấy sản phẩm để xoá.");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi xoá sản phẩm: " + e.getMessage());
        } finally {
            System.out.println("Kết thúc thao tác xoá sản phẩm.");
        }
    }

    private static void displayOutOfStockProducts() {
        try {
            System.out.println("\n===== HÀNG HẾT =====");
            productList.displayOutOfStockProducts();
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị sản phẩm hết hàng: " + e.getMessage());
        } finally {
            System.out.println("Kết thúc xử lý hiển thị hết hàng.");
        }
    }

    private static void displayLowStockProducts() {
        try {
            System.out.println("\n===== HÀNG GẦN HẾT =====");
            productList.displayLowStockProducts();
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị sản phẩm sắp hết hàng: " + e.getMessage());
        } finally {
            System.out.println("Kết thúc xử lý hiển thị hàng gần hết.");
        }
    }
}
