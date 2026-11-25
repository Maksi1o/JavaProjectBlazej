package pd15.model;

public record Weather(
        double latitude,
        double longitude,
        CurrentWeather current
) {
}
