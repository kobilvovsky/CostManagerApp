package il.ac.hit.java.costmanagerapp.view;

import javax.swing.*;
import java.awt.*;

public class LoginScreen  {

    private JFrame frame;
    private JPanel panelUpper;
    private JPanel panelMiddle;
    private JPanel panelLower;
    private JPanel contentPane;
    private JLabel lblUserName;
    private JLabel lblPassword;
    private JTextField tfUserName;
    private JPasswordField tfPassword;
    private JButton btnLogin;
    private JButton btnSignUp;

    public LoginScreen() {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setTitle("CostManagerApp - Login");
        frame.setLayout(new FlowLayout(FlowLayout.LEADING));

        lblUserName = new JLabel("User name: ");
        tfUserName = new JTextField(15);
        lblPassword = new JLabel("Password:  ");
        tfPassword = new JPasswordField(15);
        btnLogin = new JButton("  login  ");
        btnSignUp = new JButton("Sign up");

        panelUpper = new JPanel();
        panelUpper.add(lblUserName);
        panelUpper.add(tfUserName);

        panelMiddle = new JPanel();
        panelMiddle.add(lblPassword);
        panelMiddle.add(tfPassword);

        panelLower =new JPanel();
        panelLower.add(btnSignUp);
        panelLower.add(btnLogin);

        contentPane=new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(20,20,40,40));
        contentPane.add(panelUpper,BorderLayout.NORTH);
        contentPane.add(panelMiddle,BorderLayout.CENTER);
        contentPane.add(panelLower,BorderLayout.SOUTH);

        frame.add(contentPane);

        frame.pack();
        frame.setVisible(true);
    }
}
