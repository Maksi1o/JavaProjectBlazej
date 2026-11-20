package pd14.service.impl;

import lombok.RequiredArgsConstructor;
import pd14.dto.BookDto;
import pd14.dto.CreateBookRequest;
import pd14.entity.Book;
import pd14.exceptions.BookAlreadyExistsException;
import pd14.exceptions.BookNotFoundException;
import pd14.mapper.BookMapper;
import pd14.repository.BookRepository;
import pd14.service.BookService;
import pd14.validator.Validation;

import java.util.List;

@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public BookDto addBook(CreateBookRequest request) {
        bookRepository.findByIsbn(request.isbn())
                .ifPresent(book -> {
                    throw new BookAlreadyExistsException(request.isbn());
                });
        return BookMapper.toDto(bookRepository.save(
                new Book(request.isbn(), request.title(), request.author(), request.year(), request.availableCopies())));
    }

    @Override
    public BookDto findByIsbn(String isbn) {
        return bookRepository
                .findByIsbn(isbn)
                .map(BookMapper::toDto)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    @Override
    public List<BookDto> findAvailable() {
        return bookRepository.findAll().stream()
                .filter(Book::isAvailable)
                .map(BookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDto> search(String query) {
        String normalizedQuery = Validation.requireNotBlank(query, "query").toLowerCase();
        return bookRepository.findAll().stream()
                .filter(book -> matches(book, normalizedQuery))
                .map(BookMapper::toDto)
                .toList();
    }

    private boolean matches(Book book, String normalizedQuery) {
        return book.getTitle().toLowerCase().contains(normalizedQuery)
                || book.getAuthor().toLowerCase().contains(normalizedQuery);
    }
}
