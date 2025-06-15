import java.util.Scanner;

public class OrderListTest {
    private static CustomerList customerList = new CustomerList();
    private static ProductList productList = new ProductList();
    private static OrderList orderList = new OrderList();

    // ==== OVERLOAD METHOD DÙNG CHO USERLISTTEST GỌI ====
    public static void runOrderMenu(OrderList oList, CustomerList cList, ProductList pList) {
        orderList = oList;
        customerList = cList;
        productList = pList;

        runOrderMenu(); // gọi lại hàm gốc
    }

    public static void runOrderMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;

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

    private static void addOrder(OrderList orderList, Scanner sc) {
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

        System.out.print("Nhập mã sản phẩm: ");
        String productId = sc.nextLine();
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

        Order order = new Order(orderId, customer, product, qty);
        orderList.addOrder(order);
    }

    private static void editOrder(OrderList orderList, Scanner sc) {
        System.out.print("Nhập ID đơn hàng cần sửa: ");
        String editId = sc.nextLine();
        Order editOrder = orderList.getOrderById(editId);

        if (editOrder == null) {
            System.out.println("Không tìm thấy đơn hàng.");
            return;
        }

        boolean editing = true;
        while (editing) {
            System.out.println("\n=== Chọn thông tin cần sửa ===");
            System.out.println("1. Sửa khách hàng");
            System.out.println("2. Sửa sản phẩm");
            System.out.println("3. Sửa số lượng");
            System.out.println("4. Sửa trạng thái");
            System.out.println("5. Quay lại");

            System.out.print("Lựa chọn: ");
            String editChoice = sc.nextLine();

            switch (editChoice) {
                case "1":
                    customerList.printCustomerList();
                    System.out.print("Nhập ID khách hàng mới: ");
                    String newCid = sc.nextLine();
                    Customer newC = customerList.getCustomerById(newCid);
                    if (newC != null) {
                        editOrder.setCustomer(newC);
                        System.out.println("Đã cập nhật khách hàng mới.");
                    } else {
                        System.out.println("Không tìm thấy khách hàng.");
                    }
                    break;

                case "2":
                    productList.displayAllProducts();
                    System.out.print("Nhập ID sản phẩm mới: ");
                    String newPid = sc.nextLine();
                    Product newP = productList.getProductById(newPid);
                    if (newP != null) {
                        editOrder.setProduct(newP);
                        System.out.println("Đã cập nhật sản phẩm mới.");
                    } else {
                        System.out.println("Không tìm thấy sản phẩm.");
                    }
                    break;

                case "3":
                    System.out.print("Nhập số lượng mới: ");
                    int newQty = Integer.parseInt(sc.nextLine());
                    editOrder.setQuantity(newQty);
                    System.out.println("Đã cập nhật số lượng.");
                    break;

                case "4":
                    System.out.print("Nhập trạng thái mới: ");
                    String newStatus = sc.nextLine();
                    editOrder.setStatus(newStatus);
                    System.out.println("Đã cập nhật trạng thái.");
                    break;

                case "5":
                    editing = false;
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
