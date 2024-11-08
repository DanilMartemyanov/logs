package backend.academy.analyzer;

import backend.academy.analyzer.models.LogRecord;
import backend.academy.analyzer.services.implementations.FactoryLogsImpl;
import backend.academy.analyzer.services.implementations.ReaderImpl;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import backend.academy.analyzer.services.interfaces.Reader;
import backend.academy.analyzer.services.statistics.CalculatingStatistics;
import backend.academy.analyzer.services.statistics.CalculatingStatisticsImpl;
import backend.academy.analyzer.services.statistics.StatisticsData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.stream.Stream;

public class CalculatingStatisticsTest {
    @Test
    void getStatisticsTest() {
        FactoryLog factoryLog = new FactoryLogsImpl();
        Reader reader = new ReaderImpl(factoryLog);
        String link = "src/main/resources/logs/";
        Stream<LogRecord> logRecordStream = reader.readFile(link);
        CalculatingStatistics calculatingStatistics = new CalculatingStatisticsImpl();
        StatisticsData statisticsData = calculatingStatistics.getStatistic(logRecordStream);
        System.out.println(statisticsData.totalRequests());
        Assertions.assertNotNull(statisticsData.percentile95());
        Assertions.assertNotNull(statisticsData.frequentlyRequestResources());
        Assertions.assertNotNull(statisticsData.frequentlyStatusCode());
        Assertions.assertNotNull(statisticsData);
    }


}
