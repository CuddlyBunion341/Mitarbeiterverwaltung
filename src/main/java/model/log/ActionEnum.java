package main.java.model.log;

/**
 * Enum for the different actions that can be performed.
 * @author Daniel Bengl
 */
public enum ActionEnum {
    CREATE_PERSON(0),
    CHANGE_VALUE(1),
    SET_ASSIGNMENT(2),
    DELETE_PERSON(3),
    UPDATE_PERSON(4),
    LOGIN_SUCCESS(5),
    LOGOUT_ACTION(6),
    UNKNOWN_ACTION(7);

    private final int number;
    ActionEnum(int number) {
        this.number = number;
    }

    /**
     * Returns the action number
     * @return Action number
     */
    public int number() { return number; }
}
