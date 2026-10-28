package pd8.userV3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class ExperimentV3 {
    public static void main(String[] args) {
        UserAccountV3 userAccount1 = new UserAccountV3(1L, "12@gmail.com", "Darek");
        UserAccountV3 userAccount2 = new UserAccountV3(1L, "12@gmail.com", "Darek");

        Set<UserAccountV3> testSet = new HashSet<>();

        testSet.add(userAccount1);
        testSet.add(userAccount2);

        int testSize = testSet.size();
        boolean testContains = testSet.contains(new UserAccountV3(1L, "12@gmail.com", "Darek"));

        System.out.println("Size: " + testSize + " Contains: " + testContains);

        Map<UserAccountV3, String> testMap = new HashMap<>();

        testMap.put(userAccount1, "Konto A");
        testMap.put(userAccount2, "Konto B");

        int testSizeMap = testMap.size();
        String getTest = testMap.get(new UserAccountV3(1L, "12@gmail.com", "Darek"));

        System.out.println("Size: " + testSizeMap + " Get: " + getTest);
        // Poprawne nadpisanie equals() i hashCode() zapewnia
        // spójne porównywanie logicznie równych obiektów.
        // HashSet nie dodaje duplikatów (size = 1), a contains()
        // odnajduje nowy obiekt o identycznych danych.
        // HashMap wykorzystuje te metody do porównywania kluczy.
        // Drugie put() zastępuje wartość pod istniejącym kluczem,
        // dlatego size = 1, a get() zwraca "Konto B".
    }
}
