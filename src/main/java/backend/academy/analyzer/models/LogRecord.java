package backend.academy.analyzer.models;

import java.time.ZonedDateTime;
import lombok.Builder;

@SuppressWarnings("checkstyle:RecordComponentNumber")
@Builder
public record LogRecord(
    String remoteAddress,
    String remoteUser,
    ZonedDateTime timeLocal,
    String requestType,
    String statusCode,
    String pathResources,
    String bodyByteSent,
    String httpRefer,
    String httpUserAgent
) {
    @Override
    public String toString() {
        return "remoteAddress: " + remoteAddress + "\n"
            + "remoteUser: " + remoteUser + "\n"
            + "timeLocal: " + timeLocal + "\n"
            + "requestType: " + requestType + "\n"
            + "statusCode: " + statusCode + "\n"
            + "pathResources: " + pathResources + "\n"
            + "bodyByteSent: " + bodyByteSent + "\n"
            + "httpRefer: " + httpRefer + "\n"
            + "httpUserAgent: " + httpUserAgent + "\n";
    }
}

