package il.ac.hit.java.costmanagerapp.model;
import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DerbyDBModel implements IModel {

    private static DerbyDBModel single_instance = null;

    public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    public static String connectionString = "jdbc:derby:CostManagerDB;create=true";

    Connection connection;
    Statement statement;
    ResultSet resultSet = null;

    /**
     * DerbyDBModel constructor
     */
    private DerbyDBModel() throws CostManagerException {
        try {
            Class.forName(driver);
            //createTables();
        } catch (ClassNotFoundException e) {
            throw new CostManagerException("Could not find the Derby JAR file.");
        }
    }
  
    /**
     * Gets singleton instance of DerbyDBModel object
     * @return instance of DerbyDBModel
     */
    public static DerbyDBModel getInstance() throws CostManagerException {
        if (single_instance == null)
            single_instance = new DerbyDBModel();
        return single_instance;

    }

     /**
     * Initialize a connection to the database
     */
    private void init() throws CostManagerException {
        connection = null;
        try {
            setConnection(DriverManager.getConnection(connectionString));
            setStatement(getConnection().createStatement());
        }
        catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
    }
    /**
     * Closes the connection to the database
     */
    private void close() {
        if(getStatement() != null) try { getStatement().close(); } catch (Exception e) {};
        if(getConnection() != null) try { getConnection().close(); } catch (Exception e) {};
        if(getRs() != null) try { getRs().close(); } catch (Exception e) {};
    }

    public String[][] getUserExpenses() throws CostManagerException {
        init();
        ArrayList<ArrayList<String>> allExpenses = new ArrayList<>();
        try {
            setRs(getStatement().executeQuery("SELECT id, cost, category, currency, description, creationDate, dueDate, frequency FROM Expense"));
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
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
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
    public void createTables() throws CostManagerException {
        init();
        createUsers();
        createExpenses();
        close();
    }

    // ALPHA
    public void dropTables() throws CostManagerException {
        init();
        try {
            getStatement().execute("DROP TABLE Users");
            getStatement().execute("DROP TABLE Expense");
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }

        close();
    }

    // ALPHA
    public void createUsers() throws CostManagerException {
        init();
        try {
            getStatement().execute("CREATE TABLE Users(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), username varchar(250) NOT NULL, password varchar(100) NOT NULL, UNIQUE (id))");
            getStatement().execute("INSERT INTO Users(username, password) values ('erez', 'erez')");
            getStatement().execute("INSERT INTO Users(username, password) values ('nati', 'nati')");
            getStatement().execute("INSERT INTO Users(username, password) values ('kobi', 'kobi')");
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        close();
    }

    public void addUser(User user) throws CostManagerException {
        init();
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO Users(username, password) values (?, ?)");
            insertStatement.setString(1, user.getUserName().getName());
            insertStatement.setString(2,user.getUserPassword().getPassword());
            insertStatement.execute();
        } catch (SQLException e) {
            throw new CostManagerException("Could not add user.");
        }
        close();
    }

    // ALPHA
    public void createExpenses() throws CostManagerException {
        init();
        try {
            getStatement().execute("CREATE TABLE Expense(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                    "ownerid int, cost float, category varchar(250) NOT NULL," +
                    "currency int, description varchar(250) NOT NULL," +
                    "creationDate DATE, dueDate DATE, frequency int," +
                    "UNIQUE (id))");
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        close();
    }

    public void addExpense(Expense e) throws CostManagerException {
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

            setRs(getStatement().executeQuery("SELECT id, cost, description, creationDate, category, dueDate FROM Expense"));

            while(getRs().next()) {
                System.out.println("id=" + getRs().getInt("id") + " cost=" + getRs().getDouble("cost") + " description=" + getRs().getString("description")
                        + " creationDate=" + getRs().getDate("creationDate") + " dueDate=" + getRs().getDate("dueDate") + " category=" + getRs().getString("category"));
            }

        } catch (SQLException ex1) {
            throw new CostManagerException("The query was incorrect");
        } catch (NumberFormatException ex2) {
            throw new CostManagerException("Entered wrong data type");
        }

        close();
    }

    public ArrayList<String> getExpense(int id) throws CostManagerException {
        init();

        ArrayList<String> expenseData = new ArrayList<>();
        try {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT cost, category, currency, description, dueDate, frequency FROM Expense WHERE id = ?");
            selectStatement.setInt(1, id);
            setRs(selectStatement.executeQuery());

            if(getRs().next()) {
                expenseData.add(String.valueOf(getRs().getDouble("cost")));
                expenseData.add(getRs().getString("category"));
                expenseData.add(Currency.stringToCurrency(String.valueOf(getRs().getInt("currency"))));
                expenseData.add(getRs().getString("description"));
                expenseData.add(getRs().getDate("dueDate").toString());
                expenseData.add(Frequency.stringToFrequency(String.valueOf(getRs().getInt("frequency"))));
            }
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        close();

        return expenseData;
    }

    public void updateExpense(int id, double amount, Category cat, Currency currency, String description, String date, Frequency freq) throws CostManagerException {
        init();

        try {
            PreparedStatement uploadStatement = connection.prepareStatement("UPDATE Expense SET cost = ?, category = ?, currency = ?, description = ?, dueDate = ?, frequency = ? WHERE id = ?");
            uploadStatement.setDouble(1, amount);
            uploadStatement.setString(2, cat.getCategoryName());
            uploadStatement.setInt(3, currency.getId());
            uploadStatement.setString(4, description);
            uploadStatement.setDate(5, (Date) java.sql.Date.valueOf(date));
            uploadStatement.setInt(6, freq.getId());
            uploadStatement.setInt(7, id);
            uploadStatement.execute();

            setRs(getStatement().executeQuery("SELECT id, cost, description, creationDate, category, dueDate FROM Expense"));

            while(getRs().next()) {
                System.out.println("id=" + getRs().getInt("id") + " cost=" + getRs().getDouble("cost") + " description=" + getRs().getString("description")
                        + " creationDate=" + getRs().getDate("creationDate") + " dueDate=" + getRs().getDate("dueDate") + " category=" + getRs().getString("category"));
            }

        } catch (SQLException ex1) {
            throw new CostManagerException("The query was incorrect");
        } catch (NumberFormatException ex2) {
            throw new CostManagerException("Entered wrong data type");
        }

        close();
    }

    public HashMap<String, Double> getSumPerCategory() throws CostManagerException {
        init();
        HashMap<String, Double> categories = new HashMap<>();
        try {
            setRs(getStatement().executeQuery("SELECT category, SUM(cost) AS total FROM Expense GROUP BY category"));

            while(getRs().next())
                categories.put(getRs().getString("category"), getRs().getDouble("total"));

        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        close();

        return categories;
    }

    public boolean isUserMatched(String username, String password) throws CostManagerException {
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

        } catch (SQLException ex1) {
            throw new CostManagerException("The query was incorrect");
        } catch (NumberFormatException ex2) {
            throw new CostManagerException("Entered wrong data type");
        }

        close();
        return r;
    }
}