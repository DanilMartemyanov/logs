package backend.academy.analyzer.constant;

import lombok.experimental.UtilityClass;


@UtilityClass
public class PatternConstant {
    public static final String LOGPATTERN =
        "(?<ip>\\d{1,3}(?:\\.\\d{1,3}){3})\\s-\\s(?<remoteUser>\\S+)\\s\\[(?<timeLocal>[^\\]]+)]\\s\""
            + "(?<requestType>\\S+)\\s(?<pathResources>[^\\s]+)\\sHTTP/\\d\\.\\d\"\\s(?<statusCode>\\d{3})\\s"
            + "(?<bodyBytesSent>\\d+)\\s\"(?<referer>[^\"]*)\"\\s\"(?<userAgent>[^\"]*)\"";
    public static final String PATTERNZONE = "dd/MMM/yyyy:HH:mm:ss Z";
    public static final String PATTERNUSERDATE = "yyyy-MMM-dd";
    public static final int PERCENTILE95 = 95;
    public static final int TOP3 = 3;


}
