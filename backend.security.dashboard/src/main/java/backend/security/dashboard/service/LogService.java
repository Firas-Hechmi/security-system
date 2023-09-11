package backend.security.dashboard.service;

import backend.security.dashboard.dto.FilterLogsDTO;
import backend.security.dashboard.dto.LogDTO;
import backend.security.dashboard.mapper.LogMapper;
import backend.security.dashboard.model.Log;
import backend.security.dashboard.repository.LogRepository;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogService {
    private final LogRepository logRepository;
    private final LogMapper logMapper;

    @Autowired
    public LogService(LogRepository logRepository, LogMapper logMapper) {
        this.logRepository = logRepository;
        this.logMapper = logMapper;
    }

    public List<LogDTO> getAllLogs(){
        return this.logMapper.toLogsDTO(logRepository.findAllLogs());
    }

    public List<LogDTO> filterLogs(FilterLogsDTO filterLogs) {
        return logMapper.toLogsDTO(logRepository.findByMessageAndLogLevel(filterLogs.getMessage(),filterLogs.getLogLevel()));
    }


    public void saveLog(ILoggingEvent eventObject) {
        Level level = eventObject != null ? eventObject.getLevel() : null;
        if (level != null && level.isGreaterOrEqual(Level.INFO)) {
            Log newLog = new Log(LocalDateTime.now(), eventObject.getLoggerName(),
                    eventObject.getMessage(), level.toString());
            logRepository.save(newLog);
        }
    }
}

