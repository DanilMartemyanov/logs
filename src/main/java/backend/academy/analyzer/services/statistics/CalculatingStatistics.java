package backend.academy.analyzer.services.statistics;

import backend.academy.analyzer.models.LogRecord;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface CalculatingStatistics {
    int getCountsRequest(Stream<LogRecord> logs);

    Map<String, Long> getFrequentlyRequestsResources(Stream<LogRecord> logs);

    List<String> getFrequentlyStatusCode(Stream<LogRecord> logs);

    String getMediumSizeAnswerServer(Stream<LogRecord> logs);


}
