public class CustomerTest {
    public static void test() {
        try {
            Customer customer = new Customer("C001", "Nguyen Van A", "a@gmail.com","mua truc tiep");
            customer.displayInfo();
        } catch (Exception e) {
            System.out.println("Lỗi khi test Customer: " + e.getMessage());
        } finally {
            System.out.println("Kết thúc kiểm thử Customer.");
        }
    }
}
