package backend.academy.analyzer;


import backend.academy.analyzer.services.formatters.DateFormatterImpl;
import backend.academy.analyzer.services.interfaces.DateFormatter;
import backend.academy.analyzer.services.statistics.StatisticsData;
import backend.academy.report.models.Report;
import backend.academy.report.services.implementations.GeneratorReportAdoc;
import backend.academy.report.services.implementations.GeneratorReportMarkdown;
import backend.academy.report.services.interfaces.GeneratorReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.when;

public class GeneratorReportTest {
    @Test
    void generateReportMarkdown() {
        StatisticsData statisticsData = Mockito.mock(StatisticsData.class);
        Map<String, Long> resources = Map.of(
            "/download_1", 467312L,
            "/download_2", 46731L,
            "/download_3", 4673L
        );
        Map<String, Long> statusCode= Map.of(
            "404", 50000L,
            "200", 90012312L,
            "500", 6L
        );
        when(statisticsData.frequentlyRequestResources()).thenReturn(resources);
        when(statisticsData.frequentlyStatusCode()).thenReturn(statusCode);
        when(statisticsData.percentile95()).thenReturn(2956.0);
        when(statisticsData.totalRequests()).thenReturn(19137L);
        when(statisticsData.averageResponseServer()).thenReturn(23331.0);
        when(statisticsData.from()).thenReturn(ZonedDateTime.parse("2015-05-17T08:05:32Z"));
        GeneratorReport generatorReport = new GeneratorReportMarkdown();
        DateFormatter dateFormatter = new DateFormatterImpl();
        List<String> fileNames = List.of(
            "log1",
            "log2"
        );
        Report report = new Report(fileNames, statisticsData);
        String reportString  = generatorReport.generateReport(report);
        String expectedReport = "#### Общая информация\n" +
            "\n" +
            "|        Метрика        |                Значение                |\n" +
            "|:---------------------:|:--------------------------------------:|\n" +
            "|       Файл(-ы)        |                            [log1, log2]|\n" +
            "|    Начальная дата     |                    2015-05-17T08:05:32Z|\n" +
            "|     Конечная дата     |                                       -|\n" +
            "|  Количество запросов  |                                   19137|\n" +
            "| Средний размер ответа |                           23331,000000b|\n" +
            "|   95p размера ответа  |                            2956,000000b|\n" +
            "\n" +
            "#### Запрашиваемые ресурсы\n" +
            "\n" +
            "|     Ресурс             | Количество       |\n" +
            "|:----------------------:|-----------------:|\n" +
            "| /download_1            |            467312|\n" +
            "| /download_2            |             46731|\n" +
            "| /download_3            |              4673|\n" +
            "\n" +
            "#### Коды ответа\n" +
            "\n" +
            "| Код |          Имя          | Количество  |\n" +
            "|:---:|:---------------------:|------------:|\n" +
            "| 500 | Internal Server Error |            6|\n" +
            "| 404 | Not Found             |        50000|\n"+
            "| 200 | OK                    |     90012312|";



        Assertions.assertNotEquals(expectedReport, reportString, "Отчет не соответствует ожидаемому формату");
    }
}
