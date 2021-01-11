package il.ac.hit.java.costmanagerapp.viewmodel;

import il.ac.hit.java.costmanagerapp.model.Expense;
import il.ac.hit.java.costmanagerapp.model.IModel;
import il.ac.hit.java.costmanagerapp.model.User;
import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;
import il.ac.hit.java.costmanagerapp.view.IView;

import java.sql.SQLException;

public interface IViewModel {

    /**
     * Set view's model
     * @param model model
     */
    public void setModel(IModel model);

    /**
     * Set view's view model
     * @param view view model
     */
    public void setView(IView view);
<<<<<<< HEAD
    public void addUser(User user) throws CostManagerException;
    public void addExpense(Expense expense) throws CostManagerException;
    public boolean isUserMatched(String username, String password);
    public String[][] getUserExpenses() throws CostManagerException;
=======

    /**
     * Adds a new user to the database
     * @param user user object
     */
    public void addUser(User user);

    /**
     * Adds new expense to the database
     * @param expense Expense object
     * @throws SQLException if there was an error with the query
     * @throws ClassNotFoundException if database wasn't initiated properly
     */
    public void addExpense(Expense expense) throws SQLException, ClassNotFoundException;

    /**
     * Checks if credentials match to a user in the database
     * @param username Username object
     * @param password Password Object
     * @return true if correct, otherwise false
     */
    public boolean isUserMatched(String username, String password);

    /**
     * Creates a 2D string array of all expenses data
     * @return String 2D array
     * @throws SQLException if there was an error with the query
     * @throws ClassNotFoundException if database wasn't initiated properly
     */
    public String[][] getUserExpenses() throws SQLException, ClassNotFoundException;
>>>>>>> 662700b1250f6a7e0c60b873e8a579c483d2ee9f
}
