package il.ac.hit.java.costmanagerapp.model;

import static il.ac.hit.java.costmanagerapp.model.utils.AppUtils.checkArgument;
import static java.util.Objects.requireNonNull;

public class Password {
    public static final String MESSAGE_PASSWORD_CONSTRAINTS =
            "Passwords cannot contain spaces and must be 6 to 100 characters long.";

    private static final String PASSWORD_VALIDATION_REGEX = "[^\\s]{6,100}+$";

    private String password;

    /**
     * Password constructor
     * @param password password of the user
     */
    public Password(String password) {
        requireNonNull(password);
        checkArgument(isValidPassword(password), MESSAGE_PASSWORD_CONSTRAINTS);
        setPassword(password);
    }

    /**
     * Gets user password
     * @return user password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets user password
     * @param password password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks if password is valid by matching to regex
     * @param pass password
     * @return true if password is valid, otherwise false
     */
    public static boolean isValidPassword(String pass) {
        return !pass.isEmpty() && pass.matches(PASSWORD_VALIDATION_REGEX);
    }

    /**
     * Prints password variables data
     * @return password object as string
     */
    @Override
    public String toString() {
        return this.password;
    }
}
