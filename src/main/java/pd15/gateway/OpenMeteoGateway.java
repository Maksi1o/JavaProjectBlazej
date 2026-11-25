package pd15.gateway;

import pd15.http.HttpSender;
import pd15.http.JsonParser;
import pd15.model.Weather;
import pd15.model.user.Geo;

public class OpenMeteoGateway {

    private static final String BASE_URL = "https://api.open-meteo.com/v1/forecast";
    private static final String CURRENT_PARAMS = "&current=temperature_2m,wind_speed_10m";

    private final HttpSender httpSender;
    private final JsonParser jsonParser;

    public OpenMeteoGateway(HttpSender httpSender, JsonParser jsonParser) {
        this.httpSender = httpSender;
        this.jsonParser = jsonParser;
    }

    public Weather fetchWeather(Geo geo) {
        String url = BASE_URL + "?latitude=" + geo.lat() + "&longitude=" + geo.lng() + CURRENT_PARAMS;
        return jsonParser.parse(httpSender.sendGet(url), Weather.class);
    }
}
