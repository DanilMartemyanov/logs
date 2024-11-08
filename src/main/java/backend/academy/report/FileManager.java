package backend.academy.report;

import lombok.experimental.UtilityClass;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class FileManager {
    public static List<String> getFileNamesInDirectory(String directoryPath) {
        try (Stream<Path> paths = Files.list(Paths.get(directoryPath))) {
            return paths
                .filter(Files::isRegularFile) // Фильтруем только файлы (без директорий)
                .map(Path::getFileName)       // Извлекаем имя файла
                .map(Path::toString)          // Преобразуем в строку
                .collect(Collectors.toList()); // Собираем в список
        } catch (IOException e) {
            throw new RuntimeException("Error reading directory: " + directoryPath, e);
        }
    }
}
