package model.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Vector;

public class LogBook {
    private Vector<UserAction> entries;
    private static LogBook instance;
    private File file;
    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean fileWritingEnabled;

    public LogBook() {}

    public static LogBook getInstance() {
        if (instance == null) {
            instance = new LogBook();
        }
        return instance;
    }

    public void addEntry(UserAction entry) {
        entries.add(entry);
        if (fileWritingEnabled) {
            writeFile();
        }
    }

    public UserAction getEntry(int index) {
        return entries.get(index);
    }

    public int getSize() {
        return entries.size();
    }

    public void closeFile() {
        try {
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printLog() {
        entries.forEach(entry -> System.out.println(entry.getEntry()));
    }

    public void writeFile() {
        try {
            writer.write(entries.get(entries.size() - 1).getEntry());
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                // wtf should I do here?!
                // entries.add(new UserAction(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
