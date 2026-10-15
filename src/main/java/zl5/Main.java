package zl5;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OperationResult<String> userResult = OperationResult.success("Jarek");
        OperationResult<Double> doubleResult = OperationResult.failure("Co byś zrobił");
        OperationResult<List<String>> namesResults = OperationResult.success(List.of("Adam", "Waszka G"));

        String user = userResult.getValue();
        Double sum = doubleResult.getValue();
        List<String> names = namesResults.getValue();

        OperationResult<Integer> failedResult = OperationResult.failure("Skoda");

        System.out.println(user);
        System.out.println(sum);
        System.out.println(names);
        System.out.println(failedResult.getMessage());

    }

}
