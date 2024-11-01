package backend.academy;

import lombok.experimental.UtilityClass;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        String logLine = "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        String remoteAddress = "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        String logPattern = "(?<ip>\\d{1,3}(?:\\.\\d{1,3}){3})\\s-\\s(?<remoteUser>\\S+)\\s\\[(?<timeLocal>[^\\]]+)]\\s\"(?<requestType>\\S+)\\s(?<pathResources>[^\\s]+)\\sHTTP/\\d\\.\\d\"\\s(?<statusCode>\\d{3})\\s(?<bodyBytesSent>\\d+)\\s\"(?<referer>[^\"]*)\"\\s\"(?<userAgent>[^\"]*)\"";
        Pattern pattern = Pattern.compile(logPattern);
        Matcher matcher = pattern.matcher(remoteAddress);

    }
}
