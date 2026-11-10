package pd8.userV1;

import java.util.HashSet;
import java.util.Set;

public class Experiment {
    public static void main(String[] args) {
        UserAccount userAccount1 = new UserAccount(11L, "1234@gmail.com", "Arek");
        UserAccount userAccount2 = new UserAccount(11L, "1234@gmail.com", "Arek");

        Set<UserAccount> testSet = new HashSet<>();

        testSet.add(userAccount1);
        testSet.add(userAccount2);

        int size = testSet.size();
        boolean equals = userAccount1.equals(userAccount2);

        System.out.println("Size: " + size + " Equals: " + equals);

        // Bez nadpisania equals() i hashCode() obiekty są porównywane
        // na podstawie tożsamości, a nie wartości pól.
        // Dlatego HashSet przechowuje oba obiekty (size = 2),
        // mimo że mają identyczne dane. equals() zwraca false.
    }
}
