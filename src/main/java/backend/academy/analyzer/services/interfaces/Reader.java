package backend.academy.analyzer.services.interfaces;

import backend.academy.analyzer.models.LogDto;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface Reader {
    Stream<LogDto> readFile(Path path);
}
