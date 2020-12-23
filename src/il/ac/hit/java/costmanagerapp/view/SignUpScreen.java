package il.ac.hit.java.costmanagerapp.view;

import javax.swing.*;
import java.awt.*;

public class SignUpScreen {

    private JFrame frame;
    private JPanel panel;
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

        panel = new JPanel();
        frame.add(panel, BorderLayout.CENTER);
        placeComponents(panel);

        frame.setVisible(true);


    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


//        Creating Username Label
        userNameLabel = new JLabel("User name");
        userNameLabel.setBounds(10,20,80,25);
        panel.add(userNameLabel);

        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        signUpBtn = new JButton("Sign up");
        signUpBtn.setBounds(135, 100, 80, 25);
        panel.add(signUpBtn);
    }
}