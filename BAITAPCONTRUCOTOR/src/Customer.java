public class Customer {

   
    private String id;   //null 
    private String name; //null
    private String email; //null



    //constructor  
    //String email
    
    Customer(String id, String name ) {
        this.id = id;     
        this.name = name; 
     //   this.email = email; 
    }

    //method
    public void displayInfo() {
        System.out.println("ID khách hàng: " + id);
        System.out.println("Tên khách hàng: " + name);
        System.out.println("Email: " + email);
    }

    
}
