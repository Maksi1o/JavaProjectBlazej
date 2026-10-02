package pd4.model;

import pd3.service.RentalStatus;
import pd3.util.RentalSummary;

import java.util.ArrayList;
import java.util.List;

public class RentalSystem {
    final List<Rental> rentals = new ArrayList<>();

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public double getTotalCost() {
        double totalCost = 0;
        for (Rental rental : rentals) {
            double rentalCost = rental.getResource().calculate(rental.getDays());
            totalCost = totalCost + rentalCost;
        }
        return totalCost;
    }

    public int countRentalsByStatus(RentalStatus status) {
        int statusCount = 0;
        for (Rental rental : rentals) {
            RentalStatus rentalStatus = rental.getRentalStatus();
            if (rentalStatus == status) {
                statusCount++;
            }

        }
        return statusCount;

    }

    public RentalSummary getSummary() {
        int totalRentals = rentals.size();
        double totalCost = getTotalCost();
        int activeRentals = countRentalsByStatus(RentalStatus.ACTIVE);
        int completedRentals = countRentalsByStatus(RentalStatus.COMPLETED);
        int cancelledRentals = countRentalsByStatus(RentalStatus.CANCELLED);

        RentalSummary rentalSummary = new RentalSummary(
                totalRentals,
                totalCost,
                activeRentals,
                completedRentals,
                cancelledRentals
        );
        return rentalSummary;
    }
}
