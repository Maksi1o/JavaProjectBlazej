package pd6.model;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class User {
    private final Long id;
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void changeName(String newName) {
        this.name = newName;
    }
}
