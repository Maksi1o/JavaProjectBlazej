package pd14.exceptions;

public class BookNotAvailableException extends LibraryException {
    public BookNotAvailableException(String isbn) {
        super("Book with isbn " + isbn + " is not available");
    }
}
