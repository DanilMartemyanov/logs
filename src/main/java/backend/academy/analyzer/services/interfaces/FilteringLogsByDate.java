package backend.academy.analyzer.services.interfaces;

import backend.academy.analyzer.models.LogDto;
import java.time.ZonedDateTime;
import java.util.List;

public interface FilteringLogsByDate {
    List<LogDto> filterLogsByDate(List<LogDto> logs, ZonedDateTime from, ZonedDateTime to);
}
