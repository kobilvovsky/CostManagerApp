package il.ac.hit.java.costmanagerapp.model;

import static java.util.Objects.requireNonNull;
import static il.ac.hit.java.costmanagerapp.model.utils.AppUtils.checkArgument;

public class Username implements Comparable<Username> {
    public static final String MESSAGE_NAME_CONSTRAINTS =
            "Names must be at most 250 characters long, cannot be empty and cannot contain white spaces or any of "
                    + "these characters: \" > < : \\ / | ? *";

    private static final String USERNAME_VALIDATION_REGEX = ".*[/\\\\:*?\"<>|\\s].*";

    private String name;

    /**
     * Username constructor
     * @param name name of the user
     */
    public Username(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_NAME_CONSTRAINTS);
        setName(name);
    }

    /**
     * Gets user name
     * @return user name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets user name
     * @param name name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return !test.isEmpty() && !test.matches(USERNAME_VALIDATION_REGEX) && test.length() <= 250;
    }

    /**
     * Prints username variables data
     * @return username object as string
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Checks if username equals to a different username object
     * @param other username object
     * @return true if match, otherwise false
     */
    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof Username
            && name.equalsIgnoreCase(((Username) other).name));
    }

    /**
     * Compares entered username as text to another username text
     * @param o username object
     * @return true if match, otherwise false
     */
    @Override
    public int compareTo(Username o) {
        return String.CASE_INSENSITIVE_ORDER.compare(this.name, o.name);
    }
}

