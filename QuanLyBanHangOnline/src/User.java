public class User {
    private String maUser;
    private String tenUser;
    private OrderList orderList; 

    public User(String maUser, String tenUser) {
        this.maUser = maUser;
        this.tenUser = tenUser;
        this.orderList = new OrderList(); // cập nhật cho mỗi user có danh sách order riêng
    }

    public String getMaUser() {
        return maUser;
    }

    public void setMaUser(String maUser) {
        this.maUser = maUser;
    }

    public String getTenUser() {
        return tenUser;
    }

    public void setTenUser(String tenUser) {
        this.tenUser = tenUser;
    }

    public OrderList getOrderList() {
        return orderList;
    }

    public void setOrderList(OrderList orderList) {
        this.orderList = orderList;
    }
}