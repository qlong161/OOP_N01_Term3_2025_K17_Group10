public class Customer {

   
    private String id;   //null 
    private String name; //null
    private String email; //null

    Customer (String id, String name , String email) {
    this.id = id;
    this.name = name;
    this.email = email;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
  
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    //method
    public void displayInfo() {
        System.out.println("ID khách hàng: " + id);
        System.out.println("Tên khách hàng: " + name);
        System.out.println("Email: " + email);
    }

    
}
