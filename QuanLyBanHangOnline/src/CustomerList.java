import java.util.ArrayList;

public class CustomerList {
    private ArrayList<Customer> cl = new ArrayList<Customer>();

    public ArrayList<Customer> addCustomers(Customer customer) {
        cl.add(customer);
        return cl;
    }

    public boolean editCustomer(String customerID, String newName, String newEmail, String newType) {
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

    public ArrayList<Customer> getDeleteCustomer(String id){
        for (int i = 0; i < cl.size() ; i++){
             if (cl.get(i).getId().equals(id)) {

                cl.remove(i);
                System.out.println("Đã xóa khách hàng có ID: " + id);
            }
        }
        System.out.println("Không tìm thấy khách hàng với ID: " + id);
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
