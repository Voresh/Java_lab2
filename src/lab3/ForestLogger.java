package lab3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ForestLogger {
    private String mLogFileFullName = "forest.log";
    private String mLogFileDirName = "logs";
    private String mFileSeparator = System.getProperty("file.separator");

    public ForestLogger() {
        createLogFileIfNotExists();
    }

    public void createLogFileIfNotExists() {
        File logDir = new File(mLogFileDirName);
        logDir.mkdir();

        File logFile = new File(mLogFileDirName + mFileSeparator + mLogFileFullName);

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeProgramStart() {
        writeToLog(currentTimeLabel() + "Program started");
    }

    public void writeProgramEnd() {
        writeToLog(currentTimeLabel() + "Program ended");
    }

    public void writeOtherMessage(String message) {
        writeToLog(currentTimeLabel() + message);
    }

    private void writeToLog(String str) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(mLogFileDirName + mFileSeparator + mLogFileFullName, true));
            bufferedWriter.write(str + "\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String currentTimeLabel() {
        LocalTime time = LocalTime.now();
        return "[" + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "]";
    }
}
