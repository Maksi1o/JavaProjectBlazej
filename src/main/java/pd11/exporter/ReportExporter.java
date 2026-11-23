package pd11.exporter;

import pd11.model.Report;

import java.io.IOException;
import java.nio.file.Path;

public interface ReportExporter {

    ReportExporter JSON_EXPORTER = new JsonReportExporter();
    ReportExporter CSV_EXPORTER = new CsvReportExporter();

    void exportToFile(Report report, Path path) throws IOException;
}
