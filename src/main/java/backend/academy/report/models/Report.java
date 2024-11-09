package backend.academy.report.models;

import backend.academy.analyzer.services.statistics.StatisticsData;
import lombok.Getter;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
public class Report {
    private List<String> fileName;
    private StatisticsData data;


    public Report(List<String> fileName, StatisticsData data) {
        this.fileName = fileName;
        this.data = data;

    }
}
