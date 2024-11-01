package backend.academy.analyzer.constant;

public class PatternConstant {
    public static final String LOGPATTERN = "(?<ip>\\d{1,3}(?:\\.\\d{1,3}){3})\\s-\\s(?<remoteUser>\\S+)\\s\\[(?<timeLocal>[^\\]]+)]\\s\"(?<requestType>\\S+)\\s(?<pathResources>[^\\s]+)\\sHTTP/\\d\\.\\d\"\\s(?<statusCode>\\d{3})\\s(?<bodyBytesSent>\\d+)\\s\"(?<referer>[^\"]*)\"\\s\"(?<userAgent>[^\"]*)\"";
    public static final String PATTERNZONE = "dd/MMMM/yyyy:HH:mm:ss Z";
    public static final String PATTERNZONEUTC = "dd MMMM yyyy HH:mm:ss z ";
    public static final int REMOTEADDRESS = 1;
    public static final int REMOTEUSER = 2;
    public static final int TIMELOCAL = 3;
    public static final int REQUESTTYPE = 4;
    public static final int PATHRESOURCES = 5;
    public static final int STATUSCODE = 6;
    public static final int BODYBYTESSENT = 7;
    public static final int REFER = 8;
    public static final int USERAGENT = 9;

}
