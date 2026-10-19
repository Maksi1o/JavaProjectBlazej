package pd6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class User implements EntityIdentifiable<Long> {
    private final Long id;
    private String name;

    public void changeName(String newName) {
        this.name = newName;
    }
}
