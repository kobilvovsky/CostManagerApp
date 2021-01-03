package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.view.viewutils.RoundedBorder;
import il.ac.hit.java.costmanagerapp.view.viewutils.messageBox;
import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        //Create frame settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,200);
        frame.setTitle("CostManagerApp - Sign up");

        //declare all widgets
        lblUserName = new JLabel("User name: ");
        tfUserName = new JTextField(15);
        lblPassword = new JLabel("Password:  ");
        tfPassword = new JPasswordField(15);
        btnSignUp = new JButton("Sign up");

        //Sign up listener
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass=String.valueOf(tfPassword.getPassword());
                if(!tfUserName.getText().isEmpty() && !pass.isEmpty()) {
                    MainScreen mainScreen = new MainScreen();
                    frame.dispose();
                }
                else{
                    messageBox.infoBox("user name or password is incorrect","Login error");
                }
            }
        });

        //Rounding button
        btnSignUp.setBorder(new RoundedBorder(3));

        //Adding user name field&label to panel
        panelUpper = new JPanel();
        panelUpper.add(lblUserName);
        panelUpper.add(tfUserName);

        //Adding password field&label to panel
        panelMiddle = new JPanel();
        panelMiddle.add(lblPassword);
        panelMiddle.add(tfPassword);

        //Adding sigh up button to panel
        panelLower =new JPanel();
        panelLower.add(btnSignUp);

        //layout manager
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
    public void showMessage(String strMessage, String strTitle) {

    }
}