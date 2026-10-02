package zl4;

public class Product {
    protected String name;
    protected int price;
    protected String category;

    public Product(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getDescription() {
        return "nazwa produktu: " + name
                + " cena " + price
                + "kategoria: " + category;
    }
}
