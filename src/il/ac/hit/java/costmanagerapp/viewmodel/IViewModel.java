package il.ac.hit.java.costmanagerapp.viewmodel;

import il.ac.hit.java.costmanagerapp.model.Expense;
import il.ac.hit.java.costmanagerapp.model.IModel;
import il.ac.hit.java.costmanagerapp.view.IView;

public interface IViewModel {

    public void setModel(IModel model);
    public void setView(IView view);
    public void addExpense(Expense expense);
}
