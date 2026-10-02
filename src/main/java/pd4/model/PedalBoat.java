package pd4.model;

public final class PedalBoat extends Resource {
    private final boolean electric;

    public PedalBoat(double basePrice, int id, String name, ResourceType resourceType, boolean electric) {
        super(basePrice, id, name, resourceType);
        this.electric = electric;
    }

    @Override
    public double calculate(int days) {
        double calculation;
        if (electric) {
            calculation = basePrice * days + (15 * days);
        } else {
            calculation = basePrice * days;
        }
        return calculation;
    }
}
