public class CustomerListTest{
    public static void main(String[] args) {
    CustomerList cm = new CustomerList();
    try {
            Customer c1 = new Customer("C001", "Nguyen Van A", "a@gmail.com", "mua truc tiep");
            cm.addCustomers(c1);
            System.out.println("Thêm khách hàng thành công.");
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm khách hàng: " + e.getMessage());
        }
    cm.printCustomerList();
    try {
            cm.editCustomer("C001", "Nguyen Van B", "b@gmail.com", "dat hang");
            System.out.println("Cập nhật khách hàng thành công.");
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật khách hàng: " + e.getMessage());
        }
    cm.printCustomerList();
    cm.getDeleteCustomer("C001");
     try {
            cm.getDeleteCustomer("C001");  // Thử xóa lại xem xử lý lỗi thế nào khi khách hàng đã bị xóa trước đó
            System.out.println("Xóa khách hàng thành công (hoặc không tìm thấy).");
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa khách hàng: " + e.getMessage());
        }

    }
}