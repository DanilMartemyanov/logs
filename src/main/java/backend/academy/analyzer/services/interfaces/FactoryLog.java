package backend.academy.analyzer.services.interfaces;

import backend.academy.analyzer.models.LogDto;

public interface FactoryLog {
    LogDto createLogDto(String logsLine);
}
