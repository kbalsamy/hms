package com.hygieia.AppLogger;


import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/*
 * SINGLETON PATTERN IMPLEMENTATION
 *  * 
 */

public class AppLogger {


    private static final String LOG_FILE_PATH = "appLogs.json";
    private static AppLogger instance;
    private Logger logger;

    private AppLogger() {
        logger = Logger.getLogger(AppLogger.class.getName());
        configureLogger();
    }

    public static synchronized AppLogger getInstance() {
        if (instance == null) {
            instance = new AppLogger();
        }
        return instance;
    }

    private void configureLogger() {
        // Set the default logging level 
        logger.setLevel(Level.INFO);

        // Remove existing handlers to avoid duplicate logs        
        Handler [] handlers = logger.getHandlers();

        for (Handler handler : handlers) {
            logger.removeHandler(handler);
        }

        // Add a console handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);

        // Add a file handler
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE_PATH, true);
            fileHandler.setLevel(Level.ALL); 
            fileHandler.setFormatter(new JsonFormatter());                     
            logger.addHandler(fileHandler);
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in creating file handler", e);
        }
            

        }

        private static class JsonFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {
            return String.format(
                    "{\"timestamp\":\"%s\", \"level\":\"%s\", \"message\":\"%s\"}%n",
                    record.getMillis(),
                    record.getLevel(),
                    record.getMessage()
            );
        }
    }

        public void logInfo(String message) {
            logger.info(message);
        }

        public void logError(String message, Throwable throwable) {
            logger.log(Level.SEVERE, message, throwable);
        }

    
}
