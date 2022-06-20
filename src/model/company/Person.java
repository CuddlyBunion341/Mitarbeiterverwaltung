package model.company;

import java.util.Vector;

public class Person {
    private String photoPath;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private boolean isHr;
    private Vector<String> teams;
    private Vector<String> functions;
    private String department;
    private int passwordHash;

    public Person(String fName, String lName, String photoPath) {
        this.firstName = fName;
        this.lastName = lName;
        this.photoPath = photoPath;
    }

    public String getPhotoPath() {
        return this.photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isHr() {
        return this.isHr;
    }

    public void setHr(boolean isHr) {
        this.isHr = isHr;
    }

    public Vector<String> getTeams() {
        return this.teams;
    }

    public void setTeams(Vector<String> teams) {
        this.teams = teams;
    }

    public Vector<String> getRoles() {
        return this.functions;
    }

    public void setRoles(Vector<String> roles) {
        this.functions = roles;
    }

    public boolean authenticate(String password) {
        return this.passwordHash == password.hashCode();
    }

    public void setPassword(String password) {
        this.passwordHash = password.hashCode();
    }

    public void setPasswordHash(int hash) {
        this.passwordHash = hash;
    }

    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
