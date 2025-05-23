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

    public getId(){
        return id;
    }
    public setId(string id){
        this.id = id;
    }

    public getName(){
        return name;
    }
    public setName(string name){
        this.name = name;
    }
    public getEmail(){
        return email;
    }
    public setEmail(string email){
        this.email = email;
    }
    public getType(){
        return type;
    }
    public setType(string type){
        this.type = type;
    }
    
    public void displayInfo(){
        System.out.println("Ma khach hang: " + id);
        System.out.println("Ten khach hang: " + name);
        System.out.println("email: " + email);
        System.out.println("Kieu khach: " + type);
    }

}  
