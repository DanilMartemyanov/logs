package backend.academy.analyzer;

import backend.academy.analyzer.models.LogRecord;
import backend.academy.analyzer.services.implementations.FactoryLogsImpl;
import backend.academy.analyzer.services.implementations.ReaderImpl;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import backend.academy.analyzer.services.interfaces.Reader;
import backend.academy.analyzer.services.statistics.CalculatingStatistics;
import backend.academy.analyzer.services.statistics.CalculatingStatisticsImpl;
import org.junit.jupiter.api.Assertions;
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
        String link =
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        Stream<LogRecord> logRecordStream = reader.readLogsByURL(link, httpClient);
        CalculatingStatistics calculatingStatistics = new CalculatingStatisticsImpl();
        Map<String, Long> logRecords = calculatingStatistics.getFrequentlyRequestsResources(logRecordStream);
        Assertions.assertEquals(30246 ,logRecords.get("/downloads/product_1"));
    }
}
