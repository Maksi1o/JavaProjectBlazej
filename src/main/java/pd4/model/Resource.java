package pd4.model;

sealed public abstract class Resource implements Comparable<Resource> permits Kayak, PedalBoat {
    private final int id;
    private String name;

    public String getName() {
        return name;
    }

    protected double basePrice;
    private ResourceType resourceType;

    protected Resource(double basePrice, int id, String name, ResourceType resourceType) {
        this.basePrice = basePrice;
        this.id = id;
        this.name = name;
        this.resourceType = resourceType;
    }

    public abstract double calculate(int days);

    @Override
    public int compareTo(Resource other) {
        return Double.compare(basePrice, other.basePrice);
    }
}