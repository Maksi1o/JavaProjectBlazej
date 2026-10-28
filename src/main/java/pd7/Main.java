package pd7;


import pd7.model.TransportType;
import pd7.model.Trip;
import pd7.model.User;
import pd7.repository.TripRepository;
import pd7.service.TripFinder;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Anna", TransportType.TRAIN, new BigDecimal("1000"));
        User user2 = new User("Marek", TransportType.PLANE, new BigDecimal("2000"));
        User user3 = new User("Julia", null, new BigDecimal("700"));
        User user4 = new User("Piotr", TransportType.CAR, null);
        User user5 = new User("Tomasz", null, null);
        User user6 = new User("Kasia", TransportType.PLANE, new BigDecimal("100"));

        TripRepository repository = new TripRepository();
        List<Trip> trips = repository.findAll();

        System.out.println(TripFinder.findBestTrip(user1, trips));
        System.out.println(TripFinder.findBestTrip(user2, trips));
        System.out.println(TripFinder.findBestTrip(user6, trips));
        System.out.println(TripFinder.buildTripDescription(user3, trips));
        System.out.println(TripFinder.findByDestination(trips, "Paris"));
        System.out.println(TripFinder.findBestTrip(user4, trips));
        System.out.println(TripFinder.findBestTrip(user5, trips));
        System.out.println(TripFinder.buildTripDescription(user4, trips));
        System.out.println(TripFinder.buildTripDescription(user5, trips));
    }
}
