package il.ac.hit.java.costmanagerapp.model.exceptions;

public class CostManagerException extends Exception {

    /**
     * CostManagerException constructor
     * @param message exception message
     */
    public CostManagerException(String message) {
        super(message);
    }

    /**
     * CostManagerException constructor
     * @param message exception message
     * @param cause exception cause
     */
    public CostManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
