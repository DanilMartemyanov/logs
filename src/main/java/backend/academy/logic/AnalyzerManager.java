package backend.academy.logic;

import backend.academy.analyzer.models.LogRecord;
import backend.academy.analyzer.services.formatters.DateFormatterImpl;
import backend.academy.analyzer.services.implementations.FactoryLogsImpl;
import backend.academy.analyzer.services.implementations.ReaderImpl;
import backend.academy.analyzer.services.interfaces.DateFormatter;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import backend.academy.analyzer.services.interfaces.Reader;
import backend.academy.analyzer.services.statistics.CalculatingStatistics;
import backend.academy.analyzer.services.statistics.CalculatingStatisticsImpl;
import backend.academy.report.FileManager;
import backend.academy.report.models.Report;
import backend.academy.report.services.implementations.GeneratorReportImpl;
import backend.academy.report.services.interfaces.GeneratorReport;
import java.net.http.HttpClient;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class AnalyzerManager {
    private GeneratorReport generatorReport = new GeneratorReportImpl();
    private FactoryLog factoryLog = new FactoryLogsImpl();
    private Reader reader = new ReaderImpl(factoryLog);
    private CalculatingStatistics calculatingStatistics = new CalculatingStatisticsImpl();
    private List<String> fileNames;

    public void analyzeLogFile(String pathLog, ZonedDateTime from, ZonedDateTime to, String pathSave) {
        Stream<LogRecord> logRecords;
        if (pathLog.startsWith("http")) {
            HttpClient client = HttpClient.newHttpClient();
            logRecords = reader.readLogsByURL(pathLog, client);
            fileNames.add(pathLog);
        } else {
            fileNames = FileManager.getFileNamesInDirectory(pathLog);
            logRecords = reader.readFile(pathLog);
        }

        Report report =
            new Report(fileNames, calculatingStatistics.getStatistic(logRecords, from, Optional.ofNullable(to)));

        GeneratorReportImpl.writeReportToFile(report, pathSave, generatorReport);
    }
}
