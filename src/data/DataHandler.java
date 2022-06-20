package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import model.company.Person;

public class DataHandler {
    private static final String personPath = "resource/csv/person.csv";
    private static final String teamPath = "resource/csv/team.csv";

    private static Vector<Person> employees;
    private static Vector<String> teams;
    private static Vector<String> functions; // todo

    public static void main(String[] args) {
        Vector<Person> employees = DataHandler.getEmployees();
        for (Person p : employees) {
            System.out.println(p.getName());
        }

        Vector<String> teams = DataHandler.getTeams();
        for (String t : teams) {
            System.out.println(t);
        }
    }

    public static void writeEmployees(Vector<Person> people) {
        try {
            FileWriter writer = new FileWriter(personPath,true);
            for (Person person : people) {
                writer.write(person.getFirstName() + ";" + person.getLastName() + ";" + person.getPhotoPath() + "\n");
            }
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static Vector<String> readTeams() {
        teams = new Vector<>();
        try {
            Scanner scanner = new Scanner(new File(teamPath));
            teams = new Vector<String>();
            while (scanner.hasNextLine()) {
                teams.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return teams;
    }

    public static Vector<Person> readEmployees() {
        employees = new Vector<Person>();
        try {
            Scanner scanner = new Scanner(new File(personPath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                String firstName = parts[0];
                String lastName = parts[1];
                String photoPath = parts[2];
                boolean isHR = parts[3].equals("true");
                boolean isAdmin = parts[4].equals("true");
                String teams = parts[5]; // todo
                String functions = parts[6]; // todo
                int passwordHash = Integer.parseInt(parts[7]);

                Person person = new Person(firstName, lastName, photoPath);
                person.setHr(isHR);
                person.setAdmin(isAdmin);
                person.setPasswordHash(passwordHash);

                employees.add(person);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static void writeTeams(Vector<String> teams) {
        try {
            FileWriter writer = new FileWriter(teamPath,true);
            for (String team : teams) {
                writer.write(team);
            }
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static Vector<Person> getEmployees() {
        if (employees == null) {
            readEmployees();
        }
        return employees;
    }

    public static Vector<String> getTeams() {
        if (teams == null) {
            readTeams();
        }
        return teams;
    }
    
}
