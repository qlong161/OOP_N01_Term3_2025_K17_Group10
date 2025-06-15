import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static ProductList productList = new ProductList();
    private static CustomerList customerList = new CustomerList();
    private static OrderList orderList = new OrderList();
    private static String sellerName;

    public static void main(String[] args) {
        System.out.print("Nhap ten nguoi ban: ");
        sellerName = sc.nextLine();
        System.out.println("Chao mung, " + sellerName + "!");

        int choice;
        do {
            showMainMenu();
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    manageProducts();
                    break;
                case 2:
                    manageCustomers();
                    break;
                case 3:
                    manageOrders();
                    break;
                case 4:
                    
                    break;
                case 0:
                    System.out.println("Tam biet!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
                    break;
            }
        } while (choice != 0);
    }

    private static void showMainMenu() {
        System.out.println("\n===== MENU CHINH =====");
        System.out.println("1. Quan ly san pham");
        System.out.println("2. Quan ly khach hang");
        System.out.println("3. Quan ly don hang");
        System.out.println("4. Thong ke doanh thu trong ngay");
        System.out.println("0. Thoat");
        System.out.print("Nhap lua chon: ");
    }

    private static void manageProducts() {
        int choice;
        do {
            System.out.println("\n--- Quan ly san pham ---");
            System.out.println("1. Them san pham");
            System.out.println("2. Sua san pham");
            System.out.println("3. Xoa san pham");
            System.out.println("4. Hien thi danh sach");
            System.out.println("0. Quay lai");
            System.out.print("Lua chon: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Ten: ");
                    String name = sc.nextLine();
                    System.out.print("Gia: ");
                    double price = Double.parseDouble(sc.nextLine());
                    System.out.print("So luong: ");
                    int qty = Integer.parseInt(sc.nextLine());
                    productList.addProduct(new Product(id, name, price, qty));
                    break;
                case 2:
                    System.out.print("ID san pham: ");
                    id = sc.nextLine();
                    Product p = productList.getProductById(id);
                    if (p != null) {
                        System.out.print("Ten moi: ");
                        p.setName(sc.nextLine());
                        System.out.print("Gia moi: ");
                        p.setPrice(Double.parseDouble(sc.nextLine()));
                        System.out.print("So luong moi: ");
                        p.setQuantity(Integer.parseInt(sc.nextLine()));
                    } else {
                        System.out.println("Khong tim thay san pham.");
                    }
                    break;
                case 3:
                    System.out.print("ID san pham can xoa: ");
                    productList.removeProduct(sc.nextLine());
                    break;
                case 4:
                    productList.displayAllProducts();
                    break;
            }
        } while (choice != 0);
    }

    private static void manageCustomers() {
        int choice;
        do {
            System.out.println("\n--- Quan ly khach hang ---");
            System.out.println("1. Them khach hang");
            System.out.println("2. Sua khach hang");
            System.out.println("3. Xoa khach hang");
            System.out.println("4. Danh sach khach hang");
            System.out.println("0. Quay lai");
            System.out.print("Lua chon: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Ten: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Loai khach (truc tiep/tu xa): ");
                    String type = sc.nextLine();
                    customerList.addCustomer(new Customer(id, name, email, type));
                    break;
                case 2:
                    System.out.print("ID khach hang: ");
                    id = sc.nextLine();
                    System.out.print("Ten moi: ");
                    name = sc.nextLine();
                    System.out.print("Email moi: ");
                    email = sc.nextLine();
                    System.out.print("Loai moi: ");
                    type = sc.nextLine();
                    customerList.editCustomer(id, name, email, type);
                    break;
                case 3:
                    System.out.print("ID khach hang can xoa: ");
                    customerList.deleteCustomer(sc.nextLine());
                    break;
                case 4:
                    customerList.printCustomerList();
                    break;
            }
        } while (choice != 0);
    }

    private static void manageOrders() {
        int choice;
        do {
            System.out.println("\n--- Quan ly don hang ---");
            System.out.println("1. Tao don hang");
            System.out.println("2. Cap nhat don hang");
            System.out.println("3. Xoa don hang");
            System.out.println("4. Xem tat ca don hang");
            System.out.println("0. Quay lai");
            System.out.print("Lua chon: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Ma don: ");
                    String orderId = sc.nextLine();
                    System.out.print("ID khach hang: ");
                    String cid = sc.nextLine();
                    Customer c = customerList.getCustomerById(cid);
                    System.out.print("ID san pham: ");
                    String pid = sc.nextLine();
                    Product p = productList.getProductById(pid);
                    if (c != null && p != null) {
                        System.out.print("So luong: ");
                        int qty = Integer.parseInt(sc.nextLine());
                        if (qty <= p.getQuantity()) {
                            p.setQuantity(p.getQuantity() - qty);
                            Order o = new Order(orderId, c, p, qty);
                            orderList.addOrder(o);
                        } else {
                            System.out.println("So luong vuot qua kho.");
                        }
                    } else {
                        System.out.println("Khong tim thay san pham hoac khach hang.");
                    }
                    break;
                case 2:
                    System.out.print("Nhap ma don can sua: ");
                    String oid = sc.nextLine();
                    System.out.print("Ma khach hang moi: ");
                    Customer newCus = customerList.getCustomerById(sc.nextLine());
                    System.out.print("Ma san pham moi: ");
                    Product newPro = productList.getProductById(sc.nextLine());
                    System.out.print("So luong moi: ");
                    int qty = Integer.parseInt(sc.nextLine());
                    System.out.print("Trang thai moi: ");
                    String status = sc.nextLine();
                    orderList.editOrder(oid, newCus, newPro, qty, status);
                    break;
                case 3:
                    System.out.print("Nhap ma don can xoa: ");
                    orderList.deleteOrder(sc.nextLine());
                    break;
                case 4:
                    orderList.displayOrderList();
                    break;
            }
        } while (choice != 0);
    }

    private static void showDailyRevenue() {
        System.out.print("Nhap ngay (dd/MM/yyyy): ");
        String date = sc.nextLine();
        orderList.printDailySummary(date);
    }
}
