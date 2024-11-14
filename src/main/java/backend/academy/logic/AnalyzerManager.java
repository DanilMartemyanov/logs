package backend.academy.logic;

import backend.academy.analyzer.models.LogRecord;
import backend.academy.analyzer.services.implementations.FactoryLogsImpl;
import backend.academy.analyzer.services.implementations.ReaderFile;
import backend.academy.analyzer.services.implementations.ReaderUrl;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import backend.academy.analyzer.services.interfaces.Reader;
import backend.academy.analyzer.services.statistics.CalculatingStatistics;
import backend.academy.analyzer.services.statistics.CalculatingStatisticsImpl;
import backend.academy.report.FileManager;
import backend.academy.report.models.Report;
import backend.academy.report.services.implementations.GeneratorReportAdoc;
import backend.academy.report.services.implementations.GeneratorReportMarkdown;
import backend.academy.report.services.interfaces.GeneratorReport;
import java.net.http.HttpClient;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class AnalyzerManager {
    private GeneratorReport generatorReport;
    private FactoryLog factoryLog = new FactoryLogsImpl();
    private Reader reader;
    private CalculatingStatistics calculatingStatistics = new CalculatingStatisticsImpl();
    private List<String> fileNames;

    public void analyzeLogFile(String pathLog, ZonedDateTime from, ZonedDateTime to, String format) {
        Stream<LogRecord> logRecords;
        if (pathLog.startsWith("https://")) {
            reader = new ReaderUrl(HttpClient.newHttpClient(), factoryLog);
            logRecords = reader.read(pathLog);
            fileNames = new ArrayList<>();
            fileNames.add(pathLog);
        } else {
            reader = new ReaderFile(factoryLog);
            fileNames = FileManager.getFileNamesInDirectory(pathLog);
            logRecords = reader.read(pathLog);
        }

        if ("md".equals(format)) {
            generatorReport = new GeneratorReportMarkdown();
        } else {
            generatorReport = new GeneratorReportAdoc();
        }

        Report report =
            new Report(fileNames, calculatingStatistics.getStatistic(logRecords, from, Optional.ofNullable(to)));

        FileManager.writeReportToFile(report, format, generatorReport);
    }
}
