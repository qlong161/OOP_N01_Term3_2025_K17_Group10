package models;

public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;

    public Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void displayInfo() {
        System.out.println("Product: " + name + " | Price: $" + price + " | Quantity: " + quantity);
    }
}