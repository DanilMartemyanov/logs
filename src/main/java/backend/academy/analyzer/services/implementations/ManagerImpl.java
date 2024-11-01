package backend.academy.analyzer.services.implementations;

import backend.academy.analyzer.services.interfaces.ManagerLogs;
import backend.academy.analyzer.services.interfaces.ParserLogs;

public class ManagerImpl implements ManagerLogs {
    private ParserLogs parserLogs;

    public ManagerImpl(ParserLogs parserLogs) {
        this.parserLogs = parserLogs;
    }

}
