package backend.academy.analyzer;

import backend.academy.analyzer.constant.PatternConstant;
import backend.academy.analyzer.models.LogRecord;
import backend.academy.analyzer.services.formatters.DateFormatterImpl;
import backend.academy.analyzer.services.implementations.FactoryLogsImpl;
import backend.academy.analyzer.services.implementations.ReaderImpl;
import backend.academy.analyzer.services.interfaces.DateFormatter;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import backend.academy.analyzer.services.interfaces.Reader;
import backend.academy.analyzer.services.statistics.CalculatingStatistics;
import backend.academy.analyzer.services.statistics.CalculatingStatisticsImpl;
import backend.academy.analyzer.services.statistics.StatisticsData;
import backend.academy.report.FileManager;
import backend.academy.report.models.Report;
import backend.academy.report.services.implementations.GeneratorReportImpl;
import backend.academy.report.services.interfaces.GeneratorReport;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WriteReporttoFileTest {
    @Test
    void writeReportToFile() throws IOException {
        GeneratorReportImpl generatorReport = new GeneratorReportImpl();
        FactoryLog factoryLog = new FactoryLogsImpl();
        Reader reader = new ReaderImpl(factoryLog);
        Stream<LogRecord> logs = reader.readFile("src/main/resources/logs/");
        List<String> fileNames = FileManager.getFileNamesInDirectory("src/main/resources/logs/");

        CalculatingStatistics calculatingStatistics = new CalculatingStatisticsImpl();
        DateFormatter dateFormatter = new DateFormatterImpl();
        ZonedDateTime from =
            dateFormatter.formatStringToDate("17/May/2015:08:05:32 +0000", PatternConstant.PATTERNZONE);
        Report report = new Report(fileNames,calculatingStatistics.getStatistic(logs),from, null);
        String string = generatorReport.generateReportFormatMarkdown(report);
        generatorReport.writeReportToFile(report, "src/main/resources/results/test.md");
    }
}
