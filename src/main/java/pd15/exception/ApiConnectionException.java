package pd15.exception;

public class ApiConnectionException extends ApiException {

    public ApiConnectionException(String url, Throwable cause) {
        super("Connection failed: " + url, cause);
    }
}
