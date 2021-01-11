package il.ac.hit.java.costmanagerapp.model;
import java.sql.*;
import java.util.ArrayList;

public class DerbyDBModel implements IModel {

    private static DerbyDBModel single_instance = null;

    public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    public static String connectionString = "jdbc:derby:CostManagerDB;create=true";

    Connection connection;
    Statement statement;
    ResultSet resultSet = null;

    /**
     * DerbyDBModel constructor
     * @throws ClassNotFoundException if database wasn't initiated properly
     */
    private DerbyDBModel() throws ClassNotFoundException {
        Class.forName(driver);
    }

    /**
     * Gets singleton instance of DerbyDBModel object
     * @return instance of DerbyDBModel
     * @throws ClassNotFoundException if database wasn't initiated properly
     */
    public static DerbyDBModel getInstance() throws ClassNotFoundException {
        if (single_instance == null)
            single_instance = new DerbyDBModel();

        return single_instance;
    }

    /**
     * Initialize a connection to the database
     * @throws SQLException if there was an error with a query
     */
    private void init() throws SQLException {
        connection = null;
        setConnection(DriverManager.getConnection(connectionString));
        setStatement(getConnection().createStatement());
    }

    /**
     * Closes the connection to the database
     */
    private void close() {
        if(getStatement() != null) try { getStatement().close(); } catch (Exception e) {};
        if(getConnection() != null) try { getConnection().close(); } catch (Exception e) {};
        if(getRs() != null) try { getRs().close(); } catch (Exception e) {};
    }

    public String[][] getUserExpenses() throws SQLException {
        init();
        setRs(getStatement().executeQuery("SELECT id, cost, category, currency, description, creationDate, dueDate, frequency FROM Expense"));

        ArrayList<ArrayList<String>> allExpenses = new ArrayList<>();
        while(getRs().next()) {
            ArrayList<String> currExpense = new ArrayList<>();
            currExpense.add(String.valueOf(getRs().getInt("id")));
            currExpense.add(String.valueOf(getRs().getDouble("cost")));
            currExpense.add(getRs().getString("category"));
            currExpense.add(Currency.stringToCurrency(String.valueOf(getRs().getInt("currency"))));
            currExpense.add(getRs().getString("description"));
            currExpense.add(getRs().getDate("creationDate").toString());
            currExpense.add(getRs().getDate("dueDate").toString());
            currExpense.add(Frequency.stringToFrequency(String.valueOf(getRs().getInt("frequency"))));
            allExpenses.add(currExpense);
        }

        close();

        String[][] data = new String[allExpenses.size()][];
        int i = 0, j = 0;
        for (ArrayList<String> row: allExpenses) {
            data[i] = new String[row.size()];
            j = 0;
            for (String str: row) {
                data[i][j] = str;
                j++;
            }
            i++;
        }

        return data;
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

    // ALPHA
    public void createTables() throws SQLException {
        init();
        createUsers();
        createExpenses();
        close();
    }

    // ALPHA
    public void dropTables() throws SQLException {
        init();
        getStatement().execute("DROP TABLE Users");
        getStatement().execute("DROP TABLE Expense");
        close();
    }

    // ALPHA
    public void createUsers() throws SQLException {
        init();
        getStatement().execute("CREATE TABLE Users(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), username varchar(250) NOT NULL, password varchar(100) NOT NULL, UNIQUE (id))");
        getStatement().execute("INSERT INTO Users(username, password) values ('erez', 'erez')");
        getStatement().execute("INSERT INTO Users(username, password) values ('nati', 'nati')");
        getStatement().execute("INSERT INTO Users(username, password) values ('kobi', 'kobi')");
        close();
    }

    public void addUser(User user) throws SQLException {
        init();
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO Users(username, password) values (?, ?)");
        insertStatement.setString(1, user.getUserName().getName());
        insertStatement.setString(2,user.getUserPassword().getPassword());
        insertStatement.execute();

        close();
    }

    // ALPHA
    public void createExpenses() throws SQLException {
        init();
        getStatement().execute("CREATE TABLE Expense(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                "ownerid int, cost float, category varchar(250) NOT NULL," +
                "currency int, description varchar(250) NOT NULL," +
                "creationDate DATE, dueDate DATE, frequency int," +
                "UNIQUE (id))");

        close();
    }

    public void addExpense(Expense e) throws SQLException {
        init();

        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO Expense(ownerid, cost, category, currency, description, creationDate, dueDate, frequency) values (?, ?, ?, ?, ?, ?, ?, ?)");
            insertStatement.setInt(1, e.getOwner());
            insertStatement.setDouble(2, e.getCost());
            insertStatement.setString(3, e.getCategory().getCategoryName());
            insertStatement.setInt(4, e.getCurrency().getId());
            insertStatement.setString(5, e.getDescription());
            insertStatement.setDate(6, (Date) e.getCreationDate());
            insertStatement.setDate(7, (Date) e.getDueDate());
            insertStatement.setInt(8, e.getType().getId());
            insertStatement.execute();
        } catch (SQLException | NumberFormatException throwables) {
            throwables.printStackTrace();
        }

        setRs(getStatement().executeQuery("SELECT id, cost, description, creationDate, category, dueDate FROM Expense")); // execute = multiple results

        while(getRs().next()) {
            System.out.println("id=" + getRs().getInt("id") + " cost=" + getRs().getDouble("cost") + " description=" + getRs().getString("description")
                    + " creationDate=" + getRs().getDate("creationDate") + " dueDate=" + getRs().getDate("dueDate") + " category=" + getRs().getString("category"));
        }

        close();
    }
    public boolean isUserMatched(String username, String password) throws SQLException {
        init();
        boolean r = false;

        try {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT id FROM Users WHERE username = ? AND password = ?");
            selectStatement.setString(1, username);
            selectStatement.setString(2, password);
            setRs(selectStatement.executeQuery());

            while(getRs().next()) {
                r = true;
                System.out.println(getRs().getInt("id"));
            }

        } catch (SQLException | NumberFormatException throwables) {
            throwables.printStackTrace();
        }

        close();
        return r;
    }
}