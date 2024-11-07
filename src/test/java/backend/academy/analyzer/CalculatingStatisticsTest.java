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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.net.http.HttpClient;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CalculatingStatisticsTest {
    @Test
    void getFrequentlyRequestsResourcesTest() {
        FactoryLog factoryLog = new FactoryLogsImpl();
        Reader reader = new ReaderImpl(factoryLog);
        HttpClient httpClient = HttpClient.newHttpClient();
        String link = "**/resources/logs/logs.txt";
        Stream<LogRecord> logRecordStream = reader.readFile(link);
        CalculatingStatistics calculatingStatistics = new CalculatingStatisticsImpl();
        StatisticsData statisticsData = calculatingStatistics.getStatistic(logRecordStream);
        System.out.println(statisticsData.totalRequests());
        Assertions.assertNotNull(statisticsData.frequentlyRequestResources());
        Assertions.assertNotNull(statisticsData.frequentlyStatusCode());
        Assertions.assertNotNull(statisticsData);
    }


}
