package pd14.dto;

public record MemberDto(
        Long id,
        String name,
        String email
) {
    @Override
    public String toString() {
        return String.format("#%d %s <%s>", id, name, email);
    }
}
