package pd10.parser;

import pd10.model.OrderLine;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderCsvProcessor {

    public static Map<Boolean, List<Result<OrderLine>>> process(List<String> lines) {

        return lines.stream()
            .map(OrderLineParser::parse)
            .collect(Collectors.partitioningBy(Result::isSuccess));
    }
}
