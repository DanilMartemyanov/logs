package backend.academy.analyzer.services.interfaces;

import java.time.ZonedDateTime;

public interface DateFormatter {
    ZonedDateTime formatStringToDate(String date, String pattern);
}
