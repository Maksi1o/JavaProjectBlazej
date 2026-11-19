package pd14.entity;

import exceptions.BookNotAvailableException;
import lombok.Getter;


@Getter
public class Book {
    private final long id;
    private final String isbn;
    private final String title;
    private final String author;
    private final int year;
    private int availableCopies;

    public Book(long id, String isbn, String title, String author, int year, int availableCopies) {
        if (availableCopies < 0) {
            throw new IllegalArgumentException("Available copies cannot be negative.");
        }
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be blank.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank.");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be blank.");
        }
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.availableCopies = availableCopies;
    }

    public void borrow() {
        if (!isAvailable()) {
            throw new BookNotAvailableException("There are no more copies.");
        }
        availableCopies--;
    }

    public void returnBack() {
        availableCopies++;
    }

    public boolean isAvailable () {
        return availableCopies > 0;
    }
}
