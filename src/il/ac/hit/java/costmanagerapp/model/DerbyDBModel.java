package il.ac.hit.java.costmanagerapp.model;
import java.sql.*;

public class DerbyDBModel implements IModel {

    public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    public static String connectionString = "jdbc:derby:CostManagerDB;create=true"; //jdbc:derby:CostManagerDB;create=true
    //CostManagerApp\src\il\ac\hit\java\costmanagerapp

    Connection connection;
    Statement statement;
    ResultSet resultSet = null;

    public DerbyDBModel() throws ClassNotFoundException, SQLException {
        try {
            connection = null;
            Class.forName(driver);
            setConnection(DriverManager.getConnection(connectionString));

            createTables();
            dropTables();
        } catch (SQLException e) { //catch (SQLException | ClassNotFoundException e)
            e.printStackTrace();
        } finally {
            if(getStatement() != null) try { getStatement().close(); } catch (Exception e) {};
            if(getConnection() != null) try { getConnection().close(); } catch (Exception e) {};
            if(getRs() != null) try { getRs().close(); } catch (Exception e) {};
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getRs() {
        return resultSet;
    }

    public void setRs(ResultSet rs) {
        this.resultSet = rs;
    }

    public void createTables() throws SQLException {
        createCategories();
    }

    public void dropTables() throws SQLException {
        getStatement().execute("DROP TABLE inventory");
    }

    public void createCategories() throws SQLException {
        getStatement().execute("CREATE TABLE Category(id int, name string)");
        getStatement().execute("INSERT INTO Category values (1,  House)");
        getStatement().execute("INSERT INTO Category values (2,  Insurance)");
        getStatement().execute("INSERT INTO Category values (3,  Dining)");
        getStatement().execute("INSERT INTO Category values (4,  Entertainment)");
        getStatement().execute("INSERT INTO Category values (5,  Shopping)");
        getStatement().execute("INSERT INTO Category values (6,  Medical)");
        getStatement().execute("INSERT INTO Category values (7,  Travel)");
        getStatement().execute("INSERT INTO Category values (8,  Groceries)");
        getStatement().execute("INSERT INTO Category values (9,  Car)");
        getStatement().execute("INSERT INTO Category values (10, Tax)");
        getStatement().execute("INSERT INTO Category values (11, Others)");
        getStatement().execute("INSERT INTO Category values (12, General)");
    }

    public void test() {

/*      setStatement(getConnection().createStatement());
        getStatement().execute("CREATE TABLE inventory(id int, fee double)"); // executeUpdate - may return nothing
        getStatement().execute("INSERT INTO inventory values (100212, 2.5)");
        getStatement().execute("INSERT INTO inventory values (100213, 1.2)");
        setRs(getStatement().executeQuery("SELECT id, fee FROM inventory ORDER BY id")); // execute = multiple results

        while(getRs().next()) {
            System.out.println("id=" + getRs().getInt("id") + " fee=" + getRs().getDouble("fee"));
        }*/
    }
}