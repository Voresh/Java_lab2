package lab3;

import lab2.*;

import java.io.*;
import java.util.Arrays;

public class ForestDataBase {
	private String dbFileFullName = "forestdb";
	private String dbFileDirName = "db";
	private String fileSeparator = System.getProperty("file.separator");

	public ForestDataBase() {
		createStorageFileIfNotExists();
	}

    public void LoadClassesFromDataBase() {
        String n = readDataBase();
        String[] s = n.split("-");

        int i = 0;
        for(String str: s) {
            String[] s2 = str.split("_");
            if (s2[0].equals("g")) {
                new Grass(GrassType.values()[Integer.parseInt(s2[1])]);
            } else if (s2[0].equals("h")) {
                new Herbivorous(Integer.parseInt(s2[1]),GrassType.values()[Integer.parseInt(s2[2])]);
            } else if (s2[0].equals("p")) {
                new Predator(Integer.parseInt(s2[1]));
            } else if (s2[0].equals("t")) {
                new Tree(TreeType.values()[Integer.parseInt(s2[1])]);
            } else {
                System.out.println("failed");
            }
            i++;
        }
    }

    public String readDataBase() {
        BufferedReader bufferedReader = null;
        File dbFile = new File(dbFileDirName + fileSeparator + dbFileFullName);
        String output = "";
        try {
            bufferedReader = new BufferedReader(new FileReader(dbFile));
            char[] buffer = new char[(int) dbFile.length()];
            bufferedReader.read(buffer);
            output = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    public void writeDataBase(String string) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(dbFileDirName + fileSeparator + dbFileFullName, false));
            bufferedWriter.write(string);
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

    private void createStorageFileIfNotExists() {
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
