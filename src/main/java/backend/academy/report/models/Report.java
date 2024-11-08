package backend.academy.report.models;

import backend.academy.analyzer.services.statistics.StatisticsData;
import lombok.Getter;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
public class Report {
    private List<String> fileName;
    private StatisticsData data;
    private ZonedDateTime from;
    private ZonedDateTime to;

    public Report(List<String> fileName, StatisticsData data, ZonedDateTime from, ZonedDateTime to) {
        this.fileName = fileName;
        this.data = data;
        this.from = from;
        this.to = to;
    }
}
