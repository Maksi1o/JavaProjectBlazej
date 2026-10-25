package pd7.service;

import pd7.model.Trip;
import pd7.model.User;

import java.util.List;
import java.util.Optional;

public class TripFinder {
    public Optional<Trip> findByDestination(List<Trip> trips, String destinationName) {
        for (Trip trip : trips) {
            if (trip.getDestination() != null && trip.getDestination().name().equals(destinationName))
                return Optional.of(trip);
        }
        return Optional.empty();
    }

    public Optional<Trip> findBestTrip(User user, List<Trip> trips) {
        boolean hasCriteria = user.getPreferredTransport() != null || user.getBudget() != null;

        if (!hasCriteria)
            return Optional.empty();

        for (Trip trip : trips) {
            boolean transportMatches = user.getPreferredTransport() == null
                || user.getPreferredTransport() == trip.getTransport();

            boolean budgetMatches = user.getBudget() == null
                || trip.getPrice().compareTo(user.getBudget()) <= 0;

            if (transportMatches && budgetMatches)
                return Optional.of(trip);
        }

        return Optional.empty();
    }

    public String buildTripDescription(User user, List<Trip> trips) {
        return findBestTrip(user, trips)
            .map(trip -> trip.getDestination() + " " + trip.getTransport() + " " + trip.getPrice())
            .orElse("No trip available");
    }
}
