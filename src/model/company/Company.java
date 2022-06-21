package model.company;

import java.util.Vector;

import data.DataHandler;

public class Company {
    private Vector<Person> employees;
    private Vector<String> teams;
    private Vector<String> departments;
    private Vector<String> functions;
    private final String companyName;

    public Company(String name) {
        companyName = name;
        employees = DataHandler.readEmployees();
        teams = DataHandler.readTeams();
        functions = DataHandler.readFunctions();
        departments = DataHandler.readDepartments();

        Person hans = new Person("Hans", "Jansen", "hans.png");
        hans.setPassword("123");
        hans.setAdmin(true);
        hans.setHr(true);

        employees.add(hans);
    }

    public Vector<Person> getEmployees() {
        return employees;
    }

    public Vector<String> getTeams() {
        return teams;
    }

    public Vector<String> getDepartments() {
        return departments;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Vector<String> getFunctions() {
        return functions;
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
