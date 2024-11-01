package backend.academy.analyzer.services.formatters;

import backend.academy.analyzer.services.interfaces.DateFormatter;
import lombok.extern.log4j.Log4j2;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Log4j2
public class DateFormatterImpl implements DateFormatter {
    private DateTimeFormatter formatter;

    public ZonedDateTime formatStringToDate(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
        try{
            return ZonedDateTime.parse(date, formatter);
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }


}
