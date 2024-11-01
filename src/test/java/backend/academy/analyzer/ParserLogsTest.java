package backend.academy.analyzer;

import backend.academy.analyzer.services.PatternConstant;
import backend.academy.analyzer.services.implementations.ParserLogsImpl;
import backend.academy.analyzer.services.interfaces.ParserLogs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserLogsTest {
    @Test
    void getRemoteAddress() {
        String remoteAddress = "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        Pattern pattern = Pattern.compile(PatternConstant.LOGPATTERN);
        Matcher matcher = pattern.matcher(remoteAddress);
        ParserLogs parserLogs = new ParserLogsImpl(matcher);
        Assertions.assertEquals("GET",parserLogs.parseRequestType());

    }
}
