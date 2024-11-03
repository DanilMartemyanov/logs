package backend.academy.analyzer.services.implementations;

import backend.academy.analyzer.models.LogDto;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import backend.academy.analyzer.services.interfaces.Reader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ReaderImpl implements Reader {
    private FactoryLog factoryLog;

    public ReaderImpl(FactoryLog factoryLog) {
        this.factoryLog = factoryLog;
    }

    @Override
    public Stream<LogDto> readFile(Path path) {
        try {
            return Files.lines(Path.of(path.toUri()))
                .map(factoryLog::createLogDto)
                .filter(logDto -> logDto != null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
