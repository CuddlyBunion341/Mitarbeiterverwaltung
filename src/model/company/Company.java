package model.company;

import java.util.Vector;

import data.DataHandler;

public class Company {
    private Vector<Person> employees;
    private Vector<String> teams;
    private Vector<String> departments;
    private final String companyName;

    public Company(String name) {
        companyName = name;
        employees = DataHandler.readEmployees();
        teams = DataHandler.readTeams();
        // departments = DataHandler.readDepar();

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
        return this.departments;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public Vector<String> getFunctions() {
        Vector<String> functions = new Vector<>();
        functions.add("HR");
        functions.add("Admin");
        functions.add("Developer");
        functions.add("Tester");
        return null;
    }
}
