package pd11.reader;

import pd11.result.OperationResult;
import pd11.model.Sale;
import pd11.parser.SaleParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class SaleCsvReader {

    public List<Sale> read(Path path) throws IOException {
        SaleParser parser = new SaleParser();

        try (Stream<String> lines = Files.lines(path)) {
            return lines
                .skip(1)
                .map(parser::parse)
                .peek(result -> {
                    if (!result.isSuccess()) {
                        System.err.println(result.getMessage());
                    }
                })
                .filter(OperationResult::isSuccess)
                .map(OperationResult::getValue)
                .toList();
        }
    }
}
