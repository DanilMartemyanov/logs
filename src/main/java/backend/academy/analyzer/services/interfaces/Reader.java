package backend.academy.analyzer.services.interfaces;

import backend.academy.analyzer.models.LogRecord;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface Reader {
    Stream<LogRecord> read(String path);

    default Stream<LogRecord> getStreamRecordFromStreamString(Stream<String> stream, FactoryLog factoryLog) {
        return stream
            .map(line -> {
                try {
                    return factoryLog.createLogDto(line);
                } catch (Exception ex) {
                    return null;
                }
            })
            .filter(logDto -> logDto != null);
    }

    default Stream<String> readAllFilesInDirectory(String dirPath) throws IOException {
        return Files.walk(Path.of(dirPath))
            .filter(Files::isRegularFile)
            .flatMap(path -> {
                try {
                    return Files.lines(path);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
    }
}
