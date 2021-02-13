package il.ac.hit.java.costmanagerapp.viewmodel;

import il.ac.hit.java.costmanagerapp.model.*;
import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;
import il.ac.hit.java.costmanagerapp.view.IView;

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

    /**
     * Adds a new user to the database
     * @param user user object
     */
    public void addUser(User user) throws CostManagerException;
  
    /**
     * Adds new expense to the database
     * @param expense Expense object
     */
    public void addExpense(Expense expense) throws CostManagerException;

    /**
     * Gets expense data by id
     * @param id expense id
     * @throws CostManagerException
     */
    public void getExpense(int id) throws CostManagerException;

    /**
     * Updates an expense with new data
     * @param id id of expense
     * @param amount cost of expense
     * @param cat category of expense
     * @param currency currency of expense
     * @param description description of expense
     * @param date due date of expense
     * @param freq frequency of expense
     * @throws CostManagerException
     */
    public void updateExpense(int id, double amount, Category cat, Currency currency, String description, String date, Frequency freq) throws CostManagerException;

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
     */
    public void getUserExpenses() throws CostManagerException;

    public void getSumPerCategory() throws CostManagerException;

}
