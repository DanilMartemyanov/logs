package backend.academy.services.interfaces;

import java.util.regex.Pattern;

public interface ManagerLogs {
    String parseRemoteAddress(String lineText);

    String parseRemoteUser(String lineText);

    String parseTimeLocal(String lineText);

    String parseRequestType(String lineText);

    String parseStatusCode(String lineText);

    String parseBodyBytesSent(String lineText);

    String parseHttpRefer(String lineText);

    String parseHttpUserAgent(String lineText);
}
