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
        employees = DataHandler.getEmployees();
        teams = DataHandler.getTeams();
        departments = DataHandler.getDepartments();
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
