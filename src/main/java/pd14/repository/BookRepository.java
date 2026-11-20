package pd14.repository;

import pd14.entity.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class BookRepository {
    private final Map<Long, Book> books = new HashMap<>();
    private final AtomicLong idSequence = new AtomicLong();

    public Book save(Book book) {
        if (book.getId() == null) {
            book.assignId(idSequence.incrementAndGet());
        }
        books.put(book.getId(), book);
        return book;
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    public List<Book> findAll() {
        return List.copyOf(books.values());
    }

    public Optional<Book> findByIsbn(String isbn) {
        return books.values().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }
}
