package main.java.model.company;

import java.util.Vector;

import main.java.data.DataHandler;

/**
 * Main model class for the company.
 * Stores all the data of the company.
 * @author Daniel Bengl
 */
public class Company {
    private Vector<Person> employees;
    private Vector<String> teams;
    private Vector<String> departments;
    private Vector<String> functions;
    private final String companyName;

    /**
     * Constructor for the company.
     * @param name
     */
    public Company(String name) {
        companyName = name;
    }

    /**
     * Gets all the employees of the company.
     * @return Vector of all employees.
     */
    public Vector<Person> getEmployees() {
        if (employees == null) {
            employees = DataHandler.getEmployees();
        }
        return employees;
    }

    /**
     * Gets all the teams of the company.
     * @return Vector of all teams.
     */
    public Vector<String> getTeams() {
        if (teams == null) {
            teams = DataHandler.getTeams();
        }
        return teams;
    }

    /**
     * Gets all the departments of the company.
     * @return Vector of all departments.
     */
    public Vector<String> getDepartments() {
        if (departments == null) {
            departments = DataHandler.getDepartments();
        }
        return departments;
    }

    /**
     * Gets all the functions of the company.
     * @return Vector of all functions.
     */
    public Vector<String> getFunctions() {
        if (functions == null) {
            functions = DataHandler.getFunctions();
        }
        return functions;
    }

    /**
     * Gets the name of the company.
     * @return Name of the company.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Writes all employees to the csv file.
     */
    public void writeEmployees() {
        DataHandler.writeEmployees(employees);
    }

    /**
     * Writes all teams to the csv file.
     */
    public void writeTeams() {
        DataHandler.writeTeams(teams);
    }

    /**
     * Writes all functions of the company to the csv file.
     */
    public void writeFunctions() {
        DataHandler.writeFunctions(functions);
    }

    /**
     * Writes all the departments of the company to the csv file.
     */
    public void writeDepartments() {
        DataHandler.writeDepartments(departments);
    }
}
