//package il.ac.hit.java.costmanagerapp.view;
//
//import il.ac.hit.java.costmanagerapp.model.DerbyDBModel;
//import il.ac.hit.java.costmanagerapp.model.IModel;
//import il.ac.hit.java.costmanagerapp.view.viewutils.RoundedBorder;
//import il.ac.hit.java.costmanagerapp.view.viewutils.messageBox;
//import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;
//import il.ac.hit.java.costmanagerapp.viewmodel.ViewModel;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//
//public class LoginScreen implements IView{
//
//    private JFrame frame;
//    private JPanel panelUpper;
//    private JPanel panelMiddle;
//    private JPanel panelLower;
//    private JPanel contentPane;
//    private JLabel lblUserName;
//    private JLabel lblPassword;
//    private JTextField tfUserName;
//    private JPasswordField tfPassword;
//    private JButton btnLogin;
//    private JButton btnSignUp;
//
//    public LoginScreen()
//    {
//        frame = new JFrame();
//
//        //Create frame settings
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(600,400);
//        frame.setTitle("CostManagerApp - Login");
//        frame.setLayout(new FlowLayout(FlowLayout.LEADING));
//
//        //declare all widgets
//        lblUserName = new JLabel("User name: ");
//        tfUserName = new JTextField(15);
//        lblPassword = new JLabel("Password:  ");
//        tfPassword = new JPasswordField(15);
//        btnLogin = new JButton("  login  ");
//        btnSignUp = new JButton("  Sign up  ");
//
//        //Login Button action listener
//        btnLogin.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String pass=String.valueOf(tfPassword.getPassword());
//                //checking that all fields are filled correctly
//                //int connection_result = model.isUserMatched(tfUserName.getText(), pass);
//
//                if(!tfUserName.getText().isEmpty() && !pass.isEmpty()) {
//                    if(vm.isUserMatched(tfUserName.getText(), pass)) {
//                        MainScreen mainScreen = new MainScreen();
//                        frame.dispose();
//                    } else
//                        System.out.println("WRONG PASSWORD U DUMBASS!!");
//                }
//            }
//        });
//
//        //Sign up listener
//        btnSignUp.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //Creating sign up screen
//                SignUpScreen signUp = new SignUpScreen();
//                frame.dispose();
//            }
//        });
//
//        //Rounding all buttons
//        btnLogin.setBorder(new RoundedBorder(3));
//        btnSignUp.setBorder(new RoundedBorder(3));
//
//        //Adding user name field&label to panel
//        panelUpper = new JPanel();
//        panelUpper.add(lblUserName);
//        panelUpper.add(tfUserName);
//
//        //Adding password field&label to panel
//        panelMiddle = new JPanel();
//        panelMiddle.add(lblPassword);
//        panelMiddle.add(tfPassword);
//
//        //Adding login&sigh up buttons to panel
//        panelLower =new JPanel();
//        panelLower.add(btnSignUp);
//        panelLower.add(btnLogin);
//
//        //layout manager
//        contentPane=new JPanel(new BorderLayout());
//        contentPane.setBorder(BorderFactory.createEmptyBorder(20,20,40,40));
//        contentPane.add(panelUpper,BorderLayout.NORTH);
//        contentPane.add(panelMiddle,BorderLayout.CENTER);
//        contentPane.add(panelLower,BorderLayout.SOUTH);
//
//        frame.add(contentPane);
//
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
//
//    @Override
//    public void setViewModel(IViewModel viewModel) {
//
//    }
//
//    @Override
//    public void showMessage(String strMessage,String strTitle) {
//        messageBox.infoBox("user name or password is incorrect","Login error");
//    }
//}
