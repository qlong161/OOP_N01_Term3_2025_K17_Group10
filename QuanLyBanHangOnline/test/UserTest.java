public class UserTest {
    public static void test() {
        User user1 = new User("0001", "Long", "0919560000");

        System.out.println("Mã: " + user1.getMaUser());
        System.out.println("Tên: " + user1.getTenUser());
        System.out.println("SĐT: " + user1.getSdt());
    }
}
