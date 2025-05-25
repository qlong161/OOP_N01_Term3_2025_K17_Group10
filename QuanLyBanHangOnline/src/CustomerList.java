import java.util.ArrayList;

public class CustomerList{
    ArrayList<Customer> cl = new ArrayList<Customer>();
    public ArrayList<Customer> addCustomers(Customer customer) {

        cl.add(customer);
        return cl;





        
    }
    public void printCustomerList() {
    int len = cl.size();
    for (int i = 0; i < len; i++) {
        Customer c = cl.get(i);
        System.out.println("Customer ID: " + c.getId());
        System.out.println("Fullname: " + c.getName());
        System.out.println("Email: " + c.getEmail());
        System.out.println("Kieu khach: " + c.getType());
        System.out.println("----------------------");
    }
}

}