public class CustomerListTest {
    public static void main(String[] args) {
        CustomerList customerList = new CustomerList();
        Customer c1 = new Customer("C001", "Nguyễn Văn A", "a@gmail.com", "Mua trực tiếp");
        Customer c2 = new Customer("C002", "Trần Thị B", "b@gmail.com", "Đặt Online");
        Customer c3 = new Customer("C003", "Lê Văn C", "c@gmail.com", "Mua trực tiếp");

        customerList.addCustomer(c1);
        customerList.addCustomer(c2);
        customerList.addCustomer(c3);
        customerList.printCustomerList();
        System.out.println("\n");
        customerList.editCustomer("C002", "Trần Thị Bích", "bich@gmail.com", "Mua trực tiếp");
        customerList.printCustomerList();

        System.out.println("\n xóa C001");
        customerList.deleteCustomer("C001");
        customerList.printCustomerList();

        customerList.deleteCustomer("C999");
        customerList.printCustomerList();
    }
}
