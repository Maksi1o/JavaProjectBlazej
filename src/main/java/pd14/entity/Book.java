package pd14.entity;

import lombok.Getter;
import pd14.exceptions.BookNotAvailableException;
import pd14.validator.Validation;

@Getter
public class Book {
    private Long id;
    private final String isbn;
    private final String title;
    private final String author;
    private final int year;
    private int availableCopies;

    public Book(String isbn, String title, String author, int year, int availableCopies) {
        this.isbn = Validation.requireNotBlank(isbn, "isbn");
        this.title = Validation.requireNotBlank(title, "title");
        this.author = Validation.requireNotBlank(author, "author");
        this.year = Validation.requireNonNegative(year, "year");
        this.availableCopies = Validation.requireNonNegative(availableCopies, "availableCopies");
    }

    public void borrow() {
        if (!isAvailable()) {
            throw new BookNotAvailableException(isbn);
        }
        availableCopies--;
    }

    public void returnBack() {
        availableCopies++;
    }

    public void assignId(Long id) {
        if (this.id != null) {
            throw new IllegalStateException("Id already assigned.");
        }
        this.id = id;
    }

    public boolean isAvailable() {
        return availableCopies > 0;
    }
}
