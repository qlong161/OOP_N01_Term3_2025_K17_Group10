import java.util.HashMap;
import java.util.Map;

public class UserList {
    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        if (users.containsKey(user.getMaUser())) {
            System.out.println("Người dùng với ID " + user.getMaUser() + " đã tồn tại.");
            return;
        }
        users.put(user.getMaUser(), user);
        System.out.println("Đã thêm người dùng: " + user.getTenUser());
    }

    public boolean removeUser(String id) {
        if (users.remove(id) != null) {
            System.out.println("Đã xóa người dùng có ID: " + id);
            return true;
        } else {
            System.out.println("Không tìm thấy người dùng để xóa.");
            return false;
        }
    }

    public User getUserById(String id) {
        return users.get(id);
    }

    public void printAllUsers() {
        if (users.isEmpty()) {
            System.out.println("Danh sách người bán trống.");
            return;
        }
        for (User user : users.values()) {
            System.out.println("Mã: " + user.getMaUser());
            System.out.println("Tên: " + user.getTenUser());
            System.out.println("----------------------");
        }
    }
}
