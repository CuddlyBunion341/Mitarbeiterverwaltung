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
        teams = new Vector<Team>();
        departments = new Vector<Department>();
    }
}
