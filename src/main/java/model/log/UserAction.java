package main.java.model.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import main.java.model.company.Person;

/**
 * UserAction model class.
 * Stores all the data of a user action.
 * @author Daniel Bengl
 */
public class UserAction {
    private String entry;

    public UserAction(Person person, ActionEnum action) {
        String time = getLocalTime();
        entry = time + " " + person.getName() + " ";
        if (action == ActionEnum.LOGIN_SUCCESS) entry += "logged in";
        if (action == ActionEnum.LOGOUT_ACTION) entry += "logged out";
        if (action == ActionEnum.UNKNOWN_ACTION) entry += "did something";
        if (action == ActionEnum.CHANGE_VALUE) entry += "changed value";
        System.out.println(person + Integer.toString(action.number()) + entry);
    }
    
    public UserAction(Person admin, Person person, ActionEnum action) {
        String time = getLocalTime();
        entry = time + " " + admin.getName() + " ";
        if (action == ActionEnum.CREATE_PERSON)  entry += "created " + person.getName();
        if (action == ActionEnum.CHANGE_VALUE)   entry += "updated " + person.getName();
        if (action == ActionEnum.SET_ASSIGNMENT) entry += "set assigment for " + person.getName();
        if (action == ActionEnum.DELETE_PERSON)  entry += "deleted " + person.getName();
        System.out.println(person + Integer.toString(action.number()) + entry);
    }

    private String getLocalTime() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return time.format(formatter);
    }

    public String getEntry() {
        return entry;
    }

    public String toString() {
        return entry;
    }
}