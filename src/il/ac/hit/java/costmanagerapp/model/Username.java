package il.ac.hit.java.costmanagerapp.model;

import static java.util.Objects.requireNonNull;
import static il.ac.hit.java.costmanagerapp.model.utils.AppUtils.checkArgument;


//Instance is created when user registers and login

public class Username implements Comparable<Username> {
    public static final String MESSAGE_NAME_CONSTRAINTS =
            "Names must be at most 250 characters long, cannot be empty and cannot contain white spaces or any of "
                    + "these characters: \" > < : \\ / | ? *";

    /*
     * Username cannot contain any of the following characters : " > < : \ / | ? *
     */
    private static final String USERNAME_VALIDATION_REGEX = ".*[/\\\\:*?\"<>|\\s].*";

    private String name;


    public Username(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_NAME_CONSTRAINTS);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return !test.isEmpty() && !test.matches(USERNAME_VALIDATION_REGEX) && test.length() <= 250;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Username // instanceof handles nulls
                && name.equalsIgnoreCase(((Username) other).name)); // state check
    }


    @Override
    public int compareTo(Username o) {
        return String.CASE_INSENSITIVE_ORDER.compare(this.name, o.name);
    }
}

