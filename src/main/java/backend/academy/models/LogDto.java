package backend.academy.models;

public record LogDto(
    String remoteAddress,
    String remoteUser,
    String timeLocal,
    String requestType,
    String statusCode,
    String bodyByteSent,
    String httpRefer,
    String httpUserAgent
) {
}
