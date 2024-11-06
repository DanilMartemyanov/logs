package backend.academy.analyzer.services.interfaces;

import backend.academy.analyzer.models.LogRecord;
import java.io.BufferedReader;
import java.net.http.HttpClient;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface Reader {
    Stream<LogRecord> readFile(Path path);

    Stream<LogRecord> readLogsByURL(BufferedReader response);

    BufferedReader getResponseServer(String url, HttpClient httpClient);
}
