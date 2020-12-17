package il.ac.hit.java.costmanagerapp.model.utils;


public class AppUtils {
     //Checks that the condition is true. Used for validating arguments to methods.
     //throws IllegalArgumentException if the condition is false.

    public static void checkArgument(Boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(Boolean condition, String errorMessage) {
        if (!condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}

