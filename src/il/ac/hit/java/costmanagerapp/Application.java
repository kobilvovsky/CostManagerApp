package il.ac.hit.java.costmanagerapp;
import il.ac.hit.java.costmanagerapp.model.*;
import il.ac.hit.java.costmanagerapp.view.AddExpenseScreen;
import il.ac.hit.java.costmanagerapp.view.IView;
import il.ac.hit.java.costmanagerapp.view.MainScreen;
import il.ac.hit.java.costmanagerapp.view.View;
import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;
import il.ac.hit.java.costmanagerapp.viewmodel.ViewModel;
import org.apache.derby.impl.tools.sysinfo.Main;

import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws ClassNotFoundException
    {
        // write your code here



//        IView view = new View();
//        IViewModel vm = new ViewModel();
//
//        view.setViewModel(vm);
//        vm.setModel(db);
//        vm.setView(view);
//
//
//
//        new View();
        new MainScreen();
    }
}