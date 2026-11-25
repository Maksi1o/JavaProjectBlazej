package pd15.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import pd15.exception.ResponseParsingException;

public class JsonParser {

    private final ObjectMapper objectMapper;

    public JsonParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T parse(String json, Class<T> type) {
        return Try.of(() -> objectMapper.readValue(json, type))
                .getOrElseThrow(ResponseParsingException::new);
    }

    public <T> T parse(String json, TypeReference<T> type) {
        return Try.of(() -> objectMapper.readValue(json, type))
                .getOrElseThrow(ResponseParsingException::new);
    }
}
