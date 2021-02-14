/*
    Submitted by:
    Erez Mizrahi 316267087
    Lvovsky Yakov 315825380
    Netanel Tarnorudsky 315424689
 */

package il.ac.hit.java.costmanagerapp;
import il.ac.hit.java.costmanagerapp.model.*;
import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;
import il.ac.hit.java.costmanagerapp.view.IView;
import il.ac.hit.java.costmanagerapp.view.View;
import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;
import il.ac.hit.java.costmanagerapp.viewmodel.ViewModel;

public class Application {

    /**
     * Runs the main flow of the application
     */
  public static void main(String[] args) throws CostManagerException {
        IModel model = DerbyDBModel.getInstance();
        IView view = new View();
        IViewModel vm = new ViewModel();

        view.setViewModel(vm);
        vm.setModel(model);
        vm.setView(view);
  }
}