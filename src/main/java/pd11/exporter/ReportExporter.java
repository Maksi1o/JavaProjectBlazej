package pd11.exporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pd11.model.Report;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReportExporter {

    public void exportJson(Report report, Path path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        objectMapper.writerWithDefaultPrettyPrinter()
            .writeValue(path.toFile(), report);
    }

    public void exportCsv(Report report, Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("category,revenue,average_price");

        report.categories().stream()
            .map(category -> String.join(",",
                category.category(),
                category.revenue().toPlainString(),
                category.averagePrice().toPlainString()
            ))
            .forEach(lines::add);

        Files.write(path, lines, StandardCharsets.UTF_8);
    }
}
