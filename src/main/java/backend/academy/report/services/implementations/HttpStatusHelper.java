package backend.academy.report.services.implementations;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class HttpStatusHelper {

    public static String getHttpStatusName(String statusCode) {
        Map<String, String> httpStatusNames = Map.of(
            "200", "OK",
            "201", "Created",
            "204", "No Content",
            "400", "Bad Request",
            "401", "Unauthorized",
            "403", "Forbidden",
            "404", "Not Found",
            "500", "Internal Server Error",
            "502", "Bad Gateway",
            "503", "Service Unavailable"
            // Добавьте другие коды состояния, если необходимо
        );

        return httpStatusNames.getOrDefault(statusCode, "Unknown Status");
    }
}
