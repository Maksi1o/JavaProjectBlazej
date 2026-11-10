package pd8.userV2;

import java.util.HashSet;
import java.util.Set;

public class ExperimentV2 {
    public static void main(String[] args) {
        UserAccountV2 userAccount1 = new UserAccountV2(10L, "123@gmail.com", "Darek");
        UserAccountV2 userAccount2 = new UserAccountV2(10L, "123@gmail.com", "Darek");

        Set<UserAccountV2> testSet = new HashSet<>();

        testSet.add(userAccount1);
        testSet.add(userAccount2);

        int testSize = testSet.size();
        boolean testContains = testSet.contains(
            new UserAccountV2(10L, "123@gmail.com", "Darek"));

        System.out.println("Size: " + testSize + " Contains: " + testContains);
        // Nadpisanie samego equals() nie wystarcza do poprawnego
        // działania HashSet, ponieważ narusza kontrakt z hashCode().
        // Logicznie równe obiekty mogą mieć różne wartości hash,
        // przez co HashSet może przechowywać duplikaty (size = 2).
        // contains() może zwrócić false mimo logicznej równości obiektów.
    }
}
