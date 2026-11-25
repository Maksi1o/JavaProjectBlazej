package pd15.http;

import pd15.exception.ApiConnectionException;
import pd15.exception.ApiResponseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpSender {

    private static final int SUCCESS_MIN = 200;
    private static final int SUCCESS_MAX = 299;

    private final HttpClient httpClient;

    public HttpSender(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String sendGet(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = send(request, url);
        if (!isSuccess(response.statusCode())) {
            throw new ApiResponseException(url, response.statusCode());
        }
        return response.body();
    }

    private HttpResponse<String> send(HttpRequest request, String url) {
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new ApiConnectionException(url, e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiConnectionException(url, e);
        }
    }

    private boolean isSuccess(int statusCode) {
        return statusCode >= SUCCESS_MIN && statusCode <= SUCCESS_MAX;
    }
}
