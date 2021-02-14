/*
    Submitted by:
    Erez Mizrahi 316267087
    Lvovsky Yakov 315825380
    Netanel Tarnorudsky 315424689
 */

package il.ac.hit.java.costmanagerapp.viewmodel;

import il.ac.hit.java.costmanagerapp.model.*;
import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;
import il.ac.hit.java.costmanagerapp.view.IView;

public interface IViewModel {

    /**
     * Sets model
     * @param model model
     */
    public void setModel(IModel model);

    /**
     * Sets view
     * @param view view model
     */
    public void setView(IView view);

    /**
     * Adds a new user to the database
     * @param user user object
     * @throws CostManagerException
     */
    public void addUser(User user) throws CostManagerException;
  
    /**
     * Adds new expense to the database
     * @param expense Expense object
     * @throws CostManagerException
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
     * Gets user expenses
     * @throws CostManagerException
     */
    public void getUserExpenses() throws CostManagerException;

    /**
     * Call on database with requested start date and end date to get expenses
     * @param start start date
     * @param end end date
     * @throws CostManagerException
     */
    public void getSumPerCategory(String start, String end) throws CostManagerException;

    /**
     * Deletes an expense
     * @param id id of expense
     * @throws CostManagerException
     */
    public void deleteExpense(int id) throws CostManagerException;
}
