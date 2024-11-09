package backend.academy.analyzer.services.statistics;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;

public record StatisticsData(
    Map<String, Long> frequentlyRequestResources,
    Map<String, Long> frequentlyStatusCode,
    long totalRequests,
    double averageResponseServer,
    double percentile95,
    ZonedDateTime from,
    Optional<ZonedDateTime> to
) {
}
