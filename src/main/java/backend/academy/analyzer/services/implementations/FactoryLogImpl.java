package backend.academy.analyzer.services.implementations;

import backend.academy.analyzer.models.LogDto;
import backend.academy.analyzer.services.PatternConstant;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import backend.academy.analyzer.services.interfaces.ParserLogs;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FactoryLogImpl implements FactoryLog {
    private final Pattern pattern = Pattern.compile(PatternConstant.LOGPATTERN);



    @Override
    public LogDto createLogDto(String logsLine) {
        Matcher matcher = pattern.matcher(logsLine);
        ParserLogs parserLogs = new ParserLogsImpl(matcher);
        LogDto logDto = LogDto.builder()
            .remoteAddress(parserLogs.parseRemoteAddress())
            .remoteUser(parserLogs.parseRemoteUser())
            .timeLocal(parserLogs.parseTimeLocal())
            .requestType(parserLogs.parseRequestType())
            .pathResources(parserLogs.parsePathResources())
            .bodyByteSent(parserLogs.parseBodyBytesSent())
            .httpRefer(parserLogs.parseHttpRefer())
            .httpUserAgent(parserLogs.parseHttpUserAgent())
            .build();
        return null;
    }
}
