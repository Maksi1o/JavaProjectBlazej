package pd14.exceptions;

public class BookNotFoundException extends LibraryException {
    public BookNotFoundException(Long id) {
        super("Book with id " + id + " was not found");
    }

    public BookNotFoundException(String isbn) {
        super("Book with isbn " + isbn + " was not found");
    }
}
