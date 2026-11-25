package pd15.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrentWeather(
        String time,
        @JsonProperty("temperature_2m") double temperature,
        @JsonProperty("wind_speed_10m") double windSpeed
) {
}
