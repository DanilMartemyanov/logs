package backend.academy.analyzer;

import backend.academy.analyzer.models.LogDto;
import backend.academy.analyzer.services.implementations.FactoryLogsImpl;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FactoryLogTest {

    @Test
    void buildLogDto(){
        String remoteAddress = "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        FactoryLog factoryLog = new FactoryLogsImpl();
        LogDto logDto = factoryLog.createLogDto(remoteAddress);
        Assertions.assertNotNull(logDto);
    }
}
