package backend.academy.analyzer;

import backend.academy.analyzer.models.LogRecord;
import backend.academy.analyzer.services.implementations.FactoryLogsImpl;
import backend.academy.analyzer.services.implementations.ReaderImpl;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import backend.academy.analyzer.services.interfaces.Reader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.stream.Stream;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReaderTest {
    private HttpResponse mockHttpResponse;
    private HttpClient mockHttpClient;

    @BeforeEach
    void setUp() {
        mockHttpResponse = Mockito.mock(HttpResponse.class);
        mockHttpClient = Mockito.mock(HttpClient.class);
    }

//    @Test
//    void readFileTest(){
//        FactoryLog factoryLog = new FactoryLogsImpl();
//        Reader reader = new ReaderImpl(factoryLog);
//        Stream<LogRecord> logs = reader.readFile(Path.of("src/main/resources/logs/log.txt"));
//        Assertions.assertNotNull(logs);
//
//    }

    @Test
    void getResponseServerTest() throws IOException, InterruptedException {
        String mockData = "log line 1";
        InputStream mockInputStream = new java.io.ByteArrayInputStream(mockData.getBytes());

        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
            .thenReturn(mockHttpResponse);
        when(mockHttpResponse.body()).thenReturn(mockInputStream);

        Reader logReader = new ReaderImpl(new FactoryLogsImpl());

        try (BufferedReader reader = logReader.getResponseServer("http://example.com/logs", mockHttpClient)) {
            Assertions.assertEquals("log line 1", reader.readLine());
        }

    }
}
