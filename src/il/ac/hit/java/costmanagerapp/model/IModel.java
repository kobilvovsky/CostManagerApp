package il.ac.hit.java.costmanagerapp.model;

import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
     * @throws SQLException if there was an error with the query
     * @throws ClassNotFoundException if database wasn't initiated properly
     */
    public String[][] getUserExpenses() throws CostManagerException;
  
      /**
     * Checks if entered username and password are correct against the database
     * @param username Username object
     * @param password Password Object
     * @return true if credentials are correct, otherwise false
     * @throws SQLException if there was an error with the query
     * @throws ClassNotFoundException if database wasn't initiated properly
     */
    public boolean isUserMatched(String username, String password) throws CostManagerException;
  
      // ALPHA
    public void createExpenses() throws CostManagerException;
  
    // ALPHA
    public void createUsers() throws CostManagerException;
  
      /**
     * Inserts a new Expense to the database
     * @param e Expense object
     * @throws SQLException if there was an error with the query
     * @throws ClassNotFoundException if database wasn't initiated properly
     */
    public void addExpense(Expense e) throws CostManagerException;

      /**
     * Inserts a new User to the database
     * @param user User object
     * @throws SQLException if there was an error with the query
     * @throws ClassNotFoundException if database wasn't initiated properly
     */
    public void addUser(User user) throws CostManagerException;
}