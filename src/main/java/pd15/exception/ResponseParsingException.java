package pd15.exception;

public class ResponseParsingException extends ApiException {

    public ResponseParsingException(Throwable cause) {
        super("Response could not be parsed: " + cause.getMessage(), cause);
    }
}
