public class TestProduct {
    public static void test() {
        try {
        // Tạo đối tượng Product
        Product product = new Product("P001", "Laptop", 1500.00, 10);

        // Hiển thị thông tin sản phẩm
        product.displayInfo();

        // Thử cập nhật thông tin sản phẩm qua setter
        product.setPrice(1400.00);
        product.setQuantity(8);

        // Hiển thị thông tin sau khi cập nhật
        System.out.println("\nSau khi cập nhật:");
        product.displayInfo();
    }
    catch(Exception e){
        System.err.println("Lỗi trong testProduct: " + e.getMessage());
    }
    finally {
        System.out.println("Kết thúc test sản phẩm.");
    }
  }
}