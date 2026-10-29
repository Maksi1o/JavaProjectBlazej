package pd9.formatter;

import pd9.model.Event;

import java.util.function.Function;

public class EventFormatters {
    public static final Function<Event, String> MESSAGE_FORMATTER = Event::getMessage;

    public static final Function<Event, String> PRIORITY_FORMATTER = event ->
        "Priority: " + event.getPriority();
}
