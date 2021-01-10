package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.model.*;
import il.ac.hit.java.costmanagerapp.view.viewutils.HintTextField;
import il.ac.hit.java.costmanagerapp.view.viewutils.RoundedBorder;
import il.ac.hit.java.costmanagerapp.view.viewutils.messageBox;
import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;
import il.ac.hit.java.costmanagerapp.viewmodel.ViewModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class View implements IView {//, Runnable {

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
//        Thread t = new Thread();
//        t.start();
    }

//    @Override
//    public void run() {
//        View.this.ui = new LoginScreen();
//    }

    public class AddExpenseScreen {
        private JPanel mainPanel;

        private GroupLayout layout;
        private GroupLayout.SequentialGroup vGroup;

        private JLabel lbHeader;
        private JLabel lbExpenseAmount;
        private JLabel lbExpenseCurrency;
        private JLabel lbExpenseDescription;
        private JLabel lbExpenseCategory;
        private JLabel lbExpenseFrequency;
        private JLabel lbExpenseDate;

        private JTextField tfExpenseAmount;
        private JTextField tfExpenseDescription;
        private JTextField tfExpenseDate;

        private ButtonGroup bgCurrency;
        private Box bxCurrency;
        private ButtonGroup bgFrequency;
        private Box bxFrequency;

        private JRadioButton rbUSD;
        private JRadioButton rbEURO;
        private JRadioButton rbYEN;
        private JRadioButton rbPOUND;
        private JRadioButton rbNIS;

        private JRadioButton rbOneTime;
        private JRadioButton rbMonthly;
        private JRadioButton rbYearly;

        private JComboBox cbCategory;

        private Box bxCategory;

        private JButton btnAddExpense;
        private JButton btnAddCategory;

        private ArrayList<String> cats;
        private Vector defaultCategory;
        private DefaultComboBoxModel model;

        public AddExpenseScreen() {
            //creating the main panel
            mainPanel = new JPanel();
            //creating the panel layout
            layout = new GroupLayout(mainPanel);
            //creating the main ui components
            lbHeader = new JLabel("Add Expense");
            lbHeader.setFont(new Font("San Francisco", Font.BOLD, 20));

            lbExpenseAmount = new JLabel("Enter Expense Amount: ");
            tfExpenseAmount = new JTextField(15);
            tfExpenseAmount.setMaximumSize(new Dimension(200, 25));


            lbExpenseCurrency = new JLabel("Select Expense Currency");
            bgCurrency = new ButtonGroup();
            bxCurrency = Box.createHorizontalBox();
            rbEURO = new JRadioButton(Currency.EURO.name());
            rbEURO.setActionCommand("EURO");
            rbUSD = new JRadioButton(Currency.USD.name());
            rbUSD.setActionCommand("USD");
            rbYEN = new JRadioButton(Currency.YEN.name());
            rbYEN.setActionCommand("YEN");
            rbPOUND = new JRadioButton(Currency.POUND.name());
            rbPOUND.setActionCommand("POUND");
            rbNIS = new JRadioButton(Currency.NIS.name());
            rbNIS.setActionCommand("NIS");

            bgCurrency.add(rbEURO);
            bgCurrency.add(rbUSD);
            bgCurrency.add(rbNIS);
            bgCurrency.add(rbPOUND);
            bgCurrency.add(rbYEN);

            bxCurrency.add(rbEURO);
            bxCurrency.add(rbUSD);
            bxCurrency.add(rbNIS);
            bxCurrency.add(rbPOUND);
            bxCurrency.add(rbYEN);

            lbExpenseDescription = new JLabel("Enter Expense Description: ");
            tfExpenseDescription = new JTextField();
            tfExpenseDescription.setMaximumSize(new Dimension(200, 25));

            lbExpenseCategory = new JLabel("Select Expense Category: ");
            defaultCategory = new Vector();
            Collections.addAll(defaultCategory, Category.INITIAL_CATEGORIES);
            model = new DefaultComboBoxModel(defaultCategory);
            cbCategory = new JComboBox(model);
            cbCategory.setMaximumSize(new Dimension(200, 25));
            btnAddCategory = new JButton("Add Category");
            btnAddCategory.setBorder(new RoundedBorder(5));

            bxCategory = Box.createHorizontalBox();

            bxCategory.add(cbCategory);
            bxCategory.add(Box.createRigidArea(new Dimension(5, 0)));
            bxCategory.add(btnAddCategory);


            lbExpenseFrequency = new JLabel("Select Expense Frequency");
            bgFrequency = new ButtonGroup();
            bxFrequency = Box.createHorizontalBox();

            rbOneTime = new JRadioButton(Frequency.ONE_TIME.name());
            rbOneTime.setActionCommand("ONE_TIME");
            rbMonthly = new JRadioButton(Frequency.MONTHLY.name());
            rbMonthly.setActionCommand("MONTHLY");
            rbYearly = new JRadioButton(Frequency.YEARLY.name());
            rbYearly.setActionCommand("YEARLY");

            bgFrequency.add(rbOneTime);
            bgFrequency.add(rbMonthly);
            bgFrequency.add(rbYearly);

            bxFrequency.add(rbOneTime);
            bxFrequency.add(rbMonthly);
            bxFrequency.add(rbYearly);

            lbExpenseDate = new JLabel("Enter Expense Date: ");
            tfExpenseDate = new HintTextField("yyyy-mm-dd");
            tfExpenseDate.setMaximumSize(new Dimension(200, 25));


            btnAddExpense = new JButton("Add Expense");
            btnAddExpense.setBorder(new RoundedBorder(5));
            start();

        }

        public void start()
        {
            btnAddCategory.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String result = (String) JOptionPane.showInputDialog(
                            mainPanel,
                            "Add your own category",
                            "Add New Category",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "Enter your category"
                    );
                    if ((result != null) && (result.length() > 0))
                    {
                        model.addElement(result);
                        cbCategory.setSelectedItem(result);
                    }
                }
            });

            mainPanel.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            //layout.linkSize(SwingConstants.HORIZONTAL, btnAddCategory, btnAddExpense);
            GroupLayout.ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.CENTER); // Will align the labels the way you wanted

            hGroup.addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                            .addComponent(lbExpenseAmount)
                            .addComponent(lbExpenseCurrency)
                            .addComponent(lbExpenseDescription)
                            .addComponent(lbExpenseCategory)
                            .addComponent(lbExpenseFrequency)
                            .addComponent(lbExpenseDate))
                    .addGroup(layout.createParallelGroup()
                            .addComponent(tfExpenseAmount)
                            .addComponent(bxCurrency)
                            .addComponent(tfExpenseDescription)
                            .addComponent(bxCategory)
                            .addComponent(bxFrequency)
                            .addComponent(tfExpenseDate)));
            hGroup.addComponent(btnAddExpense);

            layout.setHorizontalGroup(hGroup);

            // Vertical
            GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(lbExpenseAmount)
                    .addComponent(tfExpenseAmount));

            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(lbExpenseCurrency)
                    .addComponent(bxCurrency));

            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(lbExpenseDescription)
                    .addComponent(tfExpenseDescription));

            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(lbExpenseCategory)
                    .addComponent(bxCategory));

            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(lbExpenseFrequency)
                    .addComponent(bxFrequency));

            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(lbExpenseDate)
                    .addComponent(tfExpenseDate));

            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(btnAddExpense));

            layout.setVerticalGroup(vGroup);

            btnAddExpense.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        float amount = Float.parseFloat(tfExpenseAmount.getText());
                        String description = tfExpenseDescription.getText();

                        String categoryStr = String.valueOf(cbCategory.getSelectedItem());
                        Category category = new Category(categoryStr);
                        String frequencyStr = bgFrequency.getSelection().getActionCommand();
                        Frequency frequency = null;
                        switch (frequencyStr)
                        {
                            case "ONE_TIME":
                                frequency = Frequency.ONE_TIME;
                                break;
                            case "MONTHLY":
                                frequency = Frequency.MONTHLY;
                                break;
                            default:
                                frequency = Frequency.YEARLY;
                        }
                        String currencyStr = bgCurrency.getSelection().getActionCommand();
                        Currency currency = null;
                        switch (currencyStr)
                        {
                            case "USD":
                                currency = Currency.USD;
                                break;
                            case "EURO":
                                currency = Currency.EURO;
                                break;
                            case "YEN":
                                currency = Currency.YEN;
                                break;
                            case "POUND":
                                currency = Currency.POUND;
                                break;
                            default:
                                currency = Currency.NIS;
                        }
                        String date = tfExpenseDate.getText();

                        //Need to send Expense parameter after confirming with Erez and updating the branch.
                        Expense expense = new Expense(0, amount, category, currency, description, date, frequency);
                        try {
                            vm.addExpense(expense);
                        } catch (SQLException throwable) {
                            throwable.printStackTrace();
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println(ex);
                        //adding the relevant exception..
                    }
                }
            });

        }

        public JPanel getPanel() {
            return mainPanel;
        }
    }

    public class GeneratePieScreen {

        private JPanel panel;
        private JLabel startDateLabel;
        private JTextField startDateField;
        private JLabel endDateLabel;
        private JTextField endDateField;
        private JButton Generate;

        public GeneratePieScreen() {
            panel = new JPanel();
            start();
        }

        private void start() {
            panel.setLayout(null);
            panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

            startDateLabel = new JLabel("Start Date: ");
            startDateLabel.setBounds(10,20,80,25);
            panel.add(startDateLabel);

            startDateField = new HintTextField("dd/mm/yyyy");
            startDateField.setBounds(100,20,165,25);
            panel.add(startDateField);

            endDateLabel = new JLabel("End Date: ");
            endDateLabel.setBounds(10,50,80,25);
            panel.add(endDateLabel);

            endDateField = new HintTextField("dd/mm/yyyy");
            endDateField.setBounds(100,50,165,25);
            panel.add(endDateField);


            Generate = new JButton("Generate");
            Generate.setBounds(95, 100, 100, 25);
            panel.add(Generate);
        }

        public JPanel getPanel() {
            return panel;
        }
    }

    public class LoginScreen{
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

            //Create frame settings
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600,400);
            frame.setTitle("CostManagerApp - Login");
            frame.setLayout(new FlowLayout(FlowLayout.LEADING));

            //declare all widgets
            lblUserName = new JLabel("User name: ");
            tfUserName = new JTextField(15);
            lblPassword = new JLabel("Password:  ");
            tfPassword = new JPasswordField(15);
            btnLogin = new JButton("  login  ");
            btnSignUp = new JButton("  Sign up  ");

            //Login Button action listener
            btnLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String pass=String.valueOf(tfPassword.getPassword());
                    //checking that all fields are filled correctly
                    if(!tfUserName.getText().isEmpty() && !pass.isEmpty()) {
                        MainScreen mainScreen = new MainScreen();
                        frame.dispose();
//                        boolean r = vm.isUserMatched(tfUserName.getText(), pass);
//                        System.out.println(r);
//                        if(r) {
//                            MainScreen mainScreen = new MainScreen();
//                            frame.dispose();
//                        } else
//                            System.out.println("WRONG PASSWORD U DUMBASS!!");
                    }
                }
            });

            //Sign up listener
            btnSignUp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Creating sign up screen
                    SignUpScreen signUp = new SignUpScreen();
                    frame.dispose();
                }
            });

            //Rounding all buttons
            btnLogin.setBorder(new RoundedBorder(3));
            btnSignUp.setBorder(new RoundedBorder(3));

            //Adding user name field&label to panel
            panelUpper = new JPanel();
            panelUpper.add(lblUserName);
            panelUpper.add(tfUserName);

            //Adding password field&label to panel
            panelMiddle = new JPanel();
            panelMiddle.add(lblPassword);
            panelMiddle.add(tfPassword);

            //Adding login&sigh up buttons to panel
            panelLower =new JPanel();
            panelLower.add(btnSignUp);
            panelLower.add(btnLogin);

            //layout manager
            contentPane=new JPanel(new BorderLayout());
            contentPane.setBorder(BorderFactory.createEmptyBorder(20,20,40,40));
            contentPane.add(panelUpper,BorderLayout.NORTH);
            contentPane.add(panelMiddle,BorderLayout.CENTER);
            contentPane.add(panelLower,BorderLayout.SOUTH);

            frame.add(contentPane);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }

    public class MainScreen {
        private JFrame frame;
        private JPanel leftPanel;
        private JPanel leftGridPanel;
        private JPanel mainPanel;

        private JButton editBtn;
        private JButton addBtn;
        private JButton viewBtn;
        private JButton reportBtn;
        private JButton logoutBtn;
        private Container container;

        public MainScreen() {
            frame = new JFrame();
            leftPanel = new JPanel();
            leftGridPanel = new JPanel();
            mainPanel = new JPanel();
            container = frame.getContentPane();

            editBtn = new JButton("Edit Expense");
            reportBtn = new JButton("Generate Report");
            viewBtn = new JButton("View Expenses");
            addBtn = new JButton("Add Expense");
            logoutBtn = new JButton("Logout");

            viewBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ViewScreen viewScreen = null;
                    try {
                        viewScreen = new ViewScreen();
                        resetView(viewScreen.getPanel());
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });

            addBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    View.AddExpenseScreen addScreen = new View.AddExpenseScreen();
                    resetView(addScreen.getPanel());
                }
            });

            reportBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    View.GeneratePieScreen pieScreen = new View.GeneratePieScreen();
                    resetView(pieScreen.getPanel());
                }
            });

            logoutBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    View.LoginScreen loginScreen = new View.LoginScreen();
                    frame.dispose(); // close window
                }
            });

            start();
        }

        public void resetView(JPanel newPanel) {
            container.remove(mainPanel);
            container.repaint();
            container.revalidate();
            mainPanel = newPanel;
            //container.add(newPanel);
            container.add(newPanel,BorderLayout.CENTER);
            container.repaint();
            container.revalidate();
        }

        public void start() {
            container.setLayout(new BorderLayout());
            leftPanel.setBorder(new LineBorder(Color.BLACK, 2));
            leftPanel.setLayout(new FlowLayout(4, 4,4));
            leftGridPanel.setLayout(new GridLayout(5, 1, 5, 5));

            leftGridPanel.add(viewBtn);
            leftGridPanel.add(addBtn);
            leftGridPanel.add(editBtn);
            leftGridPanel.add(reportBtn);
            leftGridPanel.add(logoutBtn);
            leftPanel.add(leftGridPanel);

            mainPanel.setBorder(new LineBorder(Color.BLACK, 2));
            container.add(mainPanel);
            container.add(leftPanel, BorderLayout.WEST);

            frame.addWindowListener(new WindowAdapter() {
                /**
                 * Invoked when a window is in the process of being closed.
                 * The close operation can be overridden at this point.
                 *
                 * @param e
                 */
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            frame.setTitle("CostManagerApp - Menu");
            frame.setSize(680, 300);
            frame.setLocationRelativeTo(null);
            //frame.setLocation(500, 500);
            frame.setVisible(true);
        }
    }

    public class SignUpScreen {
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

        private IViewModel vm;

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
                        Username username = new Username(tfUserName.getText());
                        Password password = new Password(tfPassword.getPassword().toString());
                        User user= new User(username,password);
                        vm.addUser(user);
                        //check user was actually saved (receive some sort of boolean back)
                        View.MainScreen mainScreen = new View.MainScreen();
                        frame.dispose();
                    }
                    else{
                        showMessage("user name or password is incorrect","Login error");
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
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }

    public class ViewScreen {
        private JPanel panel;
        private JTable table;
        private JScrollPane sp;

        public ViewScreen() throws SQLException, ClassNotFoundException {
            panel = new JPanel(new BorderLayout());
            getTable();
        }

        public void getTable() throws SQLException, ClassNotFoundException {
            String data[][] = vm.getUserExpenses();
            String column[] = {"Id", "Cost", "Category", "Currency", "Description", "CreatedAt", "dueDate", "Frequency"};
            table = new JTable(data, column);
            table.setFillsViewportHeight(true);
            sp = new JScrollPane(table);
            panel.add(sp, BorderLayout.CENTER);
        }

        public JPanel getPanel() {
            return panel;
        }
    }
}
