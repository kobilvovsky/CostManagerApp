package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;

public interface IView {

    public void setViewModel(IViewModel viewModel);
    public void showMessage();
    public void showItem();
}
