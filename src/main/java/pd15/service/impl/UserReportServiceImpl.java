package pd15.service.impl;

import io.vavr.control.Try;
import pd15.dto.UserReport;
import pd15.gateway.JsonPlaceholderGateway;
import pd15.gateway.OpenMeteoGateway;
import pd15.model.Post;
import pd15.model.Todo;
import pd15.model.Weather;
import pd15.model.user.User;
import pd15.service.UserReportService;
import pd15.validator.Validation;

import java.util.Comparator;
import java.util.List;

public class UserReportServiceImpl implements UserReportService {

    private static final int LAST_POSTS_LIMIT = 3;

    private final JsonPlaceholderGateway jsonPlaceholderGateway;
    private final OpenMeteoGateway openMeteoGateway;

    public UserReportServiceImpl(JsonPlaceholderGateway jsonPlaceholderGateway, OpenMeteoGateway openMeteoGateway) {
        this.jsonPlaceholderGateway = jsonPlaceholderGateway;
        this.openMeteoGateway = openMeteoGateway;
    }

    @Override
    public UserReport getUserReport(Long userId) {
        Validation.requirePositiveId(userId);
        User user = jsonPlaceholderGateway.fetchUser(userId);
        List<Todo> incompleteTodos = Try.of(() -> jsonPlaceholderGateway.fetchTodos(userId))
                .onFailure(e -> System.out.println("Todos API failed: " + e.getMessage()))
                .getOrElse(List.of())
                .stream()
                .filter(todo -> !todo.completed())
                .toList();
        List<Post> lastPosts = Try.of(() -> jsonPlaceholderGateway.fetchPosts(userId))
                .onFailure(e -> System.out.println("Posts API failed: " + e.getMessage()))
                .getOrElse(List.of())
                .stream()
                .sorted(Comparator.comparing(Post::id).reversed())
                .limit(LAST_POSTS_LIMIT)
                .toList();
        Weather weather = Try.of(() -> openMeteoGateway.fetchWeather(user.address().geo()))
                .onFailure(e -> System.out.println("Weather API failed: " + e.getMessage()))
                .getOrNull();
        return new UserReport(user, incompleteTodos, lastPosts, weather);
    }
}
