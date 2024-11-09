package backend.academy.analyzer;

import backend.academy.analyzer.constant.PatternConstant;
import backend.academy.analyzer.services.formatters.DateFormatterImpl;
import backend.academy.analyzer.services.interfaces.DateFormatter;
import backend.academy.analyzer.services.statistics.StatisticsData;
import backend.academy.report.models.Report;
import backend.academy.report.services.implementations.GeneratorReportImpl;
import backend.academy.report.services.interfaces.GeneratorReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.when;

public class GeneratorReportTest {
    @Test
    void generateReportMarkdown() {
        StatisticsData statisticsData = Mockito.mock(StatisticsData.class);
        Map<String, Long> resources = new HashMap<>();
        resources.put("/download_1", 467312L);
        resources.put("/download_2", 46731L);
        resources.put("/download_3", 4673L);
        Map<String, Long> statusCode= new HashMap<>();
        statusCode.put("404", 50000L);
        statusCode.put("200", 90012312L);
        statusCode.put("500", 6L);
        when(statisticsData.frequentlyRequestResources()).thenReturn(resources);
        when(statisticsData.frequentlyStatusCode()).thenReturn(statusCode);
        when(statisticsData.percentile95()).thenReturn(2956.0);
        when(statisticsData.totalRequests()).thenReturn(19137L);
        when(statisticsData.averageResponseServer()).thenReturn(23331.0);
        GeneratorReport generatorReport = new GeneratorReportImpl();
        DateFormatter dateFormatter = new DateFormatterImpl();
        ZonedDateTime from = dateFormatter.formatStringToDate("17/May/2015:08:05:32 +0000", PatternConstant.PATTERNZONE);
        List<String> fileNames = new ArrayList<>();
        fileNames.add("log1");
        fileNames.add("log2");
        Report report = new Report(fileNames, statisticsData);
        String reportString  = generatorReport.generateReportFormatMarkdown(report);
        Assertions.assertNotNull(reportString);
    }
}
