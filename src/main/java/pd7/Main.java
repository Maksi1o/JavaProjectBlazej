package pd7;

import pd7.model.Destination;
import pd7.model.TransportType;
import pd7.model.Trip;
import pd7.model.User;
import pd7.service.TripFinder;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Trip> trips = List.of(
            new Trip(new Destination("Tokyo", "Japan"),
                TransportType.PLANE, new BigDecimal("5500"), 14),
            new Trip(new Destination("Berlin", "Germany"),
                TransportType.TRAIN, new BigDecimal("800"), 3),
            new Trip(new Destination("Paris", "France"),
                TransportType.PLANE, new BigDecimal("1800"), 5),
            new Trip(new Destination("Prague", "Czech Republic"),
                TransportType.CAR, new BigDecimal("600"), 4),
            new Trip(new Destination("Rome", "Italy"),
                TransportType.PLANE, new BigDecimal("2200"), 7),
            new Trip(new Destination("Vienna", "Austria"),
                TransportType.TRAIN, new BigDecimal("1200"), 3)
        );
        User user1 = new User("Anna", TransportType.TRAIN, new BigDecimal("1000"));
        User user2 = new User("Marek", TransportType.PLANE, new BigDecimal("2000"));
        User user3 = new User("Julia", null, new BigDecimal("700"));
        User user4 = new User("Piotr", TransportType.CAR, null);
        User user5 = new User("Tomasz", null, null);
        User user6 = new User("Kasia", TransportType.PLANE, new BigDecimal("100"));

        TripFinder tripFinder = new TripFinder();

        System.out.println(tripFinder.findBestTrip(user1, trips));
        System.out.println(tripFinder.findBestTrip(user2, trips));
        System.out.println(tripFinder.findBestTrip(user6, trips));
        System.out.println(tripFinder.buildTripDescription(user3, trips));
        System.out.println(tripFinder.findByDestination(trips, "Paris"));
        System.out.println(tripFinder.findBestTrip(user4, trips));
        System.out.println(tripFinder.findBestTrip(user5, trips));
        System.out.println(tripFinder.buildTripDescription(user4, trips));
        System.out.println(tripFinder.buildTripDescription(user5, trips));
    }
}
