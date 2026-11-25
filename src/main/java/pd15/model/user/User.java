package pd15.model.user;

public record User(
        Long id,
        String username,
        String email,
        Company company,
        Address address
) {
}
