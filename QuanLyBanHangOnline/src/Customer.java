import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// CLASS ĐẠI DIỆN CHO KHÁCH HÀNG
public class Customer {
    private String id;
    private String name;
    private String email;
    private String type;

    public Customer(String id, String name, String email, String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public void displayInfo() {
        System.out.println("Mã khách hàng: " + id);
        System.out.println("Tên khách hàng: " + name);
        System.out.println("Email: " + email);
        System.out.println("Kiểu khách: " + type);
    }
}

// CLASS QUẢN LÝ DANH SÁCH KHÁCH HÀNG (CRUD)
class CustomerManager {
    private List<Customer> customers = new ArrayList<>();

    // CREATE
    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Đã thêm khách hàng: " + customer.getName());
    }

    // READ
    public void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("Chưa có khách hàng nào.");
            return;
        }
        for (Customer customer : customers) {
            customer.displayInfo();
            System.out.println("------------");
        }
    }

    public Customer findCustomerById(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    // UPDATE
    public void updateCustomer(String id, String newName, String newEmail, String newType) {
        Customer customer = findCustomerById(id);
        if (customer != null) {
            customer.setName(newName);
            customer.setEmail(newEmail);
            customer.setType(newType);
            System.out.println("Đã cập nhật khách hàng: " + id);
        } else {
            System.out.println("Không tìm thấy khách hàng với ID: " + id);
        }
    }

    // DELETE
    public void deleteCustomer(String id) {
        Customer customer = findCustomerById(id);
        if (customer != null) {
            customers.remove(customer);
            System.out.println("Đã xóa khách hàng: " + id);
        } else {
            System.out.println("Không tìm thấy khách hàng để xóa.");
        }
    }
}

// CLASS CHẠY DEMO CHỨC NĂNG CRUD
class Main {
    public static void main(String[] args) {
        CustomerManager manager = new CustomerManager();

        // Thêm khách hàng
        manager.addCustomer(new Customer("KH01", "Nguyễn Văn A", "a@gmail.com", "Trực tiếp"));
        manager.addCustomer(new Customer("KH02", "Trần Thị B", "b@gmail.com", "Đặt từ xa"));

        // Hiển thị tất cả khách hàng
        System.out.println("\nDanh sách khách hàng:");
        manager.displayAllCustomers();

        // Cập nhật khách hàng
        System.out.println("\nCập nhật khách hàng KH02:");
        manager.updateCustomer("KH02", "Trần Thị Bảo", "baobao@gmail.com", "Đặt từ xa");

        // Xóa khách hàng
        System.out.println("\nXóa khách hàng KH01:");
        manager.deleteCustomer("KH01");

        // Hiển thị lại danh sách sau khi xóa
        System.out.println("\nDanh sách khách hàng còn lại:");
        manager.displayAllCustomers();
    }
}
