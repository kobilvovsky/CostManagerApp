package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.view.viewutils.messageBox;
import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;

import javax.swing.*;

public class View implements IView{

    private IViewModel vm;
    private LoginScreen ui;

    @Override
    public void setViewModel(IViewModel vm) {
        this.vm=vm;
    }

    @Override
    public void showMessage(String strMessage, String strTitle) {


        if (SwingUtilities.isEventDispatchThread()) {
            messageBox.infoBox(strMessage, strTitle);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    messageBox.infoBox(strMessage, strTitle);
                }
            });
        }
    }


    public View(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.this.ui=new LoginScreen();
            }
        });
    }
}
