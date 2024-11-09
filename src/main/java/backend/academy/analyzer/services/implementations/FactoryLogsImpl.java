package backend.academy.analyzer.services.implementations;

import backend.academy.analyzer.constant.NginxParametrs;
import backend.academy.analyzer.constant.PatternConstant;
import backend.academy.analyzer.models.LogRecord;
import backend.academy.analyzer.services.formatters.DateFormatterImpl;
import backend.academy.analyzer.services.interfaces.DateFormatter;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FactoryLogsImpl implements FactoryLog {
    private final Pattern pattern = Pattern.compile(PatternConstant.LOGPATTERN);
    private Matcher matcher;
    private DateFormatter dateFormatter;

    public FactoryLogsImpl() {
        this.matcher = pattern.matcher("");
        this.dateFormatter = new DateFormatterImpl();
    }

    @Override
    public LogRecord createLogDto(String logsLine) {
        matcher.reset(logsLine);
        LogRecord logRecord = null;
        if (matcher.find()) {
            logRecord = LogRecord.builder()
                .remoteAddress(findMatchByGroup(NginxParametrs.REMOTEADDRESS.value()))
                .remoteUser(findMatchByGroup(NginxParametrs.REMOTEUSER.value()))
                .timeLocal(dateFormatter.formatStringToDate(
                    findMatchByGroup(NginxParametrs.TIMELOCAL.value()), PatternConstant.PATTERNZONE))
                .requestType(findMatchByGroup(NginxParametrs.REQUESTTYPE.value()))
                .statusCode(findMatchByGroup(NginxParametrs.STATUSCODE.value()))
                .pathResources(findMatchByGroup(NginxParametrs.PATHRESOURCES.value()))
                .bodyByteSent(findMatchByGroup(NginxParametrs.BODYBYTESSENT.value()))
                .httpRefer(findMatchByGroup(NginxParametrs.REFER.value()))
                .httpUserAgent(findMatchByGroup(NginxParametrs.USERAGENT.value()))
                .build();
        }

        return logRecord;
    }

    public String findMatchByGroup(int numberGroup) {
        try {
            return matcher.group(numberGroup);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            log.info("Неверный номер группы");
            return null;
        }
    }
}
