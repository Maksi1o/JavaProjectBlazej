package pd4.model;

import pd3.service.RentalStatus;

public class Rental {
    private final Resource resource;
    private int days;
    private RentalStatus rentalStatus;

    public int getDays() {
        return days;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public Resource getResource() {
        return resource;
    }

    public Rental(int days, RentalStatus rentalStatus, Resource resource) {
        this.days = days;
        this.rentalStatus = rentalStatus;
        this.resource = resource;
    }
}
