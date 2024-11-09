package backend.academy.report.services.interfaces;


import backend.academy.report.models.Report;


public interface GeneratorReport {
    String generateReportFormatMarkdown(Report report);
}
