package pd11;

import pd11.calculator.SaleCalculator;
import pd11.exporter.ReportExporter;
import pd11.model.Report;
import pd11.model.Sale;
import pd11.reader.SaleCsvReader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SaleCsvReader reader = new SaleCsvReader();
        SaleCalculator calculator = new SaleCalculator();
        ReportExporter exporter = new ReportExporter();

        try {
            List<Sale> sales = reader.read(Path.of("src/main/resources/sales.csv"));

            Report report = new Report(
                calculator.calculateTotalRevenue(sales),
                calculator.calculateTop3Products(sales),
                calculator.calculateCategorySummaries(sales),
                calculator.calculateBestSalesDay(sales).orElse(null)
            );

            exporter.exportJson(report, Path.of("report.json"));
            exporter.exportCsv(report, Path.of("summary.csv"));

            System.out.println("Processed " + sales.size() + " sales");
            System.out.println("Reports saved: report.json, summary.csv");
        } catch (IOException e) {
            System.err.println("Cannot read or write files: " + e.getMessage());
        }
    }
}
