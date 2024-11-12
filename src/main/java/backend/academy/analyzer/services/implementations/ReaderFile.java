package backend.academy.analyzer.services.implementations;

import backend.academy.analyzer.models.LogRecord;
import backend.academy.analyzer.services.interfaces.FactoryLog;
import backend.academy.analyzer.services.interfaces.Reader;
import java.io.IOException;
import java.util.stream.Stream;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ReaderFile implements Reader {
    private FactoryLog factoryLog;

    public ReaderFile(FactoryLog factoryLog) {
        this.factoryLog = factoryLog;
    }

    @Override
    public Stream<LogRecord> read(String userPath) {
        try {
            return getStreamRecordFromStreamString(readAllFilesInDirectory(userPath), factoryLog);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }








}
