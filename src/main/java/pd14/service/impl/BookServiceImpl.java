package pd14.service.impl;

import entity.Book;
import exceptions.BookNotFoundException;
import service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookServiceImpl implements BookService {
    private final List<Book> books = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        Objects.requireNonNull(book, "Book cannot be null");
        books.add(book);
    }

    @Override
    public Book findByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("No book matches ISBN: " + isbn));
    }

    @Override
    public List<Book> findAvailable() {
        return books.stream()
                .filter(Book::isAvailable)
                .toList();
    }

    @Override
    public List<Book> search(String query) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase())
                        || book.getAuthor().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }

    @Override
    public Book findById(long id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("No book matches id: " + id));
    }
}
