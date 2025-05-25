import java.util.ArrayList;
public class OrderList {
    ArrayList<Order> ords = new ArrayList<Order>();

    public ArrayList<Order> addOrder(Order order){
        ords.add(order);
        return ords;
    } 

 /*    public ArrayList<Order> getEditOrders(String orderId, Customer customer, Product product, int quantity,String status) {

        for (int i = 0; i < ords.size(); i++) {

            if (ords.get(i).orderId == orderId) {

                System.out.print("true");

                ords.get(i). = fullname;
            }

        }

        return st;
    }

*/





    public void displayOrderList() {
        if (ords.isEmpty()) {
            System.out.println("Danh sach don hang trong.");
        } else {
            for (Order order : ords) {
                order.displayOrder();
            }
        }
    }
}
