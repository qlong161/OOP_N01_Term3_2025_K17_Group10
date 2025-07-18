package com.example.servingwebcontent.Model;

public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;

    public Product(String id, String name, double price, int quantity) {
        try {
            if (price < 0 || quantity < 0) {
                throw new IllegalArgumentException("Giá và số lượng phải >= 0");
            }
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi tạo sản phẩm: " + e.getMessage());
        }
        finally {
            System.out.println("Khởi tạo sản phẩm hoàn tất.");
        }
    }

    public Product() {}

    public boolean isAvailable(int qty) {
        return quantity >= qty;
    }

    public void reduceQuantity(int qty) {
        try {
            if (qty <= 0) {
                throw new IllegalArgumentException("Số lượng giảm phải > 0");
            }
            if (isAvailable(qty)) {
                quantity -= qty;
            } else {
                System.out.println("Không đủ hàng trong kho!");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi giảm số lượng: " + e.getMessage());
        }
        finally {
            System.out.println("Xử lý giảm số lượng hoàn tất.");
        }
    }

    public void increaseQuantity(int qty) {
        try {
            if (qty <= 0) {
                throw new IllegalArgumentException("Số lượng tăng phải > 0");
            }
            quantity += qty;
        } catch (Exception e) {
            System.err.println("Lỗi khi tăng số lượng: " + e.getMessage());
        }
        finally {
            System.out.println("Xử lý tăng số lượng hoàn tất.");
        }
    }

    public void displayInfo() {
        try {
            System.out.println("Mã sản phẩm: " + id);
            System.out.println("Tên sản phẩm: " + name);
            System.out.println("Giá: " + price);
            System.out.println("Số lượng còn: " + quantity);
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị thông tin sản phẩm: " + e.getMessage());
        } finally {
            System.out.println("Đã xử lý hiển thị sản phẩm.");
        }
    }

    // Getter và Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("ID không hợp lệ");
            }
            this.id = id;
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật ID: " + e.getMessage());
        }
        finally {
            System.out.println("Đã xử lý cập nhật ID.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên không hợp lệ");
            }
            this.name = name;
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật tên: " + e.getMessage());
        }
        finally {
            System.out.println("Đã xử lý cập nhật tên");
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        try {
            if (price < 0) {
                throw new IllegalArgumentException("Giá không được âm");
            }
            this.price = price;
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật giá: " + e.getMessage());
        }
        finally {
            System.out.println("Đã xử lý cập nhật giá.");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        try {
            if (quantity < 0) {
                throw new IllegalArgumentException("Số lượng không được âm");
            }
            this.quantity = quantity;
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật số lượng: " + e.getMessage());
        }
        finally {
            System.out.println("Đã xử lý cập nhật số lượng.");
        }
    }
}
