public class ProductListTest {

    
    public static void test() {
        ProductList productList = new ProductList();

        productList.addProduct(new Product("P01", "Iphone", 1500.0, 10));
        productList.addProduct(new Product("P02", "Laptop", 2000.0, 5));
        productList.addProduct(new Product("P03", "Tablet", 800.0, 7));
        productList.displayAllProducts();

        boolean isEdited = productList.editProduct("P02", "Laptop Gaming", 2500.0, 3);
        if (isEdited == true) {
            System.out.println("Sửa thành công.");
        }
        System.out.println("\nDanh sách sau khi sửa P02");
        productList.displayAllProducts();

        System.out.println("\nXóa sản phẩm P03");
        boolean isRemoved = productList.removeProduct("P03");
        if (isRemoved == true) {
            System.out.println("Xóa thành công.");
        }

        productList.displayAllProducts();
        System.out.println("\nThử thêm sản phẩm với ID trùng (P01)");
        productList.addProduct(new Product("P01", "Iphone X", 1300.0, 12)); 
    }
}
