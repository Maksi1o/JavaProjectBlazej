package pd15;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import pd15.user.User;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class UserReport {
    private final User user;
    private final List<Todo> incompleteTodos;
    private final List<Post> lastPosts;
    private final Weather weather;
}
