package lab3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ForestLogger {
    private String logFileFullName = "forest.log";
    private String logFileDirName = "logs";
    private String fileSeparator = System.getProperty("file.separator");

    public ForestLogger() {
        createFileIfNotExists();
    }

    public void createFileIfNotExists(){
        File logDir = new File(logFileDirName);
        logDir.mkdir();

        File logFile = new File(logFileDirName + fileSeparator + logFileFullName);

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

    private void writeToLog(String str) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(logFileDirName + fileSeparator + logFileFullName, true));
            bw.write(str);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) try {
                bw.close();
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
