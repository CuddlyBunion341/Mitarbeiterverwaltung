package main.java.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import main.java.model.company.Person;

/**
 * DataHandler is a class that handles all the data operations.
 * @author Daniel Bengl
 */
public class DataHandler {
    private static final String personPath = "resource/csv/person.csv";
    private static final String teamPath = "resource/csv/team.csv";
    private static final String functionPath = "resource/csv/function.csv";
    private static final String departmentPath = "resource/csv/department.csv";

    private static Vector<Person> employees;
    private static Vector<String> teams;
    private static Vector<String> functions;
    private static Vector<String> departments;

    public static void main(String[] args) {
        Vector<Person> employees = DataHandler.getEmployees();
        System.out.println("---- Employees ----------------------");
        for (Person p : employees) {
            System.out.println(p.getName());
        }

        Vector<String> teams = DataHandler.getTeams();

        System.out.println("---- Teams --------------------------");
        for (String t : teams) {
            System.out.println(t);
        }

        Vector<String> functions = DataHandler.getFunctions();
        System.out.println("---- Functions ----------------------");
        for (String f : functions) {
            System.out.println(f);
        }

        Vector<String> departments = DataHandler.getDepartments();
        System.out.println("---- Departments --------------------");
        for (String d : departments) {
            System.out.println(d);
        }
    }

    /**
     * Returns a vector of all employees from the csv file.
     * @return Vector of all employees.
     */
    private static Vector<String> readFunctions() {
        Vector<Object> functions = readFile(functionPath, line -> line);
        return functions.stream().map(String::valueOf).collect(Vector::new, Vector::add, Vector::addAll);
    }

    /**
     * Returns a vector of all teams from the csv file.
     * @return Vector of all teams.
     */
    private static Vector<String> readTeams() {
        Vector<Object> teams = readFile(teamPath, line -> line);
        return teams.stream().map(String::valueOf).collect(Vector::new, Vector::add, Vector::addAll);
    }

    /**
     * Returns a vector of all departments from the csv file.
     * @return Vector of all departments.
     */
    private static Vector<String> readDepartments() {
        Vector<Object> departments = readFile(departmentPath, line -> line);
        return departments.stream().map(String::valueOf).collect(Vector::new, Vector::add, Vector::addAll);
    }

    /**
     * Returns a vector of all employees from the csv file.
     * @return Vector of all employees.
     */
    private static Vector<Person> readEmployees() {
        Vector<Object> employees = readFile(personPath, line -> Person.fromCSV(line));
        return employees.stream().map(Person.class::cast).collect(Vector::new, Vector::add, Vector::addAll);
    }
    
    /**
     * Reads a File while applying a LineAction to each line.
     * @param filePath Path to the file.
     * @param parser LineAction to apply to each line.
     * @return Vector of parsed Objects.
     */
    private static Vector<Object> readFile(String filePath, LineAction parser) {
        Vector<Object> objects = new Vector<>();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                objects.add(parser.action(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return objects;
    }

    /**
     * Writes a vector of objects to a csv file.
     * @param teams Vector of teams to write.
     */
    public static void writeTeams(Vector<String> teams) {
        try {
            FileWriter writer = new FileWriter(teamPath);
            for (String team : teams) {
                writer.write(team);
                writer.write("\n");
            }
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a vector of objects to a csv file.
     * @param functions Vector of functions to write.
     */
    public static void writeFunctions(Vector<String> functions) {
        try {
            FileWriter writer = new FileWriter(functionPath);
            for (String function : functions) {
                writer.write(function);
                writer.write("\n");
            }
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a vector of objects to a csv file.
     * @param departments Vector of departments to write.
     */
    public static void writeDepartments(Vector<String> departments) {
        try {
            FileWriter writer = new FileWriter(departmentPath);
            for (String department : departments) {
                writer.write(department);
                writer.write("\n");
            }
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a vector of objects to a csv file.
     * @param people Vector of people to write.
     */
    public static void writeEmployees(Vector<Person> people) {
        try {
            FileWriter writer = new FileWriter(personPath);
            for (Person person : people) {
                writer.write(person.toCSV());
            }
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a vector of all employees from the csv file.
     * @return Vector of all employees.
     */
    public static Vector<Person> getEmployees() {
        if (employees == null) {
            employees = readEmployees();
        }
        return employees;
    }

    /**
     * Returns a vector of all teams from the csv file.
     * @return Vector of all teams.
     */
    public static Vector<String> getTeams() {
        if (teams == null) {
            teams = readTeams();
        }
        return teams;
    }

    /**
     * Returns a vector of all functions from the csv file.
     * @return Vector of all functions.
     */
    public static Vector<String> getFunctions() {
        if (functions == null) {
            functions = readFunctions();
        }
        return functions;
    }

    /**
     * Returns a vector of all departments from the csv file.
     * @return Vector of all departments.
     */
    public static Vector<String> getDepartments() {
        if (departments == null) {
            departments = readDepartments();
        }
        return departments;
    }
    
}

/**
 * Interface for LineActions.
 * Provides a method for processing a line. 
 * (Either parsing or stringifying)
 */
interface LineAction {
    public Object action(String line);
}