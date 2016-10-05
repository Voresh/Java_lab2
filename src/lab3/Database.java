package lab3;

import lab2.*;

import java.io.*;
import java.util.ArrayList;

public class Database {
    private String mDatabaseFileFullName = "forestdb";
    private String mDatabaseFileDirName = "db";
    private String mFileSeparator = System.getProperty("file.separator");

    public Database() {
        createStorageFileIfNotExists();
    }

    public void loadClassesFromDataBase() {
        String db = readDataBase();
        db = db.replaceAll(System.getProperty("line.separator"), "");
        db = db.replaceAll(" ", "");
        String[] classes = db.split("-");

        for (String _class : classes) {
            String[] classFields = _class.split("_");
            if (classFields[0].equals("g")) {
                if (classFields.length == 2)
                    new Grass(GrassType.values()[Integer.parseInt(classFields[1])]);
            } else if (classFields[0].equals("h")) {
                if (classFields.length == 3)
                    new Herbivorous(Integer.parseInt(classFields[1]), GrassType.values()[Integer.parseInt(classFields[2])]);
            } else if (classFields[0].equals("p")) {
                if (classFields.length == 2)
                    new Predator(Integer.parseInt(classFields[1]));
            } else if (classFields[0].equals("t")) {
                if (classFields.length == 2)
                    new Tree(TreeType.values()[Integer.parseInt(classFields[1])]);
            }
        }
    }

    public void saveClassesToDataBase(ArrayList<Grass> grass, ArrayList<Tree> trees, ArrayList<Herbivorous> herbivorous, ArrayList<Predator> predators) {
        String db = "";
        int i = 0;
        for (Grass _grass : grass) {
            db = db.concat("g_").concat(Integer.toString(_grass.getType().ordinal())).concat("-");
        }
        for (Tree tree : trees) {
            db = db.concat("t_").concat(Integer.toString(tree.getType().ordinal())).concat("-");
        }
        for (Herbivorous _herbivorous : herbivorous) {
            db = db.concat("h_").concat(Integer.toString(_herbivorous.getSize())).concat("_").concat(Integer.toString(_herbivorous.getEatableType().ordinal())).concat("-");
        }
        for (Predator predator : predators) {
            db = db.concat("p_").concat(Integer.toString(predator.getSize())).concat("-");
        }
        writeDataBase(db);
    }

    public String readDataBase() {
        BufferedReader bufferedReader = null;
        File dbFile = new File(mDatabaseFileDirName + mFileSeparator + mDatabaseFileFullName);
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
            bufferedWriter = new BufferedWriter(new FileWriter(mDatabaseFileDirName + mFileSeparator + mDatabaseFileFullName, false));
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
        File logDir = new File(mDatabaseFileDirName);
        logDir.mkdir();

        File logFile = new File(mDatabaseFileDirName + mFileSeparator + mDatabaseFileFullName);

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
