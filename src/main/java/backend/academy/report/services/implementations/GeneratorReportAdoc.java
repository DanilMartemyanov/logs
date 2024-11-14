package backend.academy.report.services.implementations;

import backend.academy.report.models.Report;
import backend.academy.report.services.interfaces.GeneratorReport;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressWarnings({"checkstyle:MultipleStringLiterals"})
@SuppressFBWarnings("UCPM_USE_CHARACTER_PARAMETERIZED_METHOD")
public class GeneratorReportAdoc implements GeneratorReport {
    @Override
    public String generateReport(Report report) {
        StringBuilder reportAdoc = new StringBuilder();

        String toDate;
        if (report.data().to().isPresent()) {
            toDate = report.data().to().toString();
        } else {
            toDate = "-";
        }

        reportAdoc.append("= Общая информация\n\n")
            .append("|===\n")
            .append("| Метрика              |       Значение\n")
            .append("| Файл(-ы)             | ").append(String.format("%s", report.fileName())).append("\n")
            .append("| Начальная дата       | ").append(report.data().from()).append("\n")
            .append("| Конечная дата        | ").append(toDate).append("\n")
            .append("| Количество запросов  | ").append(report.data().totalRequests()).append("\n")
            .append("| Средний размер ответа| ").append(String.format("%.6fb", report.data().averageResponseServer()))
            .append("\n")
            .append("| 95p размера ответа   | ").append(String.format("%.6fb", report.data().percentile95()))
            .append("\n")
            .append("|===\n\n");

        reportAdoc.append("== Запрашиваемые ресурсы\n\n")
            .append("|===\n")
            .append("| Ресурс | Количество\n");

        report.data().frequentlyRequestResources().forEach((resource, count) -> {
            reportAdoc.append("| ").append(resource)
                .append(" | ").append(count)
                .append("\n");
        });
        reportAdoc.append("|===\n\n");

        reportAdoc.append("== Коды ответа\n\n")
            .append("|===\n")
            .append("| Код |").append(String.format("%10s", "Имя")).append(String.format("%29s", "|Количество\n"));

        report.data().frequentlyStatusCode().forEach((code, count) -> {
            reportAdoc.append("| ").append(code)
                .append(" | ").append(String.format("%25s", HttpStatusHelper.getHttpStatusName(code)))
                .append(" | ").append(count)
                .append("\n");
        });

        reportAdoc.append("|===\n");
        return reportAdoc.toString();
    }
}
