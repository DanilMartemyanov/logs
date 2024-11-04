package backend.academy.analyzer;

import backend.academy.analyzer.models.LogRecord;
import backend.academy.analyzer.services.implementations.FactoryLogsImpl;
import backend.academy.analyzer.services.implementations.FilteringLogsByDateImpl;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilteringLogsByDateTest {

    @Test
    void filterLogsByDateTest(){
        String log1= "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        String log2= "93.180.71.3 - - [17/May/2017:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";

        FactoryLog factoryLog = new FactoryLogsImpl();
        LogRecord logRecord1 = factoryLog.createLogDto(log1);
        LogRecord logRecord2 = factoryLog.createLogDto(log2);
        List<LogRecord> logs = new ArrayList<>();
        logs.add(logRecord1);
        logs.add(logRecord2);
        FilteringLogsByDateImpl filteringLogsByDate = new FilteringLogsByDateImpl();
        List<LogRecord> filterLogs = filteringLogsByDate.filterLogsByDate(logs, ZonedDateTime.parse("2015-06-17T08:05:32Z"), ZonedDateTime.parse("2017-06-17T08:05:32Z"));
        LogRecord filterLog = filterLogs.get(0);
        Assertions.assertTrue(ZonedDateTime.parse("2017-06-17T08:05:32Z").isAfter(filterLog.timeLocal()));
    }
}
