package zl4;

public class Product {
    protected String name;
    protected int price;
    protected String category;



    public String getDescription() {
        return "nazwa produktu: " + name
                + " cena " + price
                + "kategoria: " + category;
    }
}
