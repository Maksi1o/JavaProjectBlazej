package pd11.exporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pd11.model.Report;

import java.io.IOException;
import java.nio.file.Path;

public class JsonReportExporter implements ReportExporter {

    private final ObjectMapper objectMapper;

    public JsonReportExporter() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void exportToFile(Report report, Path path) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter()
            .writeValue(path.toFile(), report);
    }
}
