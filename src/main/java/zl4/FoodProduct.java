package zl4;

public class FoodProduct extends Product {
    private final String expiryDate;

    public FoodProduct(String name, int price, String category, String expiryDate) {
        super(name, price, category);
        this.expiryDate = expiryDate;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "Data ważności: " + expiryDate;
    }
}
