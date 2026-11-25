package pd15.model.user;

public record Address(
        String street,
        String suite,
        String city,
        Geo geo
) {
}
