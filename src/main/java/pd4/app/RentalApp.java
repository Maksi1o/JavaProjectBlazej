package pd4.app;

import pd3.model.*;
import pd3.service.RentalStatus;
import pd3.util.ResourceNameComparator;
import pd4.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RentalApp {
    public static void main(String[] args) {
        Kayak kayak = new Kayak(50, 1, "Kayak#1", ResourceType.KAYAK, 1);
        Kayak kayak1 = new Kayak(50, 2, "Kayak#2", ResourceType.KAYAK, 3);

        PedalBoat pedalBoat = new PedalBoat(25, 3, "PedalBoat#1", ResourceType.PEDALBOAT, false);
        PedalBoat pedalBoat1 = new PedalBoat(25, 4, "PedalBoat#2", ResourceType.PEDALBOAT, true);

        Rental rental = new Rental(3, RentalStatus.ACTIVE, kayak);
        Rental rental1 = new Rental(5, RentalStatus.COMPLETED, kayak1);
        Rental rental2 = new Rental(7, RentalStatus.CANCELLED, pedalBoat);
        Rental rental3 = new Rental(4, RentalStatus.ACTIVE, pedalBoat1);

        RentalSystem rentalSystem = new RentalSystem();
        rentalSystem.addRental(rental);
        rentalSystem.addRental(rental1);
        rentalSystem.addRental(rental2);
        rentalSystem.addRental(rental3);

        System.out.println(rentalSystem.getSummary());

        List<Resource> resources = new ArrayList<>();
        resources.add(0, kayak);
        resources.add(1, kayak1);
        resources.add(2, pedalBoat);
        resources.add(3, pedalBoat1);

        Collections.sort(resources);
        System.out.println("Comparable: ");
        for (Resource resource : resources) {
            System.out.println(resource.getName());
        }
        Collections.sort(resources, new ResourceNameComparator());
        System.out.println("Comparator: ");
        for (Resource resource : resources) {
            System.out.println(resource.getName());

        }
    }
}
