package il.ac.hit.java.costmanagerapp.viewmodel;

import il.ac.hit.java.costmanagerapp.model.DerbyDBModel;
import il.ac.hit.java.costmanagerapp.model.Expense;
import il.ac.hit.java.costmanagerapp.model.IModel;
import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;
import il.ac.hit.java.costmanagerapp.view.IView;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel implements IViewModel {

    private IModel model = DerbyDBModel.getInstance();

    private IView view;
    private ExecutorService pool;

    public ViewModel() throws ClassNotFoundException {
        pool = Executors.newFixedThreadPool(10);
    }

    @Override
    public void setModel(IModel model) {
        this.model=model;
    }

    @Override
    public void setView(IView view) {
        this.view=view;
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


}



