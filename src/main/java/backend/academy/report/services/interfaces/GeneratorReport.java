package backend.academy.report.services.interfaces;

import backend.academy.analyzer.services.statistics.StatisticsData;
import backend.academy.report.models.Report;
import java.time.ZonedDateTime;

public interface GeneratorReport {
    String generateReportFormatMarkdown(Report report);
}
