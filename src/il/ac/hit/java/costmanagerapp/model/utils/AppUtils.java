package il.ac.hit.java.costmanagerapp.model.utils;

public class AppUtils {
    /**
     * Checks that the condition is true. Used for validating arguments to methods.
     * @param condition condition
     */
    public static void checkArgument(Boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks that the condition is true. Used for validating arguments to methods.
     * @param condition condition
     * @param errorMessage error message
     */
    public static void checkArgument(Boolean condition, String errorMessage) {
        if (!condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}