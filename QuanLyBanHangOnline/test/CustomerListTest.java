public class CustomerListTest{
    public static void main(String[] args) {
    CustomerList cm = new CustomerList();
    Customer c1 = new Customer("C001", "Nguyen Van A", "a@gmail.com","mua truc tiep");

    cm.addCustomers(c1);
    cm.printCustomerList();




    }
}