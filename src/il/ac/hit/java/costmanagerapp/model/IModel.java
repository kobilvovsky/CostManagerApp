package il.ac.hit.java.costmanagerapp.model;

import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface IModel {
    public void setConnection(Connection connection);

    public Connection getConnection();

    public Statement getStatement();

    public ResultSet getRs();

    public String[][] getUserExpenses() throws CostManagerException;

    public boolean isUserMatched(String username, String password) throws CostManagerException;

    public void createExpenses() throws CostManagerException;

    public void createUsers() throws CostManagerException;

    public void addExpense(Expense e) throws CostManagerException;

    public void addUser(User user) throws CostManagerException;
}