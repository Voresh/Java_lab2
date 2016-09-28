package lab3;

import lab2.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ForestDataBase {
	private String dbFileFullName = "forestdb";
	private String dbFileDirName = "db";
	private String fileSeparator = System.getProperty("file.separator");

	public ForestDataBase() {
		createStorageFileIfNotExists();
	}

    public void LoadClassesFromDataBase() {//проверка на наличие аргументов и возможность других разделителей
        String db = readDataBase();
        String[] classes = db.split("-");

        for(String _class: classes) {
            String[] classFields = _class.split("_");
            if (classFields[0].equals("g")) {
                new Grass(GrassType.values()[Integer.parseInt(classFields[1])]);
            } else if (classFields[0].equals("h")) {
                new Herbivorous(Integer.parseInt(classFields[1]),GrassType.values()[Integer.parseInt(classFields[2])]);
            } else if (classFields[0].equals("p")) {
                new Predator(Integer.parseInt(classFields[1]));
            } else if (classFields[0].equals("t")) {
                new Tree(TreeType.values()[Integer.parseInt(classFields[1])]);
            } else {
                System.out.println("failed");
            }
        }
    }

    public void SaveClassesToDataBase(ArrayList<Grass> grass, ArrayList<Tree> trees, ArrayList<Herbivorous> herbivorous, ArrayList<Predator> predators) {
        String db = "";
        int i = 0;
        for (Grass _grass: grass) {
            db = db.concat("g_").concat(Integer.toString(_grass.getType().ordinal())).concat("-");
        }
        for (Tree tree: trees) {
            db = db.concat("t_").concat(Integer.toString(tree.getType().ordinal())).concat("-");
        }
        for (Herbivorous _herbivorous: herbivorous) {
            db = db.concat("h_").concat(Integer.toString(_herbivorous.getSize())).concat("_").concat(Integer.toString(_herbivorous.getEatableType().ordinal())).concat("-");
        }
        for (Predator predator: predators) {
            db = db.concat("p_").concat(Integer.toString(predator.getSize())).concat("-");
        }
        writeDataBase(db);
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
