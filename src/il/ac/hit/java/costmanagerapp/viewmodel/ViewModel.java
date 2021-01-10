package il.ac.hit.java.costmanagerapp.viewmodel;

import il.ac.hit.java.costmanagerapp.model.DerbyDBModel;
import il.ac.hit.java.costmanagerapp.model.Expense;
import il.ac.hit.java.costmanagerapp.model.IModel;
import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;
import il.ac.hit.java.costmanagerapp.view.IView;
import il.ac.hit.java.costmanagerapp.view.View;

import javax.swing.*;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel implements IViewModel {

    private IModel model;
    private IView view;
    private ExecutorService pool;

    public ViewModel() { pool = Executors.newFixedThreadPool(10); }

    @Override
    public void setView(IView view) {
        this.view=view;
    }

    @Override
    public void setModel(IModel model) {
        this.model=model;
    }

    @Override
    public void addExpense(Expense expense) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addExpense(expense);
                } catch (SQLException | ClassNotFoundException throwable) { //needs to be replaced with own exception!
                    throwable.printStackTrace();
                    //Show message
                }
            }
        });
    }

    @Override
    public boolean isUserMatched(String username, String password) {
        Boolean r[] = {false};
        pool.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                r[0] = model.isUserMatched(username, password);
                System.out.println(r[0]);
                return r[0];
            }

//            @Override
//            public void run() {
//                try {
//                    r[0] = model.isUserMatched(username, password);
////                    if(r[0]) {
////                        SwingUtilities.invokeLater(
////                            View.MainScreen mainScreen = new View.MainScreen();
////                        frame.dispose();
////                        );
////                    }
//                } catch (SQLException | ClassNotFoundException throwable) { //needs to be replaced with own exception!
//                    throwable.printStackTrace();
//                    //Show message
//                }
//            }
        });
        System.out.println(r[0]);
        return r[0];
    }

    @Override
    public String[][] getUserExpenses() throws SQLException, ClassNotFoundException {
        return model.getUserExpenses();
    }
}



