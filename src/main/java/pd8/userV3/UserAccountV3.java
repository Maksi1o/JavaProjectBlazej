package pd8.userV3;

import lombok.ToString;

import java.util.Objects;

@ToString
public class UserAccountV3 {
    Long id;
    String email;
    String displayName;

    public UserAccountV3(Long id, String email, String displayName) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if(!(obj instanceof UserAccountV3 other)) {
            return false;
        }

        return Objects.equals(id, other.id)
            && Objects.equals(email, other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
