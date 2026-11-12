package pd11.reader;

import pd11.model.Sale;
import pd11.parser.SaleParser;
import pd11.result.OperationResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class SaleCsvReader {

    private static final int HEADER_LINES = 1;

    private final SaleParser parser;

    public SaleCsvReader(SaleParser parser) {
        this.parser = Objects.requireNonNull(parser, "parser cannot be null");
    }

    public SaleReadResult read(Path path) throws IOException {
        List<OperationResult<Sale>> results;
        try (Stream<String> lines = Files.lines(path)) {
            results = lines
                .skip(HEADER_LINES)
                .map(parser::parse)
                .toList();
        }

        List<Sale> sales = results.stream()
            .filter(OperationResult::successful)
            .map(OperationResult::value)
            .toList();

        List<String> errors = results.stream()
            .filter(result -> !result.successful())
            .map(OperationResult::message)
            .toList();

        return new SaleReadResult(sales, errors);
    }
}