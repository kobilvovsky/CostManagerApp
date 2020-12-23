package il.ac.hit.java.costmanagerapp;
import il.ac.hit.java.costmanagerapp.model.*;
<<<<<<< Updated upstream
import il.ac.hit.java.costmanagerapp.view.GeneratePieScreen;
import il.ac.hit.java.costmanagerapp.view.MainScreen;
=======
import il.ac.hit.java.costmanagerapp.view.AddExpenseScreen;
>>>>>>> Stashed changes

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
	    // write your code here
        DerbyDBModel db = new DerbyDBModel();

<<<<<<< Updated upstream
        MainScreen a = new MainScreen();
=======
        //AddExpenseScreen newScreen = new AddExpenseScreen();
>>>>>>> Stashed changes
/*
        java.sql.Date date=new java.sql.Date(System.currentTimeMillis()+10);

        Expense a = new Expense(1, 3, "exp1", 100,
                new Category(1, "House"), Currency.EURO,
                "description", date, Frequency.ONE_TIME);*/
    }
}
