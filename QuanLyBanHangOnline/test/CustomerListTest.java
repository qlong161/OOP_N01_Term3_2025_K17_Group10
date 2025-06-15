import java.util.Scanner;

public class CustomerListTest {
    private static Scanner sc = new Scanner(System.in);
    private static CustomerList customerList = new CustomerList();

    public static void runCustomerMenu() {
        int choice;
        do {
            showCustomerMenu();
            choice = getChoice();

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    editCustomer();
                    break;
                case 3:
                    deleteCustomer();
                    break;
                case 4:
                    customerList.printCustomerList();
                    break;
                case 0:
                    System.out.println("Quay lại menu trước...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private static void showCustomerMenu() {
        System.out.println("\n===== QUẢN LÝ KHÁCH HÀNG =====");
        System.out.println("1. Thêm khách hàng");
        System.out.println("2. Sửa thông tin khách hàng");
        System.out.println("3. Xoá khách hàng");
        System.out.println("4. Hiển thị danh sách khách hàng");
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

    private static void addCustomer() {
        System.out.print("Nhập ID khách hàng: ");
        String id = sc.nextLine();
        System.out.print("Nhập tên khách hàng: ");
        String name = sc.nextLine();
        System.out.print("Nhập email khách hàng: ");
        String email = sc.nextLine();
        System.out.print("Nhập loại khách hàng (truc tiep/tu xa): ");
        String type = sc.nextLine();

        Customer customer = new Customer(id, name, email, type);
        customerList.addCustomer(customer);
    }

    private static void editCustomer() {
        System.out.print("Nhập ID khách hàng cần sửa: ");
        String id = sc.nextLine();

        System.out.print("Tên mới: ");
        String name = sc.nextLine();
        System.out.print("Email mới: ");
        String email = sc.nextLine();
        System.out.print("Loại khách mới: ");
        String type = sc.nextLine();

        customerList.editCustomer(id, name, email, type);
    }

    private static void deleteCustomer() {
        System.out.print("Nhập ID khách hàng cần xoá: ");
        String id = sc.nextLine();
        customerList.deleteCustomer(id);
    }
}
