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
            return getStreamRecordFromStreamString(Files.lines(Path.of(path.toUri())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Stream<LogRecord> readLogsByURL(String url, HttpClient httpClient) {
        BufferedReader response = getResponseServer(url, httpClient);
        return getStreamRecordFromStreamString(response.lines());
    }

    public  BufferedReader getResponseServer(String url, HttpClient httpClient) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.body(), StandardCharsets.UTF_8));
            return bufferedReader;
        }catch (IOException e) {
            throw new RuntimeException("Ошибка ввода-вывода при отправке запроса: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Запрос был прерван: " + e.getMessage(), e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<LogRecord> getStreamRecordFromStreamString(Stream<String> stream){
        return stream
            .map(line -> {
                try {
                    return factoryLog.createLogDto(line);
                } catch (Exception ex) {
                    log.error("Ошибка при обработке строки: " + line, ex);
                    return null;
                }
            })
            .filter(logDto -> logDto != null);
    }
}
