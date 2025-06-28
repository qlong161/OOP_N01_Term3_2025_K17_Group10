package ObjectGeneral;
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

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
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
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }

    public void displayInfo(){
        try{
        System.out.println("Ma khach hang: " + id);
        System.out.println("Ten khach hang: " + name);
        System.out.println("email: " + email);
        System.out.println("Kieu khach: " + type);
    }
    catch (Exception e) {
        System.out.println("Lỗi khi hiển thị thông tin khách hàng: "+ e.getMessage());
    }
    finally {
        System.out.println("Đã thực hiện hiển thị thông tin khách hàng.");
    }
  }
}  
