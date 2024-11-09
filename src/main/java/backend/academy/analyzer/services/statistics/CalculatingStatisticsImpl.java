package backend.academy.analyzer.services.statistics;

import backend.academy.analyzer.models.LogRecord;
import com.google.common.math.Quantiles;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("checkstyle:MagicNumber")
@SuppressFBWarnings("NAB_NEEDLESS_BOOLEAN_CONSTANT_CONVERSION")
public class CalculatingStatisticsImpl implements CalculatingStatistics {

    @Override
    public StatisticsData getStatistic(Stream<LogRecord> logs, ZonedDateTime from, Optional<ZonedDateTime> to) {
        Map<String, Long> frequentlyRequestResources = new HashMap<>();
        Map<String, Long> frequentlyStatusCode = new HashMap<>();
        long[] totalRequests = {0};
        long[] totalResponseSize = {0};
        double averageResponseServer;
        double percentile95;
        List<Long> sizeResponseServer = new ArrayList<>();

        logs.filter(logRecord -> {
                ZonedDateTime logDate = logRecord.timeLocal();
                return (logDate.isAfter(from) || logDate.isEqual(from))
                    && (to.map(end -> logDate.isBefore(end) || logDate.isEqual(end)).orElse(true));
            })
            .forEach(logRecord -> {
                totalRequests[0]++;

                frequentlyRequestResources.merge(logRecord.pathResources(), 1L, Long::sum);

                frequentlyStatusCode.merge(logRecord.statusCode(), 1L, Long::sum);

                long responseSize = Long.parseLong(logRecord.bodyByteSent());
                totalResponseSize[0] += responseSize;
                sizeResponseServer.add(responseSize);

            });

        if (totalRequests[0] > 0) {
            sizeResponseServer.sort(Long::compare);
            percentile95 = Quantiles.percentiles().index(95).compute(sizeResponseServer);
            averageResponseServer = totalResponseSize[0] / (double) totalRequests[0];
        } else {
            averageResponseServer = 0;
            percentile95 = 0;
        }

        return new StatisticsData(
            getSortedMap(frequentlyRequestResources),
            getSortedMap(frequentlyStatusCode),
            totalRequests[0],
            averageResponseServer,
            percentile95,
            from,
            to
        );

    }

    private static Map<String, Long> getSortedMap(Map<String, Long> frequentlyRequests) {
        return frequentlyRequests.entrySet()
            .stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(3)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new
            ));
    }
}
