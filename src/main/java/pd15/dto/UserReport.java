package pd15.dto;

import pd15.model.Post;
import pd15.model.Todo;
import pd15.model.Weather;
import pd15.model.user.User;

import java.util.List;

public record UserReport(
        User user,
        List<Todo> incompleteTodos,
        List<Post> lastPosts,
        Weather weather
) {
}
