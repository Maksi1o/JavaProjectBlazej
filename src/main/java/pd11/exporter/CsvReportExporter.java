package pd11.exporter;

import pd11.model.CategorySummary;
import pd11.model.Report;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvReportExporter implements ReportExporter {

    private static final String HEADER = "category,revenue,average_price";
    private static final String SEPARATOR = ",";
    private static final String QUOTE = "\"";

    @Override
    public void export(Report report, Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(HEADER);

        report.categories().stream()
            .map(this::toCsvLine)
            .forEach(lines::add);

        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    private String toCsvLine(CategorySummary category) {
        return String.join(SEPARATOR,
            escape(category.category()),
            category.revenue().toPlainString(),
            category.averagePrice().toPlainString()
        );
    }

    private String escape(String value) {
        boolean needsQuoting = value.contains(SEPARATOR) || value.contains(QUOTE)
            || value.contains("\n") || value.contains("\r");
        if (!needsQuoting) {
            return value;
        }
        return QUOTE + value.replace(QUOTE, QUOTE + QUOTE) + QUOTE;
    }
}
