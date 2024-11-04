package backend.academy.analyzer.services.interfaces;

import backend.academy.analyzer.models.LogRecord;

public interface FactoryLog {
    LogRecord createLogDto(String logsLine);
}
