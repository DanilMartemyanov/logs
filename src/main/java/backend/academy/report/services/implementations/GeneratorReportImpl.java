package backend.academy.report.services.implementations;

import backend.academy.report.models.Report;
import backend.academy.report.services.interfaces.GeneratorReport;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Log4j2
public class GeneratorReportImpl implements GeneratorReport {
    @Override
    public String generateReportFormatMarkdown(Report report) {

        StringBuilder reportMarkDown = new StringBuilder();

        String toDate;
        if(report.data().to().isPresent()){
            toDate = report.data().to().toString();
        }else {
            toDate = "-";
        }

        reportMarkDown.append("#### Общая информация\n\n")
            .append("|        Метрика        |                Значение                |\n")
            .append("|:---------------------:|:--------------------------------------:|\n")
            .append("|       Файл(-ы)        | ").append(String.format("%39s",report.fileName())).append("|\n")
            .append("|    Начальная дата     | ").append(String.format("%39s",report.data().from().toString())).append("|\n")
            .append("|     Конечная дата     | ").append(String.format("%39s",toDate)).append("|\n")
            .append("|  Количество запросов  | ").append(String.format("%39d", report.data().totalRequests())).append("|\n")
            .append("| Средний размер ответа | ").append(String.format("%38f", report.data().averageResponseServer())).append("b|\n")
            .append("|   95p размера ответа  | ").append(String.format("%38f", report.data().percentile95())).append("b|\n\n");

        // Запрашиваемые ресурсы
        reportMarkDown.append("#### Запрашиваемые ресурсы\n\n")
            .append("|     Ресурс             | Количество       |\n")
            .append("|:----------------------:|-----------------:|\n");

        report.data().frequentlyRequestResources().forEach((resource, count) -> {
            reportMarkDown.append("| ").append(String.format("%-23s", resource))
                .append("| ").append(String.format("%17d", count))
                .append("|\n");
        });

        reportMarkDown.append("\n#### Коды ответа\n\n")
            .append("| Код |          Имя          | Количество  |\n")
            .append("|:---:|:---------------------:|------------:|\n");

        report.data().frequentlyStatusCode().forEach((code, count) -> {
            reportMarkDown.append("| ").append(String.format("%-3s", code))
                .append(" | ").append(String.format("%-21s", HttpStatusHelper.getHttpStatusName(code)))
                .append(" | ").append(String.format("  %10d", count))
                .append("|\n");
        });

        return reportMarkDown.toString();
    }

    public static void writeReportToFile(Report report, GeneratorReport generatorReport ) {
        Path path = Path.of("src/main/resources/results/resulAnalyze.md");
        try {
            String reportInFile = generatorReport.generateReportFormatMarkdown(report);
            Files.writeString(path, reportInFile);
            log.info("Report generated successfully at: " + path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write report to file: " + path, e);
        }
    }
}
