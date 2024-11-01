package backend.academy.analyzer.services;

public class PatternConstant {
    public static final String LOGPATTERN = "(?<ip>\\d{1,3}(?:\\.\\d{1,3}){3})\\s-\\s(?<remoteUser>\\S+)\\s\\[(?<timeLocal>[^\\]]+)]\\s\"(?<requestType>\\S+)\\s(?<pathResources>[^\\s]+)\\sHTTP/\\d\\.\\d\"\\s(?<statusCode>\\d{3})\\s(?<bodyBytesSent>\\d+)\\s\"(?<referer>[^\"]*)\"\\s\"(?<userAgent>[^\"]*)\"";

}
