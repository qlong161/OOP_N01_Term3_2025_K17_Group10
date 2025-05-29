import java.util.HashMap;
import java.util.Map;

public class CustomerList {
    private Map<String, Customer> cl = new HashMap<>();
    public void addCustomer(Customer customer) {
        cl.put(customer.getId(), customer);
        System.out.println("Đã thêm khách hàng với ID: " + customer.getId());
    }

    public boolean editCustomer(String customerID, String newName, String newEmail, String newType) {
        Customer c = cl.get(customerID);
        if (c != null) {
            c.setName(newName);
            c.setEmail(newEmail);
            c.setType(newType);
            System.out.println("Cập nhật thông tin khách hàng thành công.");
            return true;
        } else {
            System.out.println("Không tìm thấy khách hàng với ID: " + customerID);
            return false;
        }
    }

    public boolean deleteCustomer(String id) {
        if (cl.containsKey(id)) {
            cl.remove(id);
            System.out.println("Đã xóa khách hàng có ID: " + id);
            return true;
        } else {
            System.out.println("Không tìm thấy khách hàng với ID: " + id);
            return false;
        }
    }

    public void printCustomerList() {
        if (cl.isEmpty()) {
            System.out.println("Danh sách khách hàng trống.");
            return;
        }
        for (Customer c : cl.values()) {
            System.out.println("Customer ID: " + c.getId());
            System.out.println("Fullname: " + c.getName());
            System.out.println("Email: " + c.getEmail());
            System.out.println("Kiểu khách: " + c.getType());
            System.out.println("----------------------");
        }
    }

    public Map<String, Customer> getCustomers() {
        return cl;
    }
}
