package pd15;

public class App {

    public static void main(String[] args) {
        ApiGateway gateway = new ApiGateway();

        try {
            UserReport report = gateway.getUserReport(1L);
            System.out.println("User: " + report.getUser().getUsername());
            System.out.println("Incomplete todos: " + report.getIncompleteTodos().size());
            report.getLastPosts().forEach(post -> System.out.println("Post " + post.getId() + ": " + post.getTitle()));
            System.out.println("Temperature: " + (report.getWeather() == null
                    ? "unavailable"
                    : report.getWeather().getCurrent().getTemperature()));
        } catch (Exception e) {
            System.out.println("Scenario 1 failed: " + e.getMessage());
        }

        try {
            gateway.getUserReport(999L);
            System.out.println("Scenario 2: expected an exception");
        } catch (Exception e) {
            System.out.println("Scenario 2 OK: " + e.getMessage());
        }

        try {
            gateway.getUserReport(null);
            System.out.println("Scenario 3: expected an exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Scenario 3 OK: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Scenario 3 unexpected: " + e.getMessage());
        }
    }
}