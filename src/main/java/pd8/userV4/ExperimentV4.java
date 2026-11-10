package pd8.userV4;

import java.util.HashSet;
import java.util.Set;

public class ExperimentV4 {
    public static void main(String[] args) {
        UserAccountV4 userAccount1  = new UserAccountV4(2L, "11@gmail.com", "Darek", "ACTIVE");

        Set<UserAccountV4> testSet = new HashSet<>();

        testSet.add(userAccount1);
        userAccount1.setStatus("BLOCKED");

        boolean testContains = testSet.contains(userAccount1);
        boolean testRemove = testSet.remove(userAccount1);

        System.out.println("Contains: " + testContains + " Remove: " + testRemove);
        // Pole status uczestniczy w equals() i hashCode().
        // Po dodaniu obiektu do HashSet zmiana statusu powoduje
        // zmianę jego hashCode(), ale nie zmienia miejsca,
        // w którym HashSet wcześniej zapisał obiekt.
        // Dlatego contains() i remove() zwracają false,
        // mimo że obiekt nadal znajduje się w kolekcji.
        // Mutowalne pola uczestniczące w hashCode() są niebezpieczne,
        // ponieważ mogą uniemożliwić odnalezienie obiektu.
    }
}
