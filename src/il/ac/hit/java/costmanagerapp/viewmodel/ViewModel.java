package il.ac.hit.java.costmanagerapp.viewmodel;

import il.ac.hit.java.costmanagerapp.model.Expense;
import il.ac.hit.java.costmanagerapp.model.IModel;
import il.ac.hit.java.costmanagerapp.view.IView;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel implements IViewModel {

    private IModel model;
    private IView view;
    private ExecutorService pool;

    public ViewModel() {
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
        try {
            model.addExpense(expense);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

    //
//    @Override
//    public String[][] getUserExpenses() {
//
//
//
//    }
//
//    @Override
//    public void addCostItem(CostItem item) {
//        pool.submit(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    model.addCostItem(item);
//                    view.showMessage("cost item was added successfully");
//                    CostItem[] items = model.getCostItems();
//                    view.showItems(items);
//                } catch(CostManagerException e) {
//                    view.showMessage(e.getMessage());
//                }
//            }
//        });
//
//    }

