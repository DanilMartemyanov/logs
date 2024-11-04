package backend.academy.analyzer.services.implementations;

import backend.academy.analyzer.models.LogRecord;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import backend.academy.analyzer.services.interfaces.Reader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ReaderImpl implements Reader {
    private FactoryLog factoryLog;

    public ReaderImpl(FactoryLog factoryLog) {
        this.factoryLog = factoryLog;
    }

    @Override
    public Stream<LogRecord> readFile(Path path) {
        try {
            return Files.lines(Path.of(path.toUri()))
                .map(line -> {
                    try {
                        return factoryLog.createLogDto(line);
                    } catch (Exception ex) {
                        log.error("Ошибка при обработке строки: " + line, ex);
                        return null;
                    }
                })
                .filter(logDto -> logDto != null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
