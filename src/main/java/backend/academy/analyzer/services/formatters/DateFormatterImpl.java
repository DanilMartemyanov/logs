package backend.academy.analyzer.services.formatters;

import backend.academy.analyzer.constant.PatternConstant;
import backend.academy.analyzer.services.interfaces.DateFormatter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DateFormatterImpl implements DateFormatter {

    public ZonedDateTime formatStringToDate(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
        try {
            return ZonedDateTime.parse(date, formatter);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static ZonedDateTime convertToISO(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PatternConstant.PATTERNUSERDATE, Locale.ENGLISH);

        LocalDate localDate = LocalDate.parse(date, formatter);

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneOffset.UTC);
        return zonedDateTime;
    }

}
