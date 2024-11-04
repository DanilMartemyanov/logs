package backend.academy.analyzer.services.interfaces;

import backend.academy.analyzer.models.LogRecord;
import java.time.ZonedDateTime;
import java.util.List;

public interface FilteringLogsByDate {
    List<LogRecord> filterLogsByDate(List<LogRecord> logs, ZonedDateTime from, ZonedDateTime to);
}
