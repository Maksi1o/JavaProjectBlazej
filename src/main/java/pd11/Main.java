package pd11;

import pd11.calculator.ReportCalculator;
import pd11.exporter.CsvReportExporter;
import pd11.exporter.JsonReportExporter;
import pd11.exporter.ReportExporter;
import pd11.model.Report;
import pd11.parser.SaleParser;
import pd11.reader.SaleCsvReader;
import pd11.reader.SaleReadResult;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Main {

    private static final Path SALES_FILE = Path.of("src/main/resources/sales.csv");

    public static void main(String[] args) {
        SaleCsvReader reader = new SaleCsvReader(new SaleParser());
        ReportCalculator calculator = new ReportCalculator();

        Map<ReportExporter, Path> exports = Map.of(
            new JsonReportExporter(), Path.of("report.json"),
            new CsvReportExporter(), Path.of("summary.csv")
        );

        try {
            SaleReadResult readResult = reader.read(SALES_FILE);
            readResult.errors().forEach(System.err::println);

            Report report = calculator.generateReport(readResult.sales());

            for (Map.Entry<ReportExporter, Path> export : exports.entrySet()) {
                export.getKey().export(report, export.getValue());
                System.out.println("Report saved: " + export.getValue());
            }

            System.out.println("Processed " + readResult.sales().size() + " sales");
            System.out.println("Skipped " + readResult.skippedCount() + " invalid lines");
        } catch (IOException e) {
            System.err.println("Cannot read or write files: " + e.getMessage());
        }
    }
}
