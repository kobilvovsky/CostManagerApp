package il.ac.hit.java.costmanagerapp.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface IModel {
    public void setConnection(Connection connection);
    public Connection getConnection();
    public Statement getStatement();
    public ResultSet getRs();

    public void createTables() throws SQLException;
    public void dropTables() throws SQLException;

    public void createCategories() throws SQLException;
    public void createCurrency() throws SQLException;
    public void createFrequency() throws SQLException;
}
