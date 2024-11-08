package backend.academy.report.services.implementations;

import backend.academy.analyzer.services.statistics.StatisticsData;
import backend.academy.report.models.Report;
import backend.academy.report.services.interfaces.GeneratorReport;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;

@Log4j2
public class GeneratorReportImpl implements GeneratorReport {
    @Override
    public String generateReportFormatMarkdown(Report report) {
        String toDate;
        if(report.to() == null){
            toDate = "-";
        }else {
            toDate = report.to().toString();
        }
        StringBuilder reportMarkDoqn = new StringBuilder();

        reportMarkDoqn.append("#### Общая информация\n\n")
            .append("|        Метрика        |                Значение                |\n")
            .append("|:---------------------:|:--------------------------------------:|\n")
            .append("|       Файл(-ы)        | ").append(String.format("%39s",report.fileName())).append("|\n")
            .append("|    Начальная дата     | ").append(String.format("%39s",report.from().toString())).append("|\n")
            .append("|     Конечная дата     | ").append(String.format("%39s",toDate)).append("|\n")
            .append("|  Количество запросов  | ").append(String.format("%39d", report.data().totalRequests())).append("|\n")
            .append("| Средний размер ответа | ").append(String.format("%38f", report.data().averageResponseServer())).append("b|\n")
            .append("|   95p размера ответа  | ").append(String.format("%38f", report.data().percentile95())).append("b|\n\n");

        // Запрашиваемые ресурсы
        reportMarkDoqn.append("#### Запрашиваемые ресурсы\n\n")
            .append("|     Ресурс      | Количество  |\n")
            .append("|:---------------:|------------:|\n");

        report.data().frequentlyRequestResources().forEach((resource, count) -> {
            reportMarkDoqn.append("| ").append(String.format(" %-15s", resource))
                .append("| ").append(String.format("  %10d", count))
                .append("|\n");
        });

        reportMarkDoqn.append("\n#### Коды ответа\n\n")
            .append("| Код |          Имя          | Количество  |\n")
            .append("|:---:|:---------------------:|------------:|\n");

        report.data().frequentlyStatusCode().forEach((code, count) -> {
            reportMarkDoqn.append("| ").append(String.format("%-3s", code))
                .append(" | ").append(String.format("%-21s", HttpStatusHelper.getHttpStatusName(code)))
                .append(" | ").append(String.format("  %10d", count))
                .append("|\n");
        });

        return reportMarkDoqn.toString();
    }

    public void writeReportToFile(Report report, String userPath) {
        Path path = Path.of(userPath);
        String reportInFile = this.generateReportFormatMarkdown(report);
        try {
            Files.writeString(path, reportInFile);
            log.info("Report generated successfully at: " + path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write report to file: " + path, e);
        }
    }
}
