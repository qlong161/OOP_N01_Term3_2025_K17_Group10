import java.util.HashMap;
import java.util.Map;

public class CustomerList {
    private Map<String, Customer> cl = new HashMap<>();

    public void addCustomer(Customer customer) {
        try {
            cl.put(customer.getId(), customer);
            System.out.println("Đã thêm khách hàng với ID: " + customer.getId());
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm khách hàng: " + e.getMessage());
        }
        finally {
            System.out.println("Kết thúc thêm khách hàng.");
        }
    }

    public boolean editCustomer(String customerID, String newName, String newEmail, String newType) {
        try {
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
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật khách hàng: " + e.getMessage());
            return false;
        }
          finally {
            System.out.println("Kết thúc cập nhật khách hàng.");
          }
    }

    public boolean deleteCustomer(String id) {
        try {
            if (cl.containsKey(id)) {
                cl.remove(id);
                System.out.println("Đã xóa khách hàng có ID: " + id);
                return true;
            } else {
                System.out.println("Không tìm thấy khách hàng với ID: " + id);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa khách hàng: " + e.getMessage());
            return false;
        }
        finally {
            System.out.println("Kết thúc xóa khách hàng.");
        }
    }

    public Customer getCustomerById(String id) {
            return cl.getOrDefault(id, null); 
}

    public void printCustomerList() {
        try {
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
        } catch (Exception e) {
            System.out.println("Lỗi khi in danh sách khách hàng: " + e.getMessage());
        }
          finally {
            System.out.println("Kết thúc in danh sách khách hàng.");
          }
    }

    public Map<String, Customer> getCustomers() {
        return cl;
    }
}
