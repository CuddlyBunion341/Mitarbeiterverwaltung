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

    public static Vector<String> readFunctions() {
        Vector<Object> functions = readFile(functionPath, line -> line);
        return functions.stream().map(String::valueOf).collect(Vector::new, Vector::add, Vector::addAll);
    }

    public static Vector<String> readTeams() {
        Vector<Object> teams = readFile(teamPath, line -> line);
        return teams.stream().map(String::valueOf).collect(Vector::new, Vector::add, Vector::addAll);
    }
    
    public static Vector<Object> readFile(String filePath, Parser parser) {
        Vector<Object> objects = new Vector<>();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                objects.add(parser.parse(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return objects;
    }



    public static Vector<String> readDepartments() {
        Vector<String> departments = new Vector<>();
        try {
            Scanner scanner = new Scanner(new File(departmentPath));
            departments = new Vector<String>();
            while (scanner.hasNextLine()) {
                departments.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public static Vector<Person> readEmployees() {
        Vector<Person> employees = new Vector<Person>();
        try {
            Scanner scanner = new Scanner(new File(personPath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Person person = Person.fromCSV(line);
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


interface DataHandlerInterface {
    public Vector<Person> getEmployees();
    public Vector<String> getTeams();
    public Vector<String> getFunctions();
    public Vector<String> getDepartments();
    public void writeEmployees(Vector<Person> employees);
    public void writeTeams();
    public void writeFunctions();
    public void writeDepartments();
}

interface Parser {
    public Object parse(String line);
}