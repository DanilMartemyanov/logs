package backend.academy;

import backend.academy.analyzer.services.formatters.DateFormatterImpl;
import backend.academy.logic.AnalyzerManager;
import backend.academy.logic.CommandLineArgs;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.text.ParseException;
import java.time.ZonedDateTime;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@UtilityClass
@SuppressFBWarnings({"PATH_TRAVERSAL_IN", "BED_BOGUS_EXCEPTION_DECLARATION"})
@Log4j2
public class Main {
    public static void main(String[] args) {
        try {
            AnalyzerManager manager = new AnalyzerManager();
            CommandLineArgs userArgs = CommandLineArgs.parseArguments(args);
            String path = userArgs.path();
            ZonedDateTime from = DateFormatterImpl.convertToISO(userArgs.from());
            String to = userArgs.to();
            ZonedDateTime toDate;
            if (to != null) {
                toDate = DateFormatterImpl.convertToISO(userArgs.to());
            } else {
                toDate = null;
            }
            String format = userArgs.format();
            manager.analyzeLogFile(path, from, toDate, format);
        } catch (Exception e) {
            log.error("некорректный ввод параметров", e);
        }

    }
}
