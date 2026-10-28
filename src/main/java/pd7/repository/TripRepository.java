package pd7.repository;

import pd7.model.Destination;
import pd7.model.TransportType;
import pd7.model.Trip;

import java.math.BigDecimal;
import java.util.List;

public class TripRepository {
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

    public List<Trip> findAll() {
        return trips;
    }
}
