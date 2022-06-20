package model.company;

import java.util.Vector;

public class Person {
    private String photoPath;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private boolean isHr;
    private Vector<Team> teams;
    private Vector<String> roles;
    private String password;

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

    public Vector<Team> getTeams() {
        return this.teams;
    }

    public void setTeams(Vector<Team> teams) {
        this.teams = teams;
    }

    public Vector<String> getRoles() {
        return this.roles;
    }

    public void setRoles(Vector<String> roles) {
        this.roles = roles;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
