package il.ac.hit.java.costmanagerapp.viewmodel;

import il.ac.hit.java.costmanagerapp.model.Expense;
import il.ac.hit.java.costmanagerapp.model.IModel;
import il.ac.hit.java.costmanagerapp.model.User;
import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;
import il.ac.hit.java.costmanagerapp.view.IView;

import java.sql.SQLException;

public interface IViewModel {

    public void setModel(IModel model);
    public void setView(IView view);
    public void addUser(User user) throws CostManagerException;
    public void addExpense(Expense expense) throws CostManagerException;
    public boolean isUserMatched(String username, String password);
    public String[][] getUserExpenses() throws CostManagerException;
}
