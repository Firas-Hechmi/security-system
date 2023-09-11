package backend.security.dashboard.security;

import backend.security.dashboard.service.LogService;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class LogAppender extends AppenderBase<ILoggingEvent> {

    private final LogService logService;

    public LogAppender(LogService logService) {
        this.logService = logService;
    }

    @Override
    protected synchronized void append(ILoggingEvent eventObject) {
        // Save log in the database
        if (isStarted()) {
          logService.saveLog(eventObject);
        }
    }
}