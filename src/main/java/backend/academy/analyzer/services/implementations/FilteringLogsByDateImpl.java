package backend.academy.analyzer.services.implementations;

import backend.academy.analyzer.models.LogDto;
import backend.academy.analyzer.services.interfaces.FilteringLogsByDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FilteringLogsByDateImpl implements FilteringLogsByDate {
    @Override
    public List<LogDto> filterLogsByDate(List<LogDto> logs, ZonedDateTime from, ZonedDateTime to) {
        return logs
            .stream()
            .filter(logDto ->  logDto.timeLocal().compareTo(from) >=0)
            .filter(logDto -> logDto.timeLocal().compareTo(to) <=0)
            .collect(Collectors.toList());

    }
}
