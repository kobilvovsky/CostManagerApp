package il.ac.hit.java.costmanagerapp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.font.LayoutPath;
import java.util.concurrent.Flow;

public class LoginScreen  {

    private JFrame frame;
    private JPanel upperPanel;
    private JPanel middlePanel;
    private JPanel lowerPanel;
    private JPanel contentPane;
    private JLabel userNameLabel;
    private JTextField userText;
    private JLabel passwordLabel;
    private JPasswordField passwordText;
    private JButton loginBtn;
    private JButton signUpBtn;

    public LoginScreen() {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setTitle("CostManagerApp - Login");
        frame.setLayout(new FlowLayout(FlowLayout.LEADING));

        userNameLabel = new JLabel("User name: ");
        userText = new JTextField(15);
        passwordLabel = new JLabel("Password:  ");
        passwordText = new JPasswordField(15);
        loginBtn = new JButton("  login  ");
        signUpBtn = new JButton("Sign up");

        upperPanel = new JPanel();
        upperPanel.add(userNameLabel);
        upperPanel.add(userText);

        middlePanel = new JPanel();
        middlePanel.add(passwordLabel);
        middlePanel.add(passwordText);

        lowerPanel=new JPanel();
        lowerPanel.add(signUpBtn);
        lowerPanel.add(loginBtn);

        contentPane=new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(20,20,40,40));
        contentPane.add(upperPanel,BorderLayout.NORTH);
        contentPane.add(middlePanel,BorderLayout.CENTER);
        contentPane.add(lowerPanel,BorderLayout.SOUTH);

        frame.add(contentPane);

        frame.pack();
        frame.setVisible(true);
    }
}
