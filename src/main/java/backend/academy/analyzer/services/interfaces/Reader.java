package backend.academy.analyzer.services.interfaces;

import backend.academy.analyzer.models.LogRecord;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface Reader {
    Stream<LogRecord> readFile(Path path);
}
