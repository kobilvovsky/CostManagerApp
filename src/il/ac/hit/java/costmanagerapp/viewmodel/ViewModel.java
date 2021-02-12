package il.ac.hit.java.costmanagerapp.viewmodel;

import il.ac.hit.java.costmanagerapp.model.Expense;
import il.ac.hit.java.costmanagerapp.model.IModel;
import il.ac.hit.java.costmanagerapp.model.User;
import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;
import il.ac.hit.java.costmanagerapp.view.IView;

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
        this.view=view;
    }

    @Override
    public void setModel(IModel model) {
        this.model=model;
    }

    @Override
    public void addExpense(Expense expense) throws CostManagerException{
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addExpense(expense);
                    view.showMessage("Expense was added Successfully","Success!");
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
        model.addUser(user);
                    view.showMessage("User was added Successfully","Success!");
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
    public void getUserExpenses() throws CostManagerException {
        String [][] data=model.getUserExpenses();
        view.callGetTable(data);
    }
}



