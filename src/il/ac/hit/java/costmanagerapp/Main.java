package il.ac.hit.java.costmanagerapp;
import il.ac.hit.java.costmanagerapp.model.*;
import il.ac.hit.java.costmanagerapp.view.AddExpenseScreen;
import il.ac.hit.java.costmanagerapp.view.MainScreen;


import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // write your code here
        DerbyDBModel db = new DerbyDBModel();
        //AddExpenseScreen test = new AddExpenseScreen();
        MainScreen test = new MainScreen();

        //AddExpenseScreen a = new AddExpenseScreen();
        MainScreen b = new MainScreen();

    }
}