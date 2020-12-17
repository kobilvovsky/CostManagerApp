package il.ac.hit.java.costmanagerapp.model;

import static il.ac.hit.java.costmanagerapp.model.utils.AppUtils.checkArgument;
import static java.util.Objects.requireNonNull;


//Instance is created when user registers and login

public class Password {
    public static final String MESSAGE_PASSWORD_CONSTRAINTS =
            "Passwords cannot contain spaces and must be 6 to 100 characters long.";

    private static final String PASSWORD_VALIDATION_REGEX = "[^\\s]{6,100}+$";

    private String password;


    public Password(String password) {
        requireNonNull(password);
        checkArgument(isValidPassword(password), MESSAGE_PASSWORD_CONSTRAINTS);
        setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean isValidPassword(String test) {
        return !test.isEmpty() && test.matches(PASSWORD_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return password;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Password
                && password.equals(((Password) other).password));
    }

}
