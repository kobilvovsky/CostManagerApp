package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;
import java.util.ArrayList;
import java.util.HashMap;

public interface IView {

    /**
     * Sets the view model
     * @param viewModel view model object
     */
    public void setViewModel(IViewModel viewModel);

    /**
     * Shows a message to the user
     * @param strMessage text string of the message
     * @param strTitle title string of the message
     */
    public void showMessage(String strMessage,String strTitle);

    /**
     * Loads view's table data
     * @param data expenses data from DB
     */
    public void callGetTable(String[][] data);

    /**
     * Prints expense data to screen
     * @param data expense's data from DB
     */
    public void printExpenseToEditScreen(ArrayList<String> data);

    /**
     * Prints categories and their sum to pie screen
     * @param data hashmap of category and total sum
     */
    public void printPieToScreen(HashMap<String, Double> data);
}