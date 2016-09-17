package lab3;

import java.io.File;
import java.io.IOException;

public class ForestDataBase {
	private String dbFileFullName = "forestdb";
	private String dbFileDirName = "db";
	private String fileSeparator = System.getProperty("file.separator");

	public ForestDataBase() {
		createNewStorageFile();
	}
	
	private void createNewStorageFile() {
        File logDir = new File(dbFileDirName);
        logDir.mkdir();

        File logFile = new File(dbFileDirName + fileSeparator + dbFileFullName);

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
