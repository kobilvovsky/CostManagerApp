package il.ac.hit.java.costmanagerapp.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface IModel {
    public void setConnection(Connection connection);
    public Connection getConnection();
    public Statement getStatement();
    public ResultSet getRs();
}
