import java.util.ArrayList;
public class ProductList {
    ArrayList<Product> prdt = new ArrayList<Product>();

    public ArrayList<Product> addProduct(Product p){
        prdt.add(p);
        return prdt;
    }

    public ArrayList<Product> getEditProducts(String id, String name, double price, int quantity){
        for(int i = 0; i < prdt.size(); i++){
            if(prdt.get(i).getId().equals(id)){
                System.out.print("true");
                prdt.get(i).setName(name);
                prdt.get(i).setPrice(price);
                prdt.get(i).setQuantity(quantity);
            }

        }
        return prdt;
    }
      


}
