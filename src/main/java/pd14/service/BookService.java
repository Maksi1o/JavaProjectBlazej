package pd14.service;

import entity.Book;

import java.util.List;

public interface BookService {

    void addBook(Book book);

    /**
     * @throws exceptions.BookNotFoundException when no book with given ISBN was found.
     */
    Book findByIsbn(String isbn);

    List<Book> findAvailable();

    List<Book> search(String query);

    Book findById (long id);
}
