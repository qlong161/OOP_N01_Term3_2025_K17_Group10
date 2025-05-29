import java.util.ArrayList;

public class CustomerList {
    private ArrayList<Customer> cl = new ArrayList<Customer>();

    public ArrayList<Customer> addCustomers(Customer customer) {
    try {
        cl.add(customer);
    } catch (Exception e) {
        System.err.println("Lỗi khi thêm khách hàng: " + e.getMessage());
    }
    return cl;
}

    public boolean editCustomer(String customerID, String newName, String newEmail, String newType) {
        try {
        for (Customer c : cl) {
            if (c.getId().equals(customerID)) {
                c.setName(newName);
                c.setEmail(newEmail);
                c.setType(newType);
                System.out.println("Cập nhật thông tin khách hàng thành công.");
                return true;
            }
        }
        System.out.println("Không tìm thấy khách hàng với ID: " + customerID);
        return false;
    }
      catch (Exception e) {
        System.out.println("Lỗi khi cập nhật thông tin khách hàng: " + e.getMessage());
        return false;
    }
}

    public ArrayList<Customer> getDeleteCustomer(String id){
        try {
        for (int i = 0; i < cl.size() ; i++){
             if (cl.get(i).getId().equals(id)) {

                cl.remove(i);
                System.out.println("Đã xóa khách hàng có ID: " + id);
            }
        }
        System.out.println("Không tìm thấy khách hàng với ID: " + id);
         } catch (Exception e) {
        System.err.println("Lỗi khi xóa khách hàng: " + e.getMessage());
    }
        return cl;
    }


    public void printCustomerList() {
        for (Customer c : cl) {
            System.out.println("Customer ID: " + c.getId());
            System.out.println("Fullname: " + c.getName());
            System.out.println("Email: " + c.getEmail());
            System.out.println("Kiểu khách: " + c.getType());
            System.out.println("----------------------");
        }
    }
}
