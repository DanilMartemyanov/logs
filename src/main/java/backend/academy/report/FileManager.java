package backend.academy.report;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileManager {
    public static List<String> getFileNamesInDirectory(String directoryPath) {
        try (Stream<Path> paths = Files.list(Paths.get(directoryPath))) {
            return paths
                .filter(Files::isRegularFile)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error reading directory: " + directoryPath, e);
        }
    }
}
