package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import model.company.Person;

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
        System.out.println("123".hashCode());
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

    public static Vector<String> readFunctions() {
        Vector<Object> functions = readFile(functionPath, line -> line);
        return functions.stream().map(String::valueOf).collect(Vector::new, Vector::add, Vector::addAll);
    }

    public static Vector<String> readTeams() {
        Vector<Object> teams = readFile(teamPath, line -> line);
        return teams.stream().map(String::valueOf).collect(Vector::new, Vector::add, Vector::addAll);
    }

    public static Vector<String> readDepartments() {
        Vector<Object> departments = readFile(departmentPath, line -> line);
        return departments.stream().map(String::valueOf).collect(Vector::new, Vector::add, Vector::addAll);
    }

    public static Vector<Person> readEmployees() {
        Vector<Object> employees = readFile(personPath, line -> Person.fromCSV(line));
        return employees.stream().map(Person.class::cast).collect(Vector::new, Vector::add, Vector::addAll);
    }
    
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

    public static Vector<Person> getEmployees() {
        if (employees == null) {
            employees = readEmployees();
        }
        return employees;
    }

    public static Vector<String> getTeams() {
        if (teams == null) {
            teams = readTeams();
        }
        return teams;
    }

    public static Vector<String> getFunctions() {
        if (functions == null) {
            functions = readFunctions();
        }
        return functions;
    }

    public static Vector<String> getDepartments() {
        if (departments == null) {
            departments = readDepartments();
        }
        return departments;
    }
    
}

interface LineAction {
    public Object action(String line);
}