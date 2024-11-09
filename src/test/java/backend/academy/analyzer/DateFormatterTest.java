package backend.academy.analyzer;

import backend.academy.analyzer.constant.PatternConstant;
import backend.academy.analyzer.services.formatters.DateFormatterImpl;
import backend.academy.analyzer.services.interfaces.DateFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.ZonedDateTime;

public class DateFormatterTest {
    @Test
    void getDatefromStringTest(){
        DateFormatter dateFormatter = new DateFormatterImpl();
        String textDate = "17/May/2015:08:05:32 +0000";
        ZonedDateTime zonedDateTime = dateFormatter.formatStringToDate(textDate, PatternConstant.PATTERNZONE);
        ZonedDateTime expected = ZonedDateTime.parse("2015-05-17T08:05:32Z") ;

        Assertions.assertEquals(expected, zonedDateTime);
    }
}
