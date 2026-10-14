package pd4.util;

public record RentalSummary(
        int totalRentals,
        double totalCost,
        int activeRentals,
        int completedRentals,
        int cancelledRentals
) {

}
