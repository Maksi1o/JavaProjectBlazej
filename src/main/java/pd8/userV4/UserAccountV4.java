package pd8.userV4;

import java.util.Objects;

public class UserAccountV4 {
    Long id;
    String email;
    String displayName;
    String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public UserAccountV4(Long id, String email, String displayName, String status) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof UserAccountV4 other)) {
            return false;
        }

        return Objects.equals(id, other.id)
            && Objects.equals(email, other.email)
            && Objects.equals(status, other.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, status);
    }
}
