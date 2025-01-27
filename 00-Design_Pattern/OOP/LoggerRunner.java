import java.util.*;

interface Logger {
          public void log(String str);

          public List<LogResponse> getLogs();

}

class LogResponse {
          private int logId;
          private String logMesg;
          private String timestamp;

          public LogResponse(int logId, String message) {
                    this.logId = logId;
                    this.logMesg = message;
                    this.timestamp = String.valueOf(new Date());
          }

          // Getters
          public int getLogId() {
                    return logId;
          }

          public String getLogMessage() {
                    return logMesg;
          }

          public String getTimestamp() {
                    return timestamp;
          }

          @Override
          public String toString() {
                    return "Log ID: " + logId + ", Message: " + logMesg + ", Timestamp: " + timestamp;
          }

}

public class LoggerRunner implements Logger {
          private static LoggerRunner instance;
          private List<LogResponse> logList;
          private int logCounter;

          private LoggerRunner() {
                    this.logList = new ArrayList<>();
                    this.logCounter = 0;
          }

          public static synchronized LoggerRunner getInstance() {
                    if (instance == null) {
                              instance = new LoggerRunner();
                    }
                    return instance;
          }

          @Override
          public void log(String message) {
                    logCounter++;
                    logList.add(new LogResponse(logCounter, message));
                    System.out.println("Logged: " + message);
          }

          @Override
          public List<LogResponse> getLogs() {
                    return logList;
          }

          public static void main(String[] args) {
                    Logger logger = LoggerRunner.getInstance();

                    logger.log("First Log message");
                    logger.log("Second log message");

                    for (LogResponse logResponse : logger.getLogs()) {
                              System.out.println("log: " + logResponse);
                    }
          }

}
