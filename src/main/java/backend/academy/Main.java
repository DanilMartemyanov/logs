package backend.academy;


import backend.academy.analyzer.services.formatters.DateFormatterImpl;
import backend.academy.logic.AnalyzerManager;
import backend.academy.logic.CommandLineArgs;
import java.text.ParseException;
import java.time.ZonedDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) throws ParseException {
        AnalyzerManager manager = new AnalyzerManager();
        CommandLineArgs userArgs = CommandLineArgs.parseArguments(args);
//        DateFormatterImpl dateFormatter = new DateFormatterImpl();
//        String path  = "src/main/resources/logs/";
//        ZonedDateTime from = DateFormatterImpl.convertToISO("2015-May-17");
//        ZonedDateTime to = DateFormatterImpl.convertToISO("2024-Dec-11");
        DateFormatterImpl dateFormatter = new DateFormatterImpl();
        String path  = userArgs.path();
        ZonedDateTime from = DateFormatterImpl.convertToISO(userArgs.from());
        ZonedDateTime to = null;
        String format = userArgs.format();
// "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"
        manager.analyzeLogFile(path, from, to, format);
    }
}
