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

    public String[][] getUserExpenses() throws SQLException, ClassNotFoundException;

    public boolean isUserMatched(String username, String password) throws SQLException, ClassNotFoundException;

    public void createExpenses() throws CostManagerException, SQLException, ClassNotFoundException;

    public void createUsers() throws CostManagerException, SQLException, ClassNotFoundException;

    public void addExpense(Expense e) throws SQLException, ClassNotFoundException;
}