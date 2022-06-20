package model;

import java.util.Vector;

import model.company.Department;
import model.company.Person;

public class MainModel {
    private Vector<Person> employees;
    private Vector<Team> teams;
    private Vector<Department> departments;

    public MainModel() {
        employees = new Vector<Person>();
        teams = new Vector<Team>();
        departments = new Vector<Department>();
    }

    public Person getPerson(int index) {
        return employees.get(index);
    }

    public void addPerson(Person person) {
        employees.add(person);
    }

    public Vector<Person> getEmployees() {
        return employees;
    }

    public Vector<Team> getTeams() {
        return teams;
    }

    public Vector<Department> getDepartments() {
        return departments;
    }
}
