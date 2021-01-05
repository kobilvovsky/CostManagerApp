package il.ac.hit.java.costmanagerapp.model;
import java.sql.*;

public class DerbyDBModel implements IModel {

    private static DerbyDBModel single_instance = null;

    public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    public static String connectionString = "jdbc:derby:CostManagerDB;create=true";

    Connection connection;
    Statement statement;
    ResultSet resultSet = null;

    private DerbyDBModel() throws ClassNotFoundException {
        try {
            init();
        } catch (SQLException e) {
            System.out.println("Cant Create derby DB");
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public static DerbyDBModel getInstance() throws ClassNotFoundException {
        if (single_instance == null)
            single_instance = new DerbyDBModel();

        return single_instance;
    }

    private void init() throws ClassNotFoundException, SQLException {
        connection = null;
        Class.forName(driver);
        setConnection(DriverManager.getConnection(connectionString));
        setStatement(getConnection().createStatement());
    }

    private void close() {
        if(getStatement() != null) try { getStatement().close(); } catch (Exception e) {};
        if(getConnection() != null) try { getConnection().close(); } catch (Exception e) {};
        if(getRs() != null) try { getRs().close(); } catch (Exception e) {};
    }

    public void simpleQuery() throws SQLException, ClassNotFoundException {
        init();
        setRs(getStatement().executeQuery("SELECT id, description, dueDate FROM Expense WHERE dueDate >= '2020-12-01' AND dueDate <= '2020-12-30'")); // execute = multiple results

        while(getRs().next()) {
            System.out.println("id=" + getRs().getInt("id") + " description=" + getRs().getString("description")
                    + " dueDate=" + getRs().getDate("dueDate"));
        }

        close();
    }

    public String[][] getUserExpenses() throws SQLException, ClassNotFoundException {
        init();
        setRs(getStatement().executeQuery("SELECT id, cost, category, currency, description, creationDate, dueDate, frequency FROM Expense WHERE ownerid = 1"));

        String data[][] = new String[0][];
        int row = 0;
        int col = 0;
        while(getRs().next()) {
            data[row][col++] = String.valueOf(getRs().getInt("id"));
            data[row][col++] = String.valueOf(getRs().getInt("cost"));
            data[row][col++] = getRs().getString("category");
            data[row][col++] = String.valueOf(getRs().getInt("currency"));
            data[row][col++] = getRs().getString("description");
            data[row][col++] = getRs().getDate("creationDate").toString();
            data[row][col++] = getRs().getDate("dueDate").toString();
            data[row][col++] = String.valueOf(getRs().getInt("frequency"));

            row++;
            col = 0;
        }

        close();

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

    public void createTables() throws SQLException, ClassNotFoundException {
        init();
        createUsers();
        createExpenses();
        close();
    }

    public void dropTables() throws SQLException, ClassNotFoundException {
        init();
        getStatement().execute("DROP TABLE Users");
        getStatement().execute("DROP TABLE Expense");
        close();
    }

    public void createUsers() throws SQLException, ClassNotFoundException {
        init();
        getStatement().execute("CREATE TABLE Users(id int, username varchar(250), password varchar(100))");
        getStatement().execute("INSERT INTO Users values (1, 'erez', 'erez')");
        getStatement().execute("INSERT INTO Users values (2, 'nati', 'nati')");
        getStatement().execute("INSERT INTO Users values (3, 'kobi', 'kobi')");
        close();
    }

    public void createExpenses() throws SQLException, ClassNotFoundException {
        init();
        getStatement().execute("CREATE TABLE Expense(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                "ownerid int, cost int, category varchar(250) NOT NULL," +
                "currency int, description varchar(250) NOT NULL," +
                "creationDate DATE, dueDate DATE, frequency int," +
                "UNIQUE (id))");
        getStatement().execute("INSERT INTO Expense(ownerid, cost, category, currency, description, creationDate, dueDate, frequency) values (1, 100, 'House', 1, 'abba...', '2020-12-17', '2020-12-21', 1)");
        getStatement().execute("INSERT INTO Expense(ownerid, cost, category, currency, description, creationDate, dueDate, frequency) values (1, 200, 'Tax',   2, 'qqqq...', '2020-12-17', '2021-01-20', 1)");
        getStatement().execute("INSERT INTO Expense(ownerid, cost, category, currency, description, creationDate, dueDate, frequency) values (2, 300, 'Tax',   1, 'zzzz...', '2020-12-17', '2020-12-23', 1)");
        getStatement().execute("INSERT INTO Expense(ownerid, cost, category, currency, description, creationDate, dueDate, frequency) values (3, 400, 'Car',   3, 'aaaa...', '2020-12-17', '2020-12-25', 2)");
        getStatement().execute("INSERT INTO Expense(ownerid, cost, category, currency, description, creationDate, dueDate, frequency) values (1, 500, 'Car',   1, '1234...', '2020-12-17', '2022-01-01', 3)");
        close();
    }

    public void addExpense(Expense e) throws SQLException, ClassNotFoundException {
        init();

        System.out.println(e.toString());

        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO Expense(ownerid, cost, category, currency, description, creationDate, dueDate, frequency) values (?, ?, ?, ?, ?, ?, ?, ?)");
            insertStatement.setInt(1, e.getOwner());
            insertStatement.setInt(2, (int) e.getCost());
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

        setRs(getStatement().executeQuery("SELECT id, description, creationDate, category, dueDate FROM Expense")); // execute = multiple results

        while(getRs().next()) {
            System.out.println("id=" + getRs().getInt("id") + " description=" + getRs().getString("description")
                    + " creationDate=" + getRs().getDate("creationDate") + " dueDate=" + getRs().getDate("dueDate") + " category=" + getRs().getString("category"));
        }

        close();
    }

    //getStatement().execute("INSERT INTO Users values (1, 'erez', 'erez')");

    public boolean isUserMatched(String username, String password) throws SQLException, ClassNotFoundException {
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