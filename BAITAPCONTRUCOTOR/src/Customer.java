public class Customer {

   
    private String id;   
    private String name;
    private String email;

    public Customer(String id, String name, String email) {
        this.id = id;     
        this.name = name; 
        this.email = email; 
    }

    public void displayInfo() {
        System.out.println("ID khách hàng: " + id);
        System.out.println("Tên khách hàng: " + name);
        System.out.println("Email: " + email);
    }

    
}
