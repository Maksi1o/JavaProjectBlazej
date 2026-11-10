package pd10.report;

import pd10.allocation.AllocationResult;
import pd10.allocation.AllocationStatus;
import pd10.model.ClientType;
import pd10.model.Order;
import pd10.promotion.PromotionCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ReportGenerator {

    public static Map<ClientType, OrderReport> generate(List<AllocationResult> results) {
        Map<ClientType, List<AllocationResult>> groupedResults = results.stream()
            .collect(Collectors.groupingBy(
                result -> result.originalOrder().tier()
            ));

        return groupedResults.entrySet()
            .stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> buildReport(
                    entry.getKey(),
                    entry.getValue())

            ));
    }

    private static OrderReport buildReport(ClientType tier, List<AllocationResult> results) {
        long rejectedCount = results.stream()
            .filter(AllocationResult::isRejected)
            .count();

        BigDecimal revenue = results.stream()
            .filter(result ->
                result.status() != AllocationStatus.REJECTED)
            .map(result -> PromotionCalculator.calculate(result.fulfilledOrder()))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Order> topOrders = results.stream()
            .filter(result -> result.status() != AllocationStatus.REJECTED)
            .map(AllocationResult::fulfilledOrder)
            .collect(topTwoCollector());

        return new OrderReport(
            tier,
            revenue,
            rejectedCount,
            topOrders
        );
    }

    private static Collector<Order, List<Order>, List<Order>> topTwoCollector() {

        return Collector.of(
            ArrayList::new,

            (list, order) -> {
                list.add(order);
                list.sort(
                    Comparator.comparing(
                        PromotionCalculator::calculate,
                        Comparator.reverseOrder()
                    )
                );

                if (list.size() > 2) {
                    list.remove(2);
                }
            },

            (left, right) -> {
                left.addAll(right);
                left.sort(
                    Comparator.comparing(
                        PromotionCalculator::calculate,
                        Comparator.reverseOrder()
                    )
                );

                if (left.size() > 2) {
                    left.subList(2, left.size()).clear();
                }

                return left;
            },

            List::copyOf
        );
    }
}
