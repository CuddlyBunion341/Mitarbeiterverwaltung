package model.log;

import model.company.Person;

public class UserAction {
    public static final int CREATE_PERSON = 0;
    public static final int CHANGE_VALUE = 1;
    public static final int SET_ASSIGNMENT = 2;
    public static final int DELETE_PERSON = 3;

    private String[] actionDescription;
    private String entry;

    public UserAction(Person person, int action) {

    }

    public String getEntry() {
        return entry;
    }
}
