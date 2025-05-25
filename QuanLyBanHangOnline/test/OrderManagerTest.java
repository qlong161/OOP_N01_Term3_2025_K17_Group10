public class OrderManagerTest {
    public static void main(String[] args){
    OrderList om = new OrderList();
    Customer c1 = new Customer("001", "Long", "example@gmail.com", "Mua truc tiep");
    Product p1 = new Product("C001", "Iphone", 1000.0, 1);
    Order o1 = new Order("O001", c1, p1, 2);
    
    Customer c2 = new Customer("002", "Mạnh", "example@gmail.com", "Mua truc tiep");
    Product p2 = new Product("C002", "Iphone", 1000.0, 1);
    Order o2 = new Order("O002", c2, p2, 2);
        om.addOrder(o1);
        om.addOrder(o2);
        om.displayOrderList();
    }
}
