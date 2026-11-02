package pd10;

import pd10.allocation.AllocationBatch;
import pd10.allocation.OrderAllocationProcessor;
import pd10.allocation.WarehouseState;
import pd10.model.ClientType;
import pd10.model.Order;
import pd10.model.OrderLine;
import pd10.parser.OrderCsvProcessor;
import pd10.parser.OrderGrouper;
import pd10.parser.Result;
import pd10.promotion.PromotionCalculator;
import pd10.report.OrderReport;
import pd10.report.ReportGenerator;

import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        List<String> lines = List.of("O1;C1;GOLD;MILK;3;4,50",
                "O2;C2;std;MILK;5;4.50",
                "O1;C1;GOLD;BREAD;1;3.20",
                "O3;;GOLD;EGGS;10;0.90",
                "O4;C3;SILVER;BEER;-2;3.00",
                "O2;C2;STD;BREAD;1;3.20",
                "O5;C4;GOLD;EGGS;35;0.90",
                "O6;C5;GOLD;BEER;2;3.00",
                "O6;C5;GOLD;EGGS;5;0.90");

        Map<String, Integer> stock = Map.of(
                "MILK", 6,
                "BREAD", 5,
                "EGGS", 30,
                "BEER", 10
        );

        WarehouseState warehouseState = new WarehouseState(stock);

        Map<Boolean, List<Result<OrderLine>>> parsedResults = OrderCsvProcessor.process(lines);

        List<Result<OrderLine>> successfulResults = parsedResults.get(true);

        List<OrderLine> validLines = successfulResults.stream()
                .map(Result::getValue)
                .toList();

        List<Order> orders = OrderGrouper.group(validLines);

        AllocationBatch batch = OrderAllocationProcessor.process(orders, warehouseState);

        Map<ClientType, OrderReport> reports = ReportGenerator.generate(batch.results());

        reports.forEach((tier, report) -> {
            System.out.println(
                    tier + " | Revenue: " + report.revenue()
                            + " | Rejected: " + report.rejectedCount()
            );

            report.topOrders().forEach(order ->
                    System.out.println(
                            order.getOrderId() + " | "
                                    + PromotionCalculator.calculate(order) + " zł"
                    )
            );
        });

        batch.results().forEach(result ->
                System.out.println(result.status())
        );

        System.out.println(batch.warehouse());
        System.out.println(warehouseState.stock());

        parsedResults.get(false)
            .stream()
            .map(Result::getError)
            .forEach(System.out::println);
    }
}
