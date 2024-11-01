package backend.academy.analyzer.models;

import lombok.Builder;



@Builder
public record LogDto(
//    String remoteAddress,
//    String remoteUser,
//    ZonedDateTime timeLocal,
//    String requestType,
//    String statusCode,
//    String pathResources,
//    int bodyByteSent,
//    String httpRefer,
//    String httpUserAgent
    String remoteAddress,
    String remoteUser,
    String timeLocal,
    String requestType,
    String statusCode,
    String pathResources,
    String bodyByteSent,
    String httpRefer,
    String httpUserAgent
) {
    @Override
    public String toString() {
        return "remoteAddress: " + remoteAddress
            + ", remoteUser: " + remoteUser
            + ", timeLocal: " + timeLocal
            + ", requestType: " + requestType
            + ", statusCode: " + statusCode
            + ", pathResources: " + pathResources
            + ", bodyByteSent: " + bodyByteSent
            + ", httpRefer: " + httpRefer
            + ", httpUserAgent: " + httpUserAgent;

    }
}

