package main.java.model.company;

import java.util.Arrays;
import java.util.Vector;

/**
 * Person model class.
 * Stores all the data of a person.
 * @author Daniel Bengl
 */
public class Person implements Comparable {
    private String photoPath;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private boolean isHr;
    private Vector<String> teams;
    private Vector<String> functions;
    private String department;
    private int passwordHash;

    /**
     * Constructor for a person.
     * @param fName First name of the person.
     * @param lName Last name of the person.
     * @param photoPath Path to the photo of the person.
     */
    public Person(String fName, String lName, String photoPath) {
        this.firstName = fName;
        this.lastName = lName;
        this.photoPath = photoPath;
        teams = new Vector<>();
        functions = new Vector<>();
    }

    /**
     * Gets path to the photo of the person.
     * @return Path to the photo of the person.
     */
    public String getPhotoPath() {
        return this.photoPath;
    }

    /**
     * Sets path to the photo of the person.
     * @param photoPath Path to the photo of the person.
     */
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    /**
     * Gets first name of the person.
     * @return First name of the person.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets first name of the person.
     * @param firstName First name of the person.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name of the person.
     * @return Last name of the person.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Gets full name of the person.
     * @return Full name of the person.
     */
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Sets last name of the person.
     * @param lastName Last name of the person.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets if the person is an admin.
     * @return
     */
    public boolean isAdmin() {
        return this.isAdmin;
    }

    /**
     * Sets if the person is an admin.
     * @param isAdmin
     */
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * Gets if the person is an HR.
     * @return
     */
    public boolean isHr() {
        return this.isHr;
    }

    /**
     * Sets if the person is an HR.
     * @param isHr
     */
    public void setHr(boolean isHr) {
        this.isHr = isHr;
    }

    /**
     * Gets all the teams of the person.
     * @return
     */
    public Vector<String> getTeams() {
        return this.teams;
    }

    /**
     * Sets all the teams of the person.
     * @param teams
     */
    public void setTeams(Vector<String> teams) {
        this.teams = teams;
    }

    /**
     * Gets all the functions of the person.
     * @return
     */
    public Vector<String> getFunctions() {
        return this.functions;
    }

    /**
     * Sets all the functions of the person.
     * @param roles
     */
    public void setFunctions(Vector<String> roles) {
        this.functions = roles;
    }

    /**
     * Tests if password is correct.
     * @param password Password to test.
     * @return True if password is correct, false otherwise.
     */
    public boolean authenticate(String password) {
        return this.passwordHash == password.hashCode();
    }

    /**
     * Sets password of the person.
     * @param password Password of the person.
     */
    public void setPassword(String password) {
        this.passwordHash = password.hashCode();
    }

    /**
     * Sets password hash of the person.
     * @param hash
     */
    public void setPasswordHash(int hash) {
        this.passwordHash = hash;
    }

    /**
     * Gets name of the person.
     */
    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Gets department of the person.
     * @return
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     * Sets department of the person.
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Parses person from a csv string. separated with ;/
     * @param csv
     * @return
     */
    public static Person fromCSV(String csv) {
        String[] parts = csv.split(";");
        String firstName = parts[0];
        String lastName = parts[1];
        String photoPath = parts[2];
        boolean isHR = parts[3].equals("true");
        boolean isAdmin = parts[4].equals("true");
        String department = parts[5];
        String teams = parts[6];
        String functions = parts[7];
        int passwordHash = Integer.parseInt(parts[8]);
        Person person = new Person(firstName, lastName, photoPath);
        person.setDepartment(department);
        person.setHr(isHR);
        person.setAdmin(isAdmin);
        person.setPasswordHash(passwordHash);
        person.setTeams(new Vector<String>(Arrays.asList(teams.split(","))));
        person.setFunctions(new Vector<String>(Arrays.asList(functions.split(","))));
        return person;
    }

    /**
     * Converts person to a csv string. separated with ;/
     * @return
     */
    public String toCSV() {
        String csv = firstName + ";" + lastName + ";" + photoPath + ";" + isHr + ";" + isAdmin
                + ";" + teams + ";" + functions + ";" + passwordHash;
        return csv;
    }

    /**
     * Compares two persons.
     */
    @Override
    public int compareTo(Object o) {
        return getName().compareTo(o.toString());
    };
}
