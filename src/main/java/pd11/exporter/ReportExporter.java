package pd11.exporter;

import pd11.model.Report;

import java.io.IOException;
import java.nio.file.Path;

public interface ReportExporter {

    void export(Report report, Path path) throws IOException;
}
