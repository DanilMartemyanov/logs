package backend.academy.analyzer.services.statistics;

import backend.academy.analyzer.models.LogRecord;
import com.google.common.math.Quantiles;
import java.util.ArrayList;
import java.util.HashMap;
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
    public StatisticsData getStatistic(Stream<LogRecord> logs) {
        Map<String, Long> frequentlyRequestResources = new HashMap<>();
        Map<String, Long> frequentlyStatusCode = new HashMap<>();
        long[] totalRequests = {0};
        long[] totalResponseSize = {0};
        double averageResponseServer;
        double percentile95;
        List<Long> sizeResponseServer = new ArrayList<>();

        logs.forEach(logRecord -> {
            totalRequests[0]++;


            frequentlyRequestResources.merge(logRecord.pathResources(), 1L, Long::sum);


            frequentlyStatusCode.merge(logRecord.statusCode(), 1L, Long::sum);


            long responseSize = Long.parseLong(logRecord.bodyByteSent());
            totalResponseSize[0] += responseSize;
            sizeResponseServer.add(responseSize);

        });

        if (totalRequests[0] > 0){
            sizeResponseServer.sort(Long::compare);
            percentile95 = Quantiles.percentiles().index(95).compute(sizeResponseServer);
            averageResponseServer = totalResponseSize[0] / (double) totalRequests[0];
        }else {
            averageResponseServer = 0;
            percentile95 = 0;
        }



        return new StatisticsData(
            getSortedMap(frequentlyRequestResources),
            getSortedMap(frequentlyStatusCode),
            totalRequests[0],
            averageResponseServer,
            percentile95
        );

    }


    private static Map<String, Long> getSortedMap(Map<String, Long> frequentlyRequests) {
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
