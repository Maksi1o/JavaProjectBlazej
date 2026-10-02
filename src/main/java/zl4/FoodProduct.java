package zl4;

public class FoodProduct extends Product {
    int expiryDate;

    @Override
    public String getDescription() {
        return super.getDescription() + "Data ważności: " + expiryDate;
    }
}
