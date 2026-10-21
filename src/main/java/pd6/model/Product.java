package pd6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class Product implements EntityIdentifiable<String> {
    private final String id;
    private int price;
}
