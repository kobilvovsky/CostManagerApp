package il.ac.hit.java.costmanagerapp;
import il.ac.hit.java.costmanagerapp.model.*;
import il.ac.hit.java.costmanagerapp.view.GeneratePieScreen;
import il.ac.hit.java.costmanagerapp.view.MainScreen;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
	    // write your code here
        DerbyDBModel db = new DerbyDBModel();

        MainScreen a = new MainScreen();
/*
        java.sql.Date date=new java.sql.Date(System.currentTimeMillis()+10);

        Expense a = new Expense(1, 3, "exp1", 100,
                new Category(1, "House"), Currency.EURO,
                "description", date, Frequency.ONE_TIME);*/
    }
}
