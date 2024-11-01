package backend.academy.analyzer.services.implementations;

import backend.academy.analyzer.services.interfaces.FactoryLog;
import backend.academy.analyzer.services.interfaces.ManagerLogs;


public class ManagerImpl implements ManagerLogs {
    private FactoryLog factoryLog;

    public ManagerImpl(FactoryLog factoryLog) {
        this.factoryLog = factoryLog;
    }

}
