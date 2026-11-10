package pd8.userV1;

public class UserAccount {
    Long id;
    String email;
    String displayName;

    public UserAccount (Long id, String email, String displayName) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
    }
}
