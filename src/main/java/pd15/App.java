package pd15;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import pd15.config.ObjectMapperFactory;
import pd15.dto.UserReport;
import pd15.gateway.JsonPlaceholderGateway;
import pd15.gateway.OpenMeteoGateway;
import pd15.http.HttpSender;
import pd15.http.JsonParser;
import pd15.service.UserReportService;
import pd15.service.impl.UserReportServiceImpl;

import java.net.http.HttpClient;

public class App {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = ObjectMapperFactory.create();
        HttpSender httpSender = new HttpSender(httpClient);
        JsonParser jsonParser = new JsonParser(objectMapper);
        JsonPlaceholderGateway jsonPlaceholderGateway = new JsonPlaceholderGateway(httpSender, jsonParser);
        OpenMeteoGateway openMeteoGateway = new OpenMeteoGateway(httpSender, jsonParser);
        UserReportService reportService = new UserReportServiceImpl(jsonPlaceholderGateway, openMeteoGateway);

        Try.of(() -> reportService.getUserReport(1L))
                .onSuccess(App::printReport)
                .onFailure(e -> System.out.println("Scenario 1 failed: " + e.getMessage()));

        Try.of(() -> reportService.getUserReport(999L))
                .onSuccess(report -> System.out.println("Scenario 2: expected an exception"))
                .onFailure(e -> System.out.println("Scenario 2 OK: " + e.getMessage()));

        Try.of(() -> reportService.getUserReport(null))
                .onSuccess(report -> System.out.println("Scenario 3: expected an exception"))
                .onFailure(e -> System.out.println("Scenario 3 OK: " + e.getMessage()));
    }

    private static void printReport(UserReport report) {
        System.out.println("User: " + report.user().username());
        System.out.println("Incomplete todos: " + report.incompleteTodos().size());
        report.lastPosts().forEach(post -> System.out.println("Post " + post.id() + ": " + post.title()));
        System.out.println("Temperature: " + (report.weather() == null
                ? "unavailable"
                : report.weather().current().temperature()));
    }
}
