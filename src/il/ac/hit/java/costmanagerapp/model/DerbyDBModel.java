/*
    Submitted by:
    Erez Mizrahi 316267087
    Lvovsky Yakov 315825380
    Netanel Tarnorudsky 315424689
 */

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

    public boolean addUser(User user) throws CostManagerException {
        init();
        int usersAdded = 0;
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO Users(username, password) values (?, ?)");
            insertStatement.setString(1, user.getUserName().getName());
            insertStatement.setString(2,user.getUserPassword().getPassword());
            usersAdded = insertStatement.executeUpdate();
        } catch (SQLException e) {
            throw new CostManagerException("Could not add user.");
        }
        close();

        return usersAdded > 0;
    }

    public boolean addExpense(Expense e) throws CostManagerException {
        init();
        int expensesAdded = 0;
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO Expense(cost, category, currency, description, creationDate, dueDate, frequency) values (?, ?, ?, ?, ?, ?, ?)");
            insertStatement.setDouble(1, e.getCost());
            insertStatement.setString(2, e.getCategory().getCategoryName());
            insertStatement.setInt(3, e.getCurrency().getId());
            insertStatement.setString(4, e.getDescription());
            insertStatement.setDate(5, (Date) e.getCreationDate());
            insertStatement.setDate(6, (Date) e.getDueDate());
            insertStatement.setInt(7, e.getType().getId());
            expensesAdded = insertStatement.executeUpdate();
        } catch (SQLException ex1) {
            throw new CostManagerException("The query was incorrect");
        } catch (NumberFormatException ex2) {
            throw new CostManagerException("Entered wrong data type");
        }

        close();
        return expensesAdded > 0;
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

    public boolean deleteExpense(int id) throws CostManagerException {
        init();
        int countDeleted = 0;
        try {
            PreparedStatement selectStatement = connection.prepareStatement("DELETE FROM Expense WHERE id = ?");
            selectStatement.setInt(1, id);
            countDeleted = selectStatement.executeUpdate();
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        close();

        return countDeleted > 0;
    }

    public boolean updateExpense(int id, double amount, Category cat, Currency currency, String description, String date, Frequency freq) throws CostManagerException {
        init();
        int countUpdated = 0;
        try {
            PreparedStatement uploadStatement = connection.prepareStatement("UPDATE Expense SET cost = ?, category = ?, currency = ?, description = ?, dueDate = ?, frequency = ? WHERE id = ?");
            uploadStatement.setDouble(1, amount);
            uploadStatement.setString(2, cat.getCategoryName());
            uploadStatement.setInt(3, currency.getId());
            uploadStatement.setString(4, description);
            uploadStatement.setDate(5, (Date) java.sql.Date.valueOf(date));
            uploadStatement.setInt(6, freq.getId());
            uploadStatement.setInt(7, id);
            countUpdated = uploadStatement.executeUpdate();
        } catch (SQLException ex1) {
            throw new CostManagerException("The query was incorrect");
        } catch (NumberFormatException ex2) {
            throw new CostManagerException("Entered wrong data type");
        }

        close();

        return countUpdated > 0;
    }

    public HashMap<String, Double> getSumPerCategory(String start, String end) throws CostManagerException {
        init();
        HashMap<String, Double> categories = new HashMap<>();
        try {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT category, SUM(cost) AS total FROM Expense WHERE dueDate >= ? AND dueDate <= ? GROUP BY category");
            selectStatement.setDate(1, (Date) java.sql.Date.valueOf(start));
            selectStatement.setDate(2, (Date) java.sql.Date.valueOf(end));
            setRs(selectStatement.executeQuery());

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
        int numResult = 0;
        try {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT id FROM Users WHERE username = ? AND password = ?");
            selectStatement.setString(1, username);
            selectStatement.setString(2, password);
            numResult = selectStatement.executeUpdate();
        } catch (SQLException ex1) {
            throw new CostManagerException("The query was incorrect");
        } catch (NumberFormatException ex2) {
            throw new CostManagerException("Entered wrong data type");
        }

        close();
        return numResult > 0;
    }
}