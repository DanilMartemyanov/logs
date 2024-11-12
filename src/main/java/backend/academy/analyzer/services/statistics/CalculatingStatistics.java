package backend.academy.analyzer.services.statistics;

import backend.academy.analyzer.models.LogRecord;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.stream.Stream;

public interface CalculatingStatistics {
    StatisticsData getStatistic(Stream<LogRecord> logs, ZonedDateTime from, Optional<ZonedDateTime> to);

}
