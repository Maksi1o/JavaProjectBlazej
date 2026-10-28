package pd9.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Event {
    private final String message;
    private final int priority;
}
