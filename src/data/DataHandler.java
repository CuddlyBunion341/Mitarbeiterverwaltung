package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import model.Team;
import model.company.Person;

public class DataHandler {
    private static final String personPath = "src/data/files/person2.csv";
    private static final String teamPath = "src/data/files/team2.csv";
    private static final String departmentPath = "src/data/files/department2.csv";
    private static final String userActionPath = "src/data/files/userAction2.csv";

    public static void main(String[] args) {
        Vector<Person> employees = new Vector<Person>();

        for (int i = 0; i < 10; i++) {
            Person person = new Person("firstName" + i, "lastName" + i, i + ".png");
            employees.add(person);
        }

        DataHandler.setEmployees(employees);
    }

    public static void setEmployees(Vector<Person> people) {
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

    public static Vector<Person> getEmployees() {
        Vector<Person> people = new Vector<Person>();
        return people;
    }

    public static void setTeams(Vector<Team> teams) {
        try {
            FileWriter writer = new FileWriter(teamPath,true);
            for (Team team : teams) {
                // writer.write(team.getName() + ";" + team.getDescription() + "\n");
            }
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
