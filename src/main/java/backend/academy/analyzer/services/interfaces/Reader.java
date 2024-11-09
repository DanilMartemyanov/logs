package backend.academy.analyzer.services.interfaces;

import backend.academy.analyzer.models.LogRecord;
import java.io.BufferedReader;
import java.net.http.HttpClient;
import java.util.stream.Stream;

public interface Reader {
    Stream<LogRecord> readFile(String path);

    Stream<LogRecord> readLogsByURL(String url, HttpClient httpClient);

    BufferedReader getResponseServer(String url, HttpClient httpClient);
}
