package backend.academy.analyzer.services.statistics;

import backend.academy.analyzer.models.LogRecord;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalculatingStatisticsImpl implements CalculatingStatistics {
    @Override
    public int getCountsRequest(Stream<LogRecord> logs) {
        return logs.toList().size();
    }

    @Override
    public Map<String, Long> getFrequentlyRequestsResources(Stream<LogRecord> logs) {
        return getSortedFrequentlyRequestsResources(
            logs.collect(Collectors.groupingBy(LogRecord::pathResources, Collectors.counting())));
    }

    @Override
    public List<String> getFrequentlyStatusCode(Stream<LogRecord> logs) {
        return List.of();
    }

    @Override
    public String getMediumSizeAnswerServer(Stream<LogRecord> logs) {
        return "";
    }

    private static Map<String, Long> getSortedFrequentlyRequestsResources(Map<String, Long> frequentlyRequests) {
        return frequentlyRequests.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                TreeMap::new
            ));
    }
}
