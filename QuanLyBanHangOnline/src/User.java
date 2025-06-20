public class User {
    private String maUser;
    private String tenUser;
    private OrderList orderList;

    public User(String maUser, String tenUser) {
        try {
            if (maUser == null || maUser.isEmpty() || tenUser == null || tenUser.isEmpty()) {
                throw new IllegalArgumentException("Mã hoặc tên người dùng không được để trống.");
            }
            this.maUser = maUser;
            this.tenUser = tenUser;
            this.orderList = new OrderList(); // mỗi người dùng có danh sách đơn hàng riêng
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi tạo User: " + e.getMessage());
        }
    }

    public String getMaUser() {
        return maUser;
    }

    public void setMaUser(String maUser) {
        try {
            if (maUser == null || maUser.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã người dùng không được rỗng");
            }
            this.maUser = maUser;
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật mã người dùng: " + e.getMessage());
        }
    }

    public String getTenUser() {
        return tenUser;
    }

    public void setTenUser(String tenUser) {
        try {
            if (tenUser == null || tenUser.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên người dùng không được rỗng");
            }
            this.tenUser = tenUser;
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật tên người dùng: " + e.getMessage());
        }
    }

    public OrderList getOrderList() {
        return orderList;
    }

    public void setOrderList(OrderList orderList) {
        try {
            if (orderList == null) {
                throw new IllegalArgumentException("Danh sách đơn hàng không được null");
            }
            this.orderList = orderList;
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật danh sách đơn hàng: " + e.getMessage());
        }
    }
}
