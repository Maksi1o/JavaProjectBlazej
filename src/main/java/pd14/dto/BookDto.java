package pd14.dto;

public record BookDto(
        Long id,
        String isbn,
        String title,
        String author,
        int year,
        int availableCopies
) {
    @Override
    public String toString() {
        return String.format("#%d \"%s\" by %s (%d), ISBN %s, copies: %d",
                id, title, author, year, isbn, availableCopies);
    }
}
