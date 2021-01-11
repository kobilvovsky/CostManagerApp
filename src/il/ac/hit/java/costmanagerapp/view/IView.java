package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;

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
}
