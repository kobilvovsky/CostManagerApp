package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;

import javax.swing.*;
import java.awt.*;

public class SignUpScreen implements IView {

    private JFrame frame;
    private JPanel panelMain;
    private JPanel panelUpper;
    private JPanel panelMiddle;
    private JPanel panelLower;
    private JLabel lblUserName;
    private JLabel lblPassword;
    private JPasswordField tfPassword;
    private JTextField tfUserName;
    private JButton btnSignUp;

    public SignUpScreen() {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,200);
        frame.setTitle("CostManagerApp - Sign up");

        lblUserName = new JLabel("User name: ");
        tfUserName = new JTextField(15);
        lblPassword = new JLabel("Password:  ");
        tfPassword = new JPasswordField(15);
        btnSignUp = new JButton("Sign up");

        panelUpper = new JPanel();
        panelUpper.add(lblUserName);
        panelUpper.add(tfUserName);

        panelMiddle = new JPanel();
        panelMiddle.add(lblPassword);
        panelMiddle.add(tfPassword);

        panelLower =new JPanel();
        panelLower.add(btnSignUp);

        panelMain = new JPanel(new BorderLayout());
        panelMain.setBorder(BorderFactory.createEmptyBorder(20,20,40,40));
        panelMain.add(panelUpper,BorderLayout.NORTH);
        panelMain.add(panelMiddle,BorderLayout.CENTER);
        panelMain.add(panelLower,BorderLayout.SOUTH);

        frame.add(panelMain);

        frame.pack();
        frame.setVisible(true);


    }

    @Override
    public void setViewModel(IViewModel viewModel) {
    }

    @Override
    public void showMessage() {

    }
}