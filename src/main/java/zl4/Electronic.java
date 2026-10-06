package zl4;

public class Electronic extends Product {
    String warranty;

    public Electronic(String name, int price, String category, String warranty) {
        super(name, price, category);
        this.warranty = warranty;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "Gwarancja: " + warranty;
    }
}

