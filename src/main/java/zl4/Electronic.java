package zl4;

public class Electronic extends Product {
    int warranty;

    @Override
    public String getDescription() {
        return super.getDescription() + "Gwarancja: " + warranty;
    }
}

