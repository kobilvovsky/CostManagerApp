package il.ac.hit.java.costmanagerapp.viewmodel;

import il.ac.hit.java.costmanagerapp.model.*;
import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;
import il.ac.hit.java.costmanagerapp.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel implements IViewModel {

    private IModel model;
    private IView view;
    private ExecutorService pool;

    /**
     * Constructor of view model
     */
    public ViewModel() {
        pool = Executors.newFixedThreadPool(10);
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    @Override
    public void addExpense(Expense expense) throws CostManagerException{
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    if(model.addExpense(expense))
                        view.showMessage("Expense was added successfully","Success!");
                    else
                        view.showMessage("Expense couldn't be added","Error");
                } catch (CostManagerException e) {
                    System.out.println(e.getMessage());
                    view.showMessage("Error in adding expense", "Error");
                }
            }
        });
    }

    @Override
    public void addUser(User user) throws CostManagerException{
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    if(model.addUser(user))
                        view.showMessage("User was added successfully","Success!");
                    else
                        view.showMessage("Couldn't add a user","Error");
                } catch (CostManagerException e) {
                    System.out.println(e.getMessage());
                    view.showMessage("Error in adding user", "Error");
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
        });
        System.out.println(r[0]);
        return r[0];
    }

    @Override
    public void getUserExpenses() throws CostManagerException {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    String[][] data = model.getUserExpenses();
                    view.callGetTable(data);
                } catch (CostManagerException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    @Override
    public void getSumPerCategory(String start, String end) throws CostManagerException {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    HashMap<String, Double> data = model.getSumPerCategory(start, end);
                    view.printPieToScreen(data);
                } catch (CostManagerException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    @Override
    public void deleteExpense(int id) throws CostManagerException {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    if(model.deleteExpense(id))
                        view.showMessage("Expense was deleted successfully","Success!");
                    else
                        view.showMessage("Expense couldn't be deleted","Error");
                } catch (CostManagerException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    @Override
    public void getExpense(int id) throws CostManagerException {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<String> data = model.getExpense(id);
                    view.printExpenseToEditScreen(data);
                } catch (CostManagerException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    @Override
    public void updateExpense(int id, double amount, Category cat, Currency currency, String description, String date, Frequency freq) throws CostManagerException {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    if(model.updateExpense(id, amount, cat, currency, description, date, freq))
                        view.showMessage("Expense was updated successfully","Success!");
                    else
                        view.showMessage("Expense couldn't be updated","Error");
                } catch (CostManagerException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}