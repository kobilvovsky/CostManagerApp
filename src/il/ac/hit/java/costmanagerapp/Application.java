package il.ac.hit.java.costmanagerapp;
import il.ac.hit.java.costmanagerapp.model.DerbyDBModel;
import il.ac.hit.java.costmanagerapp.model.IModel;
import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;
import il.ac.hit.java.costmanagerapp.view.IView;
import il.ac.hit.java.costmanagerapp.view.View;
import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;
import il.ac.hit.java.costmanagerapp.viewmodel.ViewModel;

public class Application {

<<<<<<< HEAD
    public static void main(String[] args) throws  CostManagerException {
=======
    /**
     * Runs the main function of the project
     * @throws ClassNotFoundException if DerbyDB was not initiated properly
     */
    public static void main(String[] args) throws ClassNotFoundException {
>>>>>>> 662700b1250f6a7e0c60b873e8a579c483d2ee9f
        IModel model = DerbyDBModel.getInstance();
        IView view = new View();
        IViewModel vm = new ViewModel();

        view.setViewModel(vm);
        vm.setModel(model);
        vm.setView(view);
    }
}