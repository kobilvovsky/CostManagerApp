package il.ac.hit.java.costmanagerapp.model;

import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public interface IModel {
    /**
     * Set database's session object
     * @param connection Connection object
     */
    public void setConnection(Connection connection);

    /**
     * Gets session of database
     * @return Connection object
     */
    public Connection getConnection();

    /**
     * Gets query statement
     * @return Statement object
     */
    public Statement getStatement();

    /**
     * Sends a statment query to the database
     */
    public void setStatement(Statement statement);

    /**
     * Gets result set object
     * @return ResultSet object
     */
    public ResultSet getRs();
  
    /**
     * Sets the result set table
     */
    public void setRs(ResultSet rs);

    /**
     * Gets all expenses of a user
     * @return 2D String array
     */
    public String[][] getUserExpenses() throws CostManagerException;
  
    /**
     * Checks if entered username and password are correct against the database
     * @param username Username object
     * @param password Password Object
     * @return true if credentials are correct, otherwise false
     */
    public boolean isUserMatched(String username, String password) throws CostManagerException;
  
    // ALPHA
    public void createExpenses() throws CostManagerException;
  
    // ALPHA
    public void createUsers() throws CostManagerException;

    /**
     * Gets expense data by id
     * @param id expense id
     * @throws CostManagerException
     */
    public ArrayList<String> getExpense(int id) throws CostManagerException;

    /**
     * returns hashmap of categories and respected total sum per date
     * @return hashmap of categories and respected total sum per date
     * @throws CostManagerException
     */
    public HashMap<String, Double> getSumPerCategory(String start, String end) throws CostManagerException;

    /**
     * Updates an expense with new data
     * @param id id of expense
     * @param amount cost of expense
     * @param cat category of expense
     * @param currency currency of expense
     * @param description description of expense
     * @param date due date of expense
     * @param freq frequency of expense
     * @return returns true of updated, otherwise false
     * @throws CostManagerException
     */
    public boolean updateExpense(int id, double amount, Category cat, Currency currency, String description, String date, Frequency freq) throws CostManagerException;
  
    /**
     * Inserts a new Expense to the database
     * @return returns true of expense created, otherwise false
     * @param e Expense object
     */
    public boolean addExpense(Expense e) throws CostManagerException;

    /**
     * Inserts a new User to the database
     * @return returns true of user created, otherwise false
     * @param user User object
     */
    public boolean addUser(User user) throws CostManagerException;

    /**
     * Deletes an expense
     * @param id id of expense
     * @return returns true of deleted, otherwise false
     * @throws CostManagerException
     */
    public boolean deleteExpense(int id) throws CostManagerException;
}