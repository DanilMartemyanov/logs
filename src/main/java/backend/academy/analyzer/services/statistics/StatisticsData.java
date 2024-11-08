package backend.academy.analyzer.services.statistics;

import java.util.Map;

public record StatisticsData(
    Map<String, Long> frequentlyRequestResources,
    Map<String, Long> frequentlyStatusCode,
    long totalRequests,
    double averageResponseServer,
    double percentile95
) {
}
