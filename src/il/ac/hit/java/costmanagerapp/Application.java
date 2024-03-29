package il.ac.hit.java.costmanagerapp;
import il.ac.hit.java.costmanagerapp.model.DerbyDBModel;
import il.ac.hit.java.costmanagerapp.model.IModel;
import il.ac.hit.java.costmanagerapp.view.IView;
import il.ac.hit.java.costmanagerapp.view.View;
import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;
import il.ac.hit.java.costmanagerapp.viewmodel.ViewModel;
//import org.apache.derby.impl.tools.sysinfo.Main;

import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws ClassNotFoundException {
        IModel model = DerbyDBModel.getInstance();
        IView view = new View();
        IViewModel vm = new ViewModel();

        view.setViewModel(vm);
        vm.setModel(model);
        vm.setView(view);
    }
}