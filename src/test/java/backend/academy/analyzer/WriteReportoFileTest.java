package backend.academy.analyzer;

import backend.academy.analyzer.constant.PatternConstant;
import backend.academy.analyzer.models.LogRecord;
import backend.academy.analyzer.services.formatters.DateFormatterImpl;
import backend.academy.analyzer.services.implementations.FactoryLogsImpl;
import backend.academy.analyzer.services.implementations.ReaderFile;
import backend.academy.analyzer.services.interfaces.DateFormatter;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import backend.academy.analyzer.services.interfaces.Reader;
import backend.academy.analyzer.services.statistics.CalculatingStatistics;
import backend.academy.analyzer.services.statistics.CalculatingStatisticsImpl;
import backend.academy.report.FileManager;
import backend.academy.report.models.Report;
import backend.academy.report.services.implementations.GeneratorReportMarkdown;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class WriteReportoFileTest {
    @Test
    void writeReportToFile() throws IOException {
        GeneratorReportMarkdown generatorReport = new GeneratorReportMarkdown();
        FactoryLog factoryLog = new FactoryLogsImpl();
        Reader reader = new ReaderFile(factoryLog);
        String pathLogsTest = "src/test/java/backend/academy/resources/logs/";
        Stream<LogRecord> logs = reader.read(pathLogsTest);
        List<String> fileNames = FileManager.getFileNamesInDirectory(pathLogsTest);

        CalculatingStatistics calculatingStatistics = new CalculatingStatisticsImpl();
        DateFormatter dateFormatter = new DateFormatterImpl();
        ZonedDateTime from =
            dateFormatter.formatStringToDate("17/May/2015:08:05:32 +0000", PatternConstant.PATTERNZONE);
        Report report = new Report(fileNames,
            calculatingStatistics.getStatistic(logs, ZonedDateTime.parse("2015-05-17T08:05:32Z"),
                Optional.ofNullable(null)));
        String format = "md";
        FileManager.writeReportToFile(report, format, generatorReport);
        Path path = Paths.get("resultAnalyze." + format);
        Assertions.assertTrue(Files.exists(path));

    }

    @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(Paths.get("resultAnalyze.md"));
    }
}
