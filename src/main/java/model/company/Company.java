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

    public Company(String name) {
        companyName = name;
    }

    public Vector<Person> getEmployees() {
        if (employees == null) {
            employees = DataHandler.getEmployees();
        }
        return employees;
    }

    public Vector<String> getTeams() {
        if (teams == null) {
            teams = DataHandler.getTeams();
        }
        return teams;
    }

    public Vector<String> getDepartments() {
        if (departments == null) {
            departments = DataHandler.getDepartments();
        }
        return departments;
    }

    public Vector<String> getFunctions() {
        if (functions == null) {
            functions = DataHandler.getFunctions();
        }
        return functions;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void writeEmployees() {
        DataHandler.writeEmployees(employees);
    }

    public void writeTeams() {
        DataHandler.writeTeams(teams);
    }

    public void writeFunctions() {
        DataHandler.writeFunctions(functions);
    }

    public void writeDepartments() {
        DataHandler.writeDepartments(departments);
    }
}
