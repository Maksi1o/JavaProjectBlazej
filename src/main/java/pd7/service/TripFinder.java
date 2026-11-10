package pd7.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd7.model.Destination;
import pd7.model.Trip;
import pd7.model.User;

import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.ObjectUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TripFinder {
    public static Optional<Trip> findByDestination(
        List<Trip> trips, String destinationName) {
        for (Trip trip : trips) {

            var matchedDestination = Optional.ofNullable(trip.getDestination())
                .map(Destination::name)
                .filter(destinationName::equals);

            if (matchedDestination.isPresent()) {
                return Optional.of(trip);
            }
        }
        return Optional.empty();
    }

    public static Optional<Trip> findBestTrip(User user, List<Trip> trips) {
        boolean hasCriteria = !ObjectUtils.allNull(
            user.getPreferredTransport(),
            user.getBudget());

        if (!hasCriteria) {
            return Optional.empty();
        }

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

    public static String buildTripDescription(User user, List<Trip> trips) {
        return findBestTrip(user, trips)
            .map(trip -> trip.getDestination() + " " + trip.getTransport() + " " + trip.getPrice())
            .orElse("No trip available");
    }
}
