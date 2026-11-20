package pd14.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd14.dto.BookDto;
import pd14.entity.Book;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BookMapper {
    public static BookDto toDto(Book book) {
        Objects.requireNonNull(book, "Book cannot be null");
        return new BookDto(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getAvailableCopies()
        );
    }
}
