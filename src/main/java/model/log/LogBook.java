package main.java.model.log;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import main.java.model.company.Person;

/**
 * Logbook
 * @author Daniel Bengl
 */
public class LogBook {
    private static LogBook instance;
    private Vector<String> entries;
    private FileWriter file;
    private BufferedWriter writer;
    private static final String path = "resource/txt/log.txt";

    public static void main(String[] args) {
        // add a test log to logbook entries
        Person test = new Person("Daniel","Bengl","1.png");
        LogBook.getInstance().addEntry(new UserAction(test,ActionEnum.UNKNOWN_ACTION));
        LogBook.getInstance().closeFile();
    }

    private LogBook() {
        entries = new Vector<String>();
        try {
            file = new FileWriter(path,true);
            writer = new BufferedWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        readFile();
    }

    /**
     * Returns the instance of the LogBook.
     * @return LogBook instance.
     */
    public static LogBook getInstance() {
        if (instance == null) {
            instance = new LogBook();
        }
        return instance;
    }

    /**
     * Adds a new entry to the logbook.
     * @param entry Entry to add.
     */
    public void addEntry(UserAction entry) {
        entries.add(entry.toString());
        writeFile();
    }

    /**
     * Closes the file.
     */
    public void closeFile() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints all entries to the console.
     */
    public void printLog() {
        entries.forEach(entry -> System.out.println(entry));
    }

    /**
     * Reads the file and adds all entries to the vector.
     * @return Vector of all entries.
     */
    public Vector<String> getEntries() {
        return entries;
    }

    /**
     * Appends latest entries to the file.
     */
    private void writeFile() {
        try {
            writer.write(entries.get(entries.size() - 1));
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the file and adds all entries to the vector.
     */
    private void readFile() {
        try (Scanner sc = new Scanner(new FileReader(path))) {
            while (sc.hasNextLine()) {
                entries.add(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the logbook contents as string.
     */
    public String toString() {
        String log = "";
        for (String entry : entries) {
            log += entry + "\n";
        }
        return log;
    }
}
