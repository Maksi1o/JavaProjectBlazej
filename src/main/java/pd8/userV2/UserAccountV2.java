package pd8.userV2;

import java.util.Objects;

public class UserAccountV2 {
    Long id;
    String email;
    String displayName;

    public UserAccountV2 (Long id, String email, String displayName) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if(!(obj instanceof UserAccountV2 other)) {
            return false;
        }

        return Objects.equals(id, other.id)
            && Objects.equals(email, other.email)
            && Objects.equals(displayName, other.displayName);
    }
}
