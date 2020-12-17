package il.ac.hit.java.costmanagerapp.model;
import java.sql.*;

public class DerbyDBModel implements IModel {

    public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    public static String connectionString = "jdbc:derby:CostManagerDB;create=true"; //jdbc:derby:CostManagerDB;create=true
    //CostManagerApp\src\il\ac\hit\java\costmanagerapp

    Connection connection;
    Statement statement;
    ResultSet resultSet = null;

    public DerbyDBModel() throws ClassNotFoundException {
        try {
            connection = null;
            Class.forName(driver);
            setConnection(DriverManager.getConnection(connectionString));
            setStatement(getConnection().createStatement());

            createTables();
            setRs(getStatement().executeQuery("SELECT id, description, dueDate FROM Expense WHERE dueDate >= '2020-12-01' AND dueDate <= '2020-12-30'")); // execute = multiple results

            while(getRs().next()) {
                System.out.println("id=" + getRs().getInt("id") + " description=" + getRs().getString("description")
                + " dueDate=" + getRs().getDate("dueDate"));
            }

            dropTables();

        } catch (SQLException e) { //catch (SQLException | ClassNotFoundException e)
            e.printStackTrace();
        } finally {
            if(getStatement() != null) try { getStatement().close(); } catch (Exception e) {};
            if(getConnection() != null) try { getConnection().close(); } catch (Exception e) {};
            if(getRs() != null) try { getRs().close(); } catch (Exception e) {};
        }
    }
    
    public int isUserMatched(Username username, Password password) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM Users WHERE username = ? AND password = ?")) {
            stmt.setString(1, username.getName());
            stmt.setString(2, password.getPassword());
            setRs(stmt.executeQuery());
            if (getRs().next())
                return getRs().getInt("id");
        }
        return -1;
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
        createCurrency();
        createFrequency();
        createUsers();
        createExpenses();
    }

    public void dropTables() throws SQLException {
        getStatement().execute("DROP TABLE Category");
        getStatement().execute("DROP TABLE Currency");
        getStatement().execute("DROP TABLE Users");
        getStatement().execute("DROP TABLE Expense");
        getStatement().execute("DROP TABLE Frequency");
    }

    public void createUsers() throws SQLException {
        getStatement().execute("CREATE TABLE Users(id int, username varchar(250), password varchar(100))");
        getStatement().execute("INSERT INTO Users values (1, 'erez', 'erez')");
        getStatement().execute("INSERT INTO Users values (2, 'nati', 'nati')");
        getStatement().execute("INSERT INTO Users values (3, 'kobi', 'kobi')");
    }

    public void createExpenses() throws SQLException {
        getStatement().execute("CREATE TABLE Expense(id int, ownerid int, name varchar(250), cost int, category int," +
                "currency varchar(250), description varchar(250)," +
                "creationDate DATE, dueDate DATE, type int)");
        getStatement().execute("INSERT INTO Expense values (1, 1, 'expName', 100, 2, '$', 'abba...', '2020-12-17', '2020-12-21', 1)");
        getStatement().execute("INSERT INTO Expense values (2, 1, 'expName2', 200, 3, 'E', 'qqqq...', '2020-12-17', '2021-01-20', 1)");
        getStatement().execute("INSERT INTO Expense values (3, 2, 'expName3', 300, 1, '$', 'zzzz...', '2020-12-17', '2020-12-23', 1)");
        getStatement().execute("INSERT INTO Expense values (4, 3, 'expName4', 400, 1, 'N', 'aaaa...', '2020-12-17', '2020-12-25', 2)");
        getStatement().execute("INSERT INTO Expense values (5, 1, 'expName5', 500, 2, '$', 'nnnnnnn...', '2020-12-17', '2022-01-01', 3)");
    }

    public void createFrequency() throws SQLException {
        getStatement().execute("CREATE TABLE Frequency(id int, name varchar(10))");
        getStatement().execute("INSERT INTO Frequency values (1, 'One Time')");
        getStatement().execute("INSERT INTO Frequency values (2, 'Monthly')");
        getStatement().execute("INSERT INTO Frequency values (3, 'Yearly')");
    }

    public void createCategories() throws SQLException {
        getStatement().execute("CREATE TABLE Category(id int, name varchar(15))");
        getStatement().execute("INSERT INTO Category values (1,  'House')");
        getStatement().execute("INSERT INTO Category values (2,  'Insurance')");
        getStatement().execute("INSERT INTO Category values (3,  'Dining')");
        getStatement().execute("INSERT INTO Category values (4,  'Entertainment')");
        getStatement().execute("INSERT INTO Category values (5,  'Shopping')");
        getStatement().execute("INSERT INTO Category values (6,  'Medical')");
        getStatement().execute("INSERT INTO Category values (7,  'Travel')");
        getStatement().execute("INSERT INTO Category values (8,  'Groceries')");
        getStatement().execute("INSERT INTO Category values (9,  'Car')");
        getStatement().execute("INSERT INTO Category values (10, 'Tax')");
        getStatement().execute("INSERT INTO Category values (11, 'Others')");
        getStatement().execute("INSERT INTO Category values (12, 'General')");
    }

    public void createCurrency() throws SQLException {
        getStatement().execute("CREATE TABLE Currency(id int, name varchar(10))");
        getStatement().execute("INSERT INTO Currency values (1, 'USD')");
        getStatement().execute("INSERT INTO Currency values (2, 'Euro')");
        getStatement().execute("INSERT INTO Currency values (3, 'Yen')");
        getStatement().execute("INSERT INTO Currency values (4, 'Pound')");
        getStatement().execute("INSERT INTO Currency values (5, 'NIS')");
    }

    public void test() {

/*      getStatement().execute("CREATE TABLE inventory(id int, fee double)"); // executeUpdate - may return nothing
        getStatement().execute("INSERT INTO inventory values (100212, 2.5)");
        getStatement().execute("INSERT INTO inventory values (100213, 1.2)");
        setRs(getStatement().executeQuery("SELECT id, fee FROM inventory ORDER BY id")); // execute = multiple results

        while(getRs().next()) {
            System.out.println("id=" + getRs().getInt("id") + " fee=" + getRs().getDouble("fee"));
        }*/
    }
}