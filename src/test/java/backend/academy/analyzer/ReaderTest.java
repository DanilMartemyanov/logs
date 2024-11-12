package backend.academy.analyzer;

import backend.academy.analyzer.models.LogRecord;
import backend.academy.analyzer.services.implementations.FactoryLogsImpl;
import backend.academy.analyzer.services.implementations.ReaderFile;
import backend.academy.analyzer.services.implementations.ReaderUrl;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import backend.academy.analyzer.services.interfaces.Reader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ReaderTest {
    private HttpResponse mockHttpResponse;
    private HttpClient mockHttpClient;

    @BeforeEach
    void setUp() {
        mockHttpResponse = Mockito.mock(HttpResponse.class);
        mockHttpClient = Mockito.mock(HttpClient.class);
    }

    @Test
    void readFileTest(){
        FactoryLog factoryLog = new FactoryLogsImpl();
        Reader reader = new ReaderFile(factoryLog);
        Stream<LogRecord> logs = reader.read("src/main/resources/logs/");
        Assertions.assertNotNull(logs);

    }

    @Test
    void getResponseServerTest() throws IOException, InterruptedException {
        String mockData = "log line 1";
        InputStream mockInputStream = new ByteArrayInputStream(mockData.getBytes());

        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
            .thenReturn(mockHttpResponse);
        when(mockHttpResponse.body()).thenReturn(mockInputStream);

        ReaderUrl logReader = new ReaderUrl( HttpClient.newHttpClient(), new FactoryLogsImpl());

        try (BufferedReader reader = logReader.getResponseServer("http://example.com/logs")) {
            Assertions.assertEquals("log line 1", reader.readLine());
        }

    }

    @Test
    void readLogsByURLTest() throws IOException, InterruptedException {
        Reader logReader = new ReaderUrl(HttpClient.newHttpClient(),new FactoryLogsImpl());
        String log = "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        InputStream mockInputStream = new ByteArrayInputStream(log.getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mockInputStream));
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
            .thenReturn(mockHttpResponse);
        when(mockHttpResponse.body()).thenReturn(mockInputStream);
        Stream<LogRecord> logs =  logReader.read("http://example.com/logs");
        Assertions.assertNotNull(logs);
    }

    @Test
    void readLogsTest(){
        Reader logReader = new ReaderUrl(HttpClient.newHttpClient(),new FactoryLogsImpl());
        String link = "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        Stream<LogRecord> logs = logReader.read(link);
        Assertions.assertNotNull(logs);
    }
}
