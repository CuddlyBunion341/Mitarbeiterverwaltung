package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import model.employees.Person;

public class DataHandler {
    private static final String personPath = "src/data/files/person2.csv";

    public static void main(String[] args) {
        Vector<Person> people = new Vector<Person>();

        for (int i = 0; i < 10; i++) {
            Person person = new Person("firstName" + i, "lastName" + i, i + ".png");
            people.add(person);
        }

        DataHandler.writePeople(people);
    }

    public static void writePeople(Vector<Person> people) {
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

    public static Vector<Person> readPeople() {
        Vector<Person> people = new Vector<Person>();
        Scanner sc = new Scanner(personPath);
        sc.useDelimiter("\n");
        while (sc.hasNext()) {
            String line = sc.next();
            String[] tokens = line.split(";");
            String firstName = tokens[0];
            String lastName = tokens[1];
            String imagePath = tokens[2];

            // // this is for later when we have images and designations
            // boolean isAdmin = tokens[3].equals("true");
            // boolean isHr = tokens[4].equals("true");

            // String[] teams = tokens[5].split(",");
            // String[] roles = tokens[6].split(",");

            Person p = new Person(firstName, lastName, imagePath);
            people.add(p);
        }
        sc.close();
        return null;
    }
}
