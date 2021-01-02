package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;

import javax.swing.*;
import java.awt.*;

public class SignUpScreen implements IView {

    private JFrame frame;
    private JPanel contentPane;
    private JPanel upperPane;
    private JPanel middlePane;
    private JPanel lowerPane;
    private static JLabel userNameLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton signUpBtn;

    public SignUpScreen() {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,200);
        frame.setTitle("CostManagerApp - Sign up");

        userNameLabel = new JLabel("User name: ");
        userText = new JTextField(15);
        passwordLabel = new JLabel("Password:  ");
        passwordText = new JPasswordField(15);
        signUpBtn = new JButton("Sign up");

        upperPane = new JPanel();
        upperPane.add(userNameLabel);
        upperPane.add(userText);

        middlePane = new JPanel();
        middlePane.add(passwordLabel);
        middlePane.add(passwordText);

        lowerPane =new JPanel();
        lowerPane.add(signUpBtn);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(20,20,40,40));
        contentPane.add(upperPane,BorderLayout.NORTH);
        contentPane.add(middlePane,BorderLayout.CENTER);
        contentPane.add(lowerPane,BorderLayout.SOUTH);

        frame.add(contentPane);

        frame.pack();
        frame.setVisible(true);


    }

    @Override
    public void setViewModel(IViewModel viewModel) {
    }

    @Override
    public void showMessage() {

    }

    @Override
    public void showItem() {

    }
}