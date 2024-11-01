package backend.academy.analyzer.services.implementations;

import backend.academy.analyzer.models.LogDto;
import backend.academy.analyzer.constant.PatternConstant;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import lombok.extern.log4j.Log4j2;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Log4j2
public class FactoryLogsImpl implements FactoryLog {
    private final Pattern pattern = Pattern.compile(PatternConstant.LOGPATTERN);
    private Matcher matcher;


    public FactoryLogsImpl() {
        this.matcher = pattern.matcher("");
    }


    @Override
    public LogDto createLogDto(String logsLine) {
        matcher.reset(logsLine);
        LogDto logDto = null;
        if (matcher.find()) {
            logDto = LogDto.builder()
            .remoteAddress(findMatchByGroup(PatternConstant.REMOTEADDRESS))
            .remoteUser(findMatchByGroup(PatternConstant.REMOTEUSER))
            .timeLocal(findMatchByGroup(PatternConstant.TIMELOCAL))
            .requestType(findMatchByGroup(PatternConstant.REQUESTTYPE))
            .statusCode(findMatchByGroup(PatternConstant.STATUSCODE))
            .pathResources(findMatchByGroup(PatternConstant.PATHRESOURCES))
            .bodyByteSent(findMatchByGroup(PatternConstant.BODYBYTESSENT))
            .httpRefer(findMatchByGroup(PatternConstant.REFER))
            .httpUserAgent(findMatchByGroup(PatternConstant.USERAGENT))
                .build();
        }

        return logDto;
    }

    public String findMatchByGroup(int numberGroup) {
         try {
             return matcher.group(numberGroup);
         }catch (IllegalArgumentException | IndexOutOfBoundsException e) {
             log.info("Неверный номер группы");
             return null;
         }
    }
}
