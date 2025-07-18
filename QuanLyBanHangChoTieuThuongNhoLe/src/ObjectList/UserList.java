package ObjectList;
import java.util.ArrayList;
import java.util.List;

import ObjectGeneral.User;

public class UserList {
    private List<User> users = new ArrayList<>();
    private static UserList userListStatic;

    public static void setMainUserList(UserList userList) {
        try {
            userListStatic = userList;
        } catch (Exception e) {
            System.err.println("Lỗi khi thiết lập danh sách người dùng chính: " + e.getMessage());
        } finally {
            System.out.println("Kết thúc thiết lập danh sách người dùng chính.");
        }
    }

    public boolean addUser(User user) {
        try {
            for (User u : users) {
                if (u.getMaUser().equals(user.getMaUser())) {
                    System.out.println("Người dùng với mã này đã tồn tại.");
                    return false;
                }
            }
            users.add(user);
            System.out.println("Đã thêm người dùng thành công.");
            return true;
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm người dùng: " + e.getMessage());
            return false;
        } finally {
            System.out.println("Kết thúc thao tác thêm người dùng.");
        }
    }

    public boolean removeUser(String maUser) {
        try {
            boolean removed = users.removeIf(u -> u.getMaUser().equals(maUser));
            if (removed) {
                System.out.println("Đã xoá người dùng với mã: " + maUser);
            } else {
                System.out.println("Không tìm thấy người dùng với mã: " + maUser);
            }
            return removed;
        } catch (Exception e) {
            System.err.println("Lỗi khi xoá người dùng: " + e.getMessage());
            return false;
        } finally {
            System.out.println("Kết thúc thao tác xoá người dùng.");
        }
    }

    public User getUserById(String maUser) {
        try {
            for (User u : users) {
                if (u.getMaUser().equals(maUser)) {
                    return u;
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi tìm người dùng: " + e.getMessage());
        } finally {
            System.out.println("Kết thúc xử lý tìm người dùng.");
        }
        return null;
    }

    public List<User> getAllUsers() {
        try {
            return users;
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách người dùng: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            System.out.println("Kết thúc thao tác lấy danh sách người dùng.");
        }
    }

    public void displayAllUsers() {
        try {
            if (users.isEmpty()) {
                System.out.println("Danh sách người dùng trống.");
                return;
            }
            System.out.println("===== Danh sách người dùng =====");
            for (User u : users) {
                System.out.println("Mã người dùng: " + u.getMaUser());
                System.out.println("Tên người dùng: " + u.getTenUser());
                System.out.println("------------------");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị danh sách người dùng: " + e.getMessage());
        } finally {
            System.out.println("Kết thúc xử lý hiển thị người dùng.");
        }
    }

    public static void getTotalRevenue() {
        try {
            if (userListStatic == null) {
                System.out.println("Chưa có danh sách người dùng.");
                return;
            }

            double total = 0;
            for (User u : userListStatic.getAllUsers()) {
                total += u.getOrderList().getRevenueOfToday();
            }

            System.out.printf("Tổng doanh thu trong ngày của tất cả người dùng: %.2f VND%n", total);
        } catch (Exception e) {
            System.err.println("Lỗi khi tính tổng doanh thu: " + e.getMessage());
        } finally {
            System.out.println("Kết thúc xử lý thống kê doanh thu.");
        }
    }
}
