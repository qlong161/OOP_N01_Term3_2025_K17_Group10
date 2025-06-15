import java.util.Scanner;

public class ProductListTest {
    private static Scanner sc = new Scanner(System.in);
    private static ProductList productList = new ProductList();

    public static void runProductMenu() {
        int choice;
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
    }

    private static void editProduct() {
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
    }

    private static void deleteProduct() {
        System.out.print("Nhập ID sản phẩm cần xoá: ");
        String id = sc.nextLine();

        if (productList.removeProduct(id)) {
            System.out.println("Đã xoá sản phẩm khỏi kho.");
        }
    }

    private static void displayOutOfStockProducts() {
        System.out.println("\n===== HÀNG HẾT =====");
        productList.displayOutOfStockProducts();
    }

    private static void displayLowStockProducts() {
        System.out.println("\n===== HÀNG GẦN HẾT =====");
        productList.displayLowStockProducts();
    }
}
