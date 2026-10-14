package pd4.model;

public final class Kayak extends Resource {
    private final int numberOfSeats;

    public Kayak(double basePrice, int id, String name, ResourceType resourceType, int numberOfSeats) {
        super(basePrice, id, name, resourceType);
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public double calculate(int days) {
        double calculation;
        if (numberOfSeats > 1) {
            calculation = basePrice * days + (numberOfSeats - 1) * 25;
        } else {
            calculation = basePrice * days;
        }
        return calculation;
    }
}
