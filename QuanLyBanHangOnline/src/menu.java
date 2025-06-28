import java.util.Scanner;

import ObjectList.UserList;

public class menu {
    private static Scanner sc = new Scanner(System.in);

    public static void mainmenu() {
        int choice;
        do {
            showMainMenu();
            choice = getChoice();

            switch (choice) {
                case 1:
                    ProductListTest.runProductMenu();
                    break;
                case 2:
                    CustomerListTest.runCustomerMenu();
                    break;
                case 3:
                    UserListTest.runUserManagementMenu();
                    break;
                case 4:
                    UserList.getTotalRevenue(); // nhớ là hàm này phải static
                    break;
                case 0:
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
            }
        } while (choice != 0);
    }

    private static void showMainMenu() {
        System.out.println("\n===== MENU CHÍNH =====");
        System.out.println("1. Quản lý sản phẩm");
        System.out.println("2. Quản lý khách hàng");
        System.out.println("3. Quản lý người dùng");
        System.out.println("4. Thống kê doanh thu trong ngày");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

