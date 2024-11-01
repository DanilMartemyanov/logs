package backend.academy.analyzer.services.implementations;

import backend.academy.analyzer.services.interfaces.ParserLogs;
import java.util.regex.Matcher;

public class ParserLogsImpl implements ParserLogs {

    private Matcher matcher;

    public ParserLogsImpl(Matcher matcher) {
        this.matcher = matcher;
    }

    @Override
    public String parseRemoteAddress() {
        return findMatch("ip");
    }

    @Override
    public String parseRemoteUser() {
        return findMatch("remoteUser");
    }

    @Override
    public String parseTimeLocal() {
        return findMatch("timeLocal");
    }

    @Override
    public String parseRequestType() {
        return findMatch("requestType");
    }

    @Override
    public String parsePathResources() {
        return findMatch("pathResources");
    }

    @Override
    public String parseStatusCode() {
        return findMatch("statusCode");
    }

    @Override
    public String parseBodyBytesSent() {
        return findMatch("bodyBytesSent");
    }

    @Override
    public String parseHttpRefer() {
        return findMatch("referer");
    }

    @Override
    public String parseHttpUserAgent() {
        return findMatch("userAgent");
    }

    private String findMatch(String nameGroup) {
        if (matcher.find()) {
            return matcher.group(nameGroup);
        }
        throw  new IllegalArgumentException("Could not find match: " + nameGroup);
    }
}
