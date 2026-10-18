package pd6.model;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Product {
    private final String id;
    private int price;

    public Product(String id, int price) {
        this.id = id;
        this.price = price;
    }

}
