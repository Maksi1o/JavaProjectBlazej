package pd15.gateway;

import com.fasterxml.jackson.core.type.TypeReference;
import pd15.http.HttpSender;
import pd15.http.JsonParser;
import pd15.model.Post;
import pd15.model.Todo;
import pd15.model.user.User;

import java.util.List;

public class JsonPlaceholderGateway {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users/";

    private final HttpSender httpSender;
    private final JsonParser jsonParser;

    public JsonPlaceholderGateway(HttpSender httpSender, JsonParser jsonParser) {
        this.httpSender = httpSender;
        this.jsonParser = jsonParser;
    }

    public User fetchUser(Long userId) {
        return jsonParser.parse(httpSender.sendGet(BASE_URL + userId), User.class);
    }

    public List<Todo> fetchTodos(Long userId) {
        return jsonParser.parse(httpSender.sendGet(BASE_URL + userId + "/todos"), new TypeReference<>() {
        });
    }

    public List<Post> fetchPosts(Long userId) {
        return jsonParser.parse(httpSender.sendGet(BASE_URL + userId + "/posts"), new TypeReference<>() {
        });
    }
}
