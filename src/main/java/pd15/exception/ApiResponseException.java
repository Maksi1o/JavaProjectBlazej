package pd15.exception;

public class ApiResponseException extends ApiException {

    public ApiResponseException(String url, int statusCode) {
        super("Request failed: " + url + ", status: " + statusCode);
    }
}
