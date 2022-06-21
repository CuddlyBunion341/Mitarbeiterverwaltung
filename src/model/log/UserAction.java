package model.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.company.Person;

/**
 * UserAction model class.
 * Stores all the data of a user action.
 * @author Daniel Bengl
 */
public class UserAction {
    public static final int CREATE_PERSON = 0;
    public static final int CHANGE_VALUE = 1;
    public static final int SET_ASSIGNMENT = 2;
    public static final int DELETE_PERSON = 3;
    public static final int LOGIN_SUCCESS = 5;
    public static final int LOGOUT_ACTION = 6;

    private String entry;

    public UserAction(Person person, int action) {
        String time = getLocalTime();
        entry = time + " ";
        if (action == LOGIN_SUCCESS) entry += person.getName() + " logged in";
        if (action == LOGOUT_ACTION) entry += person.getName() + " logged out";
    }

    public UserAction(Person admin, Person person, int action) {
        String time = getLocalTime();
        entry = time + " " + admin.getName() + " ";
        if (action == CREATE_PERSON)  entry += "created " + person.getName();
        if (action == CHANGE_VALUE)   entry += "changed " + person.getName();
        if (action == SET_ASSIGNMENT) entry += "set assigment for " + person.getName();
        if (action == DELETE_PERSON)  entry += "deleted " + person.getName();
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