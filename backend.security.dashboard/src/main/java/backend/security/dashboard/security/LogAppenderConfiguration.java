package backend.security.dashboard.security;

import backend.security.dashboard.service.LogService;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogAppenderConfiguration {

    private final LogService logService;

    @Autowired
    public LogAppenderConfiguration(LogService logService){
        this.logService = logService;
    }

    @Bean
    public LogAppender logAppender() {
        LogAppender logAppender = new LogAppender(logService);
        logAppender.setContext(loggerContext());
        logAppender.start();
        return logAppender;
    }

    @Bean
    public LoggerContext loggerContext() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.start();
        return loggerContext;
    }

    @Bean
    public Logger rootLogger() {
        LoggerContext loggerContext = loggerContext();
        Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.INFO);
        rootLogger.addAppender(logAppender());
        return rootLogger;
    }
}