package pd15;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import pd15.user.Geo;
import pd15.user.User;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;


public class ApiGateway {

    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users/";
    private static final String WEATHER_URL = "https://api.open-meteo.com/v1/forecast";
    private static final String WEATHER_PARAMS = "&current=temperature_2m,wind_speed_10m";
    private static final int LAST_POSTS_LIMIT = 3;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private User fetchUser(Long userId) throws Exception {
        return objectMapper.readValue(sendGet(USERS_URL + userId), User.class);
    }

    private List<Todo> fetchTodos(Long userId) throws Exception {
        return objectMapper.readValue(sendGet(USERS_URL + userId + "/todos"), new TypeReference<List<Todo>>() {
        });
    }

    private List<Post> fetchPosts(Long userId) throws Exception {
        return objectMapper.readValue(sendGet(USERS_URL + userId + "/posts"), new TypeReference<List<Post>>() {
        });
    }

    private Weather fetchWeather(Geo geo) throws Exception {
        return objectMapper.readValue(sendGet(
                WEATHER_URL + "?latitude=" + geo.getLat()
                        + "&longitude=" + geo.getLng() + WEATHER_PARAMS), Weather.class);
    }

    public UserReport getUserReport(Long userId) throws Exception {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User id must be positive");
        }
        User user = fetchUser(userId);
        List<Todo> incompleteTodos = Try.of(() -> fetchTodos(userId))
                .onFailure(e -> System.out.println("Todos API failed: " + e.getMessage()))
                .getOrElse(List.of())
                .stream()
                .filter(todo -> !todo.isCompleted())
                .toList();

        List<Post> lastPosts = Try.of(() -> fetchPosts(userId))
                .onFailure(e -> System.out.println("Posts API failed: " + e.getMessage()))
                .getOrElse(List.of())
                .stream()
                .sorted(Comparator.comparing(Post::getId).reversed())
                .limit(LAST_POSTS_LIMIT)
                .toList();

        Weather weather = Try.of(() -> fetchWeather(user.getAddress().getGeo()))
                .onFailure(e -> System.out.println("Weather API failed: " + e.getMessage()))
                .getOrNull();
        return new UserReport(user, incompleteTodos, lastPosts, weather);
    }

    private String sendGet(String url) throws Exception {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Request failed: " + url + ", status: " + response.statusCode());
        }
        return response.body();
    }
}
