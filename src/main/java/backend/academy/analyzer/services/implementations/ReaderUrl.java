package backend.academy.analyzer.services.implementations;

import backend.academy.analyzer.models.LogRecord;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import backend.academy.analyzer.services.interfaces.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class ReaderUrl implements Reader {
    private HttpClient httpClient;
    private FactoryLog factoryLog;

    public ReaderUrl(HttpClient httpClient, FactoryLog factoryLog) {
        this.httpClient = httpClient;
        this.factoryLog = factoryLog;
    }

    public BufferedReader getResponseServer(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
            BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(response.body(), StandardCharsets.UTF_8));
            return bufferedReader;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка ввода-вывода при отправке запроса: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Запрос был прерван: " + e.getMessage(), e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Stream<LogRecord> read(String url) {
        BufferedReader response = getResponseServer(url);
        return getStreamRecordFromStreamString(response.lines(), factoryLog);
    }
}
