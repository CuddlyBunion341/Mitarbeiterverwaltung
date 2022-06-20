package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import model.company.Department;
import model.company.Person;
import model.company.Team;

public class DataHandler {
    private static final String personPath = "resource/csv/person.csv";
    private static final String teamPath = "resource/csv/tean.csv";

    private static Vector<Person> employees;
    private static Vector<Team> teams;

    public static void main(String[] args) {
        Vector<Person> employees = DataHandler.getEmployees();
        for (Person p : employees) {
            System.out.println(p.getName());
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

    public static Vector<Person> readEmployees() {
        employees = new Vector<Person>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(personPath));
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

    public static void writeTeams(Vector<Team> teams) {
        try {
            FileWriter writer = new FileWriter(teamPath,true);
            for (Team team : teams) {
                // writer.write(team.getName() + ";" + team.getDescription() + "\n");
            }
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static Vector<Team> readTeams() {
        Vector<Team> teams = new Vector<Team>();
        return teams;
    }

    public static Vector<Department> writeDepartments() {
        Vector<Department> departments = new Vector<Department>();
        return departments;
    }

    public static Vector<Person> getEmployees() {
        if (employees == null) {
            readEmployees();
        }
        return employees;
    }

    public static Vector<Team> getTeams() {
        if (teams == null) {
            readTeams();
        }
        return teams;
    }
    
}
