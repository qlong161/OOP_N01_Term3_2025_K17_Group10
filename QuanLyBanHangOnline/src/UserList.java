import java.util.ArrayList;
import java.util.List;

public class UserList {
    private List<User> users = new ArrayList<>();
    private static UserList userListStatic;

    public static void setMainUserList(UserList userList) {
        userListStatic = userList;
    }

    public boolean addUser(User user) {
        for (User u : users) {
            if (u.getMaUser().equals(user.getMaUser())) {
                System.out.println("Người dùng với mã này đã tồn tại.");
                return false;
            }
        }
        users.add(user);
        return true;
    }

    public boolean removeUser(String maUser) {
        return users.removeIf(u -> u.getMaUser().equals(maUser));
    }

    public User getUserById(String maUser) {
        for (User u : users) {
            if (u.getMaUser().equals(maUser)) {
                return u;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("Danh sách người dùng trống.");
            return;
        }
        for (User u : users) {
            System.out.println("Mã người dùng: " + u.getMaUser());
            System.out.println("Tên người dùng: " + u.getTenUser());
            System.out.println("------------------");
        }
    }

    // ====== HÀM STATIC THỐNG KÊ DOANH THU TRONG NGÀY ======
    public static void getTotalRevenue() {
        if (userListStatic == null) {
            System.out.println("Chưa có danh sách người dùng.");
            return;
        }

        double total = 0;
        for (User u : userListStatic.getAllUsers()) {
            total += u.getOrderList().getRevenueOfToday();
        }

        System.out.printf("Tổng doanh thu trong ngày của tất cả người dùng: %.2f VND%n", total);
    }
}
