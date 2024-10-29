package backend.academy.services.interfaces;

public interface ParserLogs {
    String parseRemoteAddress(String lineLogs);

    String parseRemoteUser(String lineLogs);

    String parseTimeLocal(String lineLogs);

    String parseRequestType(String lineLogs);

    String parseStatusCode(String lineLogs);

    String parseBodyBytesSent(String lineLogs);

    String parseHttpRefer(String lineLogs);

    String parseHttpUserAgent(String lineLogs);
}
