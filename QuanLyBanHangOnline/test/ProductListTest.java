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
                case 0:
                    System.out.println("Quay lại menu trước...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private static void showProductMenu() {
        System.out.println("\n===== QUẢN LÝ SẢN PHẨM =====");
        System.out.println("1. Thêm sản phẩm");
        System.out.println("2. Sửa sản phẩm");
        System.out.println("3. Xoá sản phẩm");
        System.out.println("4. Hiển thị danh sách sản phẩm");
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
        System.out.print("Nhập số lượng sản phẩm: ");
        int quantity = Integer.parseInt(sc.nextLine());

        Product product = new Product(id, name, price, quantity);
        if (productList.addProduct(product)) {
            System.out.println("Đã thêm sản phẩm thành công.");
        }
    }

    private static void editProduct() {
        System.out.print("Nhập ID sản phẩm cần sửa: ");
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

        System.out.print("Số lư
