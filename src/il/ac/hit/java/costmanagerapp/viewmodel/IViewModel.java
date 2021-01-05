package il.ac.hit.java.costmanagerapp.viewmodel;

import il.ac.hit.java.costmanagerapp.model.Expense;
import il.ac.hit.java.costmanagerapp.model.IModel;
import il.ac.hit.java.costmanagerapp.view.IView;

import java.sql.SQLException;

public interface IViewModel {

    public void setModel(IModel model);
    public void setView(IView view);
    public void addExpense(Expense expense);
    public boolean isUserMatched(String username, String password);
}
