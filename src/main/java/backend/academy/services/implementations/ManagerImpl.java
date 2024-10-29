package backend.academy.services.implementations;

import backend.academy.services.interfaces.ManagerLogs;
import backend.academy.services.interfaces.ParserLogs;

public class ManagerImpl implements ManagerLogs {
    private ParserLogs parserLogs;

    public ManagerImpl(ParserLogs parserLogs) {
        this.parserLogs = parserLogs;
    }

    @Override
    public String parseRemoteAddress(String lineText) {
         return parserLogs.parseRemoteAddress(lineText);

    }

    @Override
    public String parseRemoteUser(String lineText) {
        return parserLogs.parseRemoteUser(lineText);
    }

    @Override
    public String parseTimeLocal(String lineText) {
        return parserLogs.parseTimeLocal(lineText);
    }

    @Override
    public String parseRequestType(String lineText) {
        return parserLogs.parseRequestType(lineText);
    }

    @Override
    public String parseStatusCode(String lineText) {
        return parserLogs.parseStatusCode(lineText);
    }

    @Override
    public String parseBodyBytesSent(String lineText) {
        return parserLogs.parseBodyBytesSent(lineText);
    }

    @Override
    public String parseHttpRefer(String lineText) {
        return parserLogs.parseHttpRefer(lineText);
    }

    @Override
    public String parseHttpUserAgent(String lineText) {
        return parserLogs.parseHttpUserAgent(lineText);
    }
}
