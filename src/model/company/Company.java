package model.company;

import java.util.Vector;

import data.DataHandler;

public class Company {
    private Vector<Person> employees;
    private Vector<Team> teams;
    private Vector<Department> departments;
    private final String companyName;

    public Company(String name) {
        companyName = name;
        employees = DataHandler.readEmployees();
        teams = DataHandler.readTeams();
        departments = DataHandler.writeDepartments();

        Person hans = new Person("Hans", "Jansen", "hans.png");
        hans.setPassword("123");
        hans.setAdmin(true);
        hans.setHr(true);

        employees.add(hans);
    }

    public Vector<Person> getEmployees() {
        return employees;
    }

    public Vector<Team> getTeams() {
        return teams;
    }

    public Vector<Department> getDepartments() {
        return this.departments;
    }

    public String getCompanyName() {
        return this.companyName;
    }
}
