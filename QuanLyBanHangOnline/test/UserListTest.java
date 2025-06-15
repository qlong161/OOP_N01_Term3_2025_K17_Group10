import java.util.Scanner;

public class UserListTest {
    private static final UserList userList = new UserList();
    private static final CustomerList customerList = new CustomerList();
    private static final ProductList productList = new ProductList();

    public static void runUserManagementMenu() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;  // Fix lỗi: biến choice phải được khởi tạo trước

        do {
            System.out.println("\n===== QUẢN LÝ NGƯỜI DÙNG =====");
            System.out.println("1. Thêm người dùng");
            System.out.println("2. Sửa thông tin người dùng");
            System.out.println("3. Xóa người dùng");
            System.out.println("4. Hiển thị danh sách người dùng");
            System.out.println("5. Quản lý đơn hàng của người dùng");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
                continue;
            }

            switch (choice) {
                case 1:
                    addUser(sc);
                    break;
                case 2:
                    editUser(sc);
                    break;
                case 3:
                    deleteUser(sc);
                    break;
                case 4:
                    userList.printAllUsers();
                    break;
                case 5:
                    manageOrdersForUser(sc);
                    break;
                case 0:
                    System.out.println("Đã thoát menu quản lý người dùng.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }

        } while (choice != 0);
    }

    private static void addUser(Scanner sc) {
        System.out.print("Nhập mã người dùng: ");
        String id = sc.nextLine();
        if (userList.getUserById(id) != null) {
            System.out.println("Người dùng đã tồn tại!");
            return;
        }
        System.out.print("Nhập tên người dùng: ");
        String name = sc.nextLine();
        userList.addUser(new User(id, name));
        System.out.println("Đã thêm người dùng.");
    }

    private static void editUser(Scanner sc) {
        System.out.print("Nhập mã người dùng cần sửa: ");
        String id = sc.nextLine();
        User user = userList.getUserById(id);
        if (user == null) {
            System.out.println("Không tìm thấy người dùng.");
            return;
        }
        System.out.print("Nhập tên mới: ");
        String newName = sc.nextLine();
        user.setTenUser(newName);
        System.out.println("Đã cập nhật tên người dùng.");
    }

    private static void deleteUser(Scanner sc) {
        System.out.print("Nhập mã người dùng cần xóa: ");
        String id = sc.nextLine();
        userList.removeUser(id);
    }

    private static void manageOrdersForUser(Scanner sc) {
        System.out.print("Nhập mã người dùng để quản lý đơn hàng: ");
        String id = sc.nextLine();
        User user = userList.getUserById(id);
        if (user == null) {
            System.out.println("Không tìm thấy người dùng.");
            return;
        }
        System.out.println(">> Đang truy cập đơn hàng của: " + user.getTenUser());
        OrderListTest.runOrderMenu(user.getOrderList(), customerList, productList); // Dùng order riêng của user
    }
}
