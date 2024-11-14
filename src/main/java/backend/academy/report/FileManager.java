package backend.academy.report;

import backend.academy.report.models.Report;
import backend.academy.report.services.interfaces.GeneratorReport;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@UtilityClass
@SuppressFBWarnings("PATH_TRAVERSAL_IN")
@Log4j2
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

    public static void writeReportToFile(Report report, String format, GeneratorReport generatorReport) {
        Path path = Path.of("resultAnalyze." + format);
        try {
            String reportInFile = generatorReport.generateReport(report);
            Files.writeString(path,
                reportInFile,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            );
            log.info("Report generated successfully at {} ", path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write report to file: " + path, e);
        }
    }
}
