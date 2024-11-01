package backend.academy.analyzer.services.interfaces;

public interface ParserLogs {
    String parseRemoteAddress();

    String parseRemoteUser();

    String parseTimeLocal();

    String parseRequestType();

    String parsePathResources();

    String parseStatusCode();

    String parseBodyBytesSent();

    String parseHttpRefer();

    String parseHttpUserAgent();
}
