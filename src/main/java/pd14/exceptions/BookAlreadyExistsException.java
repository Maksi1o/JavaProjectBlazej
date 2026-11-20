package pd14.exceptions;

public class BookAlreadyExistsException extends LibraryException {
    public BookAlreadyExistsException(String isbn) {
        super("Book with ISBN: " + isbn + " already exists");
    }
}
