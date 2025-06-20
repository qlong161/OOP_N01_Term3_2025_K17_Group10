import java.util.Scanner;

public class OrderListTest {
    private static CustomerList customerList = new CustomerList();
    private static ProductList productList = new ProductList();
    private static OrderList orderList = new OrderList();

    public static void runOrderMenu(OrderList oList, CustomerList cList, ProductList pList) {
        try {
        orderList = oList;
        customerList = cList;
        productList = pList;
        runOrderMenu();
    }
    catch(Exception e){
        System.err.println("Lỗi khi chạy menu đơn hàng: " + e.getMessage());
    }
    finally {
        System.out.println("Kết thúc xử lý runOderMenu với tham số.");
    }
  }
    public static void runOrderMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
    try {
        do {
            System.out.println("\n==== Quản lý đơn hàng ====");
            System.out.println("1. Thêm đơn hàng");
            System.out.println("2. Sửa thông tin đơn hàng");
            System.out.println("3. Xóa đơn hàng");
            System.out.println("4. Hiển thị danh sách đơn hàng");
            System.out.println("5. In đơn hàng theo trạng thái");
            System.out.println("6. Hiện tổng doanh thu");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addOrder(orderList, sc);
                    break;
                case 2:
                    editOrder(orderList, sc);
                    break;
                case 3:
                    System.out.print("Nhập mã đơn hàng cần xóa: ");
                    String delId = sc.nextLine();
                    orderList.deleteOrder(delId);
                    break;
                case 4:
                    orderList.displayOrderList();
                    break;
                case 5:
                    System.out.print("Nhập trạng thái muốn lọc: ");
                    String status = sc.nextLine();
                    orderList.displayOrdersByStatus(status);
                    break;
                case 6:
                    double total = orderList.calculateTotalRevenue();
                    System.out.printf("Tổng doanh thu: %.2f\n", total);
                    break;
                case 0:
                    System.out.println("Quay lại menu chính...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 0);
    }
    catch(Exception e){
        System.err.println("Lỗi trong menu đơn hàng: " + e.getMessage());
    }
    finally {
        System.out.println("Kết thúc xử lý runOderMenu.");
    }
}
    private static void addOrder(OrderList orderList, Scanner sc) {
        try {
        System.out.print("Nhập mã đơn hàng: ");
        String orderId = sc.nextLine();

        System.out.print("Nhập mã khách hàng: ");
        String customerId = sc.nextLine();
        Customer customer = customerList.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Khách hàng không tồn tại. Tạo mới khách hàng.");
            System.out.print("Tên khách hàng: ");
            String name = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Kiểu khách: ");
            String type = sc.nextLine();
            customer = new Customer(customerId, name, email, type);
            customerList.addCustomer(customer);
        }

        Order order = new Order(orderId, customer);
        boolean addingProducts = true;
        while (addingProducts) {
            System.out.print("Nhập mã sản phẩm (hoặc 'x' để kết thúc): ");
            String productId = sc.nextLine();
            if (productId.equalsIgnoreCase("x")) break;

            Product product = productList.getProductById(productId);
            if (product == null) {
                System.out.println("Sản phẩm không tồn tại. Tạo mới sản phẩm.");
                System.out.print("Tên sản phẩm: ");
                String pname = sc.nextLine();
                System.out.print("Giá: ");
                double price = sc.nextDouble();
                System.out.print("Số lượng tồn: ");
                int stock = sc.nextInt();
                sc.nextLine(); 
                product = new Product(productId, pname, price, stock);
                productList.addProduct(product);
            }

            System.out.print("Số lượng đặt: ");
            int qty = sc.nextInt();
            sc.nextLine();

            if (!product.isAvailable(qty)) {
                System.out.println("Không đủ hàng trong kho. Tồn kho hiện tại: " + product.getQuantity());
                continue;
            }

            product.reduceQuantity(qty);
            order.addProduct(product, qty);
        }

        orderList.addOrder(order);
    }
    catch(Exception e){
        System.err.println("Lỗi khi thêm đơn hàng: " +e.getMessage()); 
    }
    finally {
        System.out.println("Kết thúc xử lý thêm đơn hàng.");
    }
 }
    private static void editOrder(OrderList orderList, Scanner sc) {
        try {
        System.out.print("Nhập ID đơn hàng cần sửa: ");
        String editId = sc.nextLine();
        Order editOrder = orderList.getOrderById(editId);

        if (editOrder == null) {
            System.out.println("Không tìm thấy đơn hàng.");
            return;
        }

        boolean editing = true;
        while (editing) {
            System.out.println("\n=== Chỉnh sửa đơn hàng: " + editId + " ===");
            System.out.println("1. Xem danh sách sản phẩm trong đơn");
            System.out.println("2. Thêm sản phẩm mới vào đơn");
            System.out.println("3. Xoá sản phẩm khỏi đơn");
            System.out.println("4. Sửa trạng thái đơn hàng");
            System.out.println("0. Quay lại");

            System.out.print("Chọn: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    for (var entry : editOrder.getItems().entrySet()) {
                        Product p = entry.getKey();
                        int qty = entry.getValue();
                        System.out.printf("- %s | SL: %d | Đơn giá: %.2f\n", p.getName(), qty, p.getPrice());
                    }
                    break;
                case "2":
                    System.out.print("Nhập mã sản phẩm cần thêm: ");
                    String pid = sc.nextLine();
                    Product prod = productList.getProductById(pid);
                    if (prod == null) {
                        System.out.println("Sản phẩm chưa tồn tại. Nhập thông tin mới.");
                        System.out.print("Tên sản phẩm: ");
                        String pname = sc.nextLine();
                        System.out.print("Giá: ");
                        double price = sc.nextDouble();
                        System.out.print("Số lượng tồn: ");
                        int stock = sc.nextInt(); sc.nextLine();
                        prod = new Product(pid, pname, price, stock);
                        productList.addProduct(prod);
                    }

                    System.out.print("Nhập số lượng: ");
                    int qty = sc.nextInt(); sc.nextLine();

                    if (!prod.isAvailable(qty)) {
                        System.out.println("Không đủ hàng trong kho. Tồn kho hiện tại: " + prod.getQuantity());
                        break;
                    }

                    prod.reduceQuantity(qty);
                    editOrder.addProduct(prod, qty);
                    System.out.println("Đã thêm sản phẩm vào đơn.");
                    break;
                case "3":
                    System.out.print("Nhập mã sản phẩm cần xoá khỏi đơn: ");
                    String removeId = sc.nextLine();
                    boolean found = false;
                    for (Product p : editOrder.getItems().keySet()) {
                        if (p.getId().equals(removeId)) {
                            editOrder.getItems().remove(p);
                            System.out.println("Đã xoá sản phẩm khỏi đơn.");
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Không tìm thấy sản phẩm trong đơn.");
                    break;
                case "4":
                    System.out.print("Nhập trạng thái mới: ");
                    String newStatus = sc.nextLine();
                    editOrder.setStatus(newStatus);
                    System.out.println("Đã cập nhật trạng thái.");
                    break;
                case "0":
                    editing = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
    catch(Exception e){
        System.err.println("Lỗi khi chỉnh sửa đơn hàng: " +e.getMessage());
    }
    finally {
        System.out.println("Kết thúc xử lý chỉnh sửa đơn hàng.");
    }
  }
}
