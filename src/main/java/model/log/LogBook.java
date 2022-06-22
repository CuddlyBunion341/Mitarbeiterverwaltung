package main.java.model.log;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.Action;

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

    public static LogBook getInstance() {
        if (instance == null) {
            instance = new LogBook();
        }
        return instance;
    }

    public void addEntry(UserAction entry) {
        entries.add(entry.toString());
        writeFile();
    }

    public void closeFile() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printLog() {
        entries.forEach(entry -> System.out.println(entry));
    }

    public Vector<String> getEntries() {
        return entries;
    }

    private void writeFile() {
        try {
            writer.write(entries.get(entries.size() - 1));
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public String toString() {
        String log = "";
        for (String entry : entries) {
            log += entry + "\n";
        }
        return log;
    }
}