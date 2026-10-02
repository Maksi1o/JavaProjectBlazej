package zl4;

import java.util.ArrayList;
import java.util.List;

public class Store {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        products.add(new FoodProduct("Cebula", 2, "Warzywo", "10.12.2026"));
        products.add(new FoodProduct("Jabłko", 1, "Owoc", "15.10.2026"));
        products.add(new Electronic("RTX 4070", 2000, "Podzespoły komputerowe", "20.12.2027"));
        products.add(new Electronic("Iphone 18 Ultra", 6000, "Urządzenia Mobilne", "15.06.2028"));
        products.add(new Electronic("TV LG OLED 50'", 10000, "RTV", "26.02.2027"));

        for (Product product : products) {
            System.out.println(product.getDescription());

        }
    }
}
