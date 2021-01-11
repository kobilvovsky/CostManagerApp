package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.model.*;
import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;
import il.ac.hit.java.costmanagerapp.view.viewutils.HintTextField;
import il.ac.hit.java.costmanagerapp.view.viewutils.RoundedBorder;
import il.ac.hit.java.costmanagerapp.view.viewutils.MessageBox;
import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class View implements IView {

    private IViewModel vm;
    private LoginScreen ui;

    @Override
    public void setViewModel(IViewModel vm) {
        this.vm = vm;
    }

    @Override
    public void showMessage(String strMessage, String strTitle) {
        if (SwingUtilities.isEventDispatchThread()) {
            MessageBox.infoBox(strMessage, strTitle);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MessageBox.infoBox(strMessage, strTitle);
                }
            });
        }
    }

    /**
     * View constructor
     */
    public View() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.this.ui=new LoginScreen();
            }
        });
    }

    public class AddExpenseScreen {
        private JPanel mainPanel;

        private GroupLayout layout;
        private GroupLayout.SequentialGroup vGroup;

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

        /**
         * Add expense screen constructor
         */
        public AddExpenseScreen() {
            mainPanel = new JPanel();
            layout = new GroupLayout(mainPanel);

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

            mainPanel.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            GroupLayout.ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.CENTER);

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

            createListeners();
        }

        /**
         * Creates all listeners of the screen
         */
        public void createListeners() {
            btnAddCategory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String result = (String) JOptionPane.showInputDialog(
                            mainPanel,
                            "Add your own category",
                            "Add New Category",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "Enter your category"
                    );
                    if ((result != null) && (result.length() > 0)) {
                        model.addElement(result);
                        cbCategory.setSelectedItem(result);
                    }
                }
            });

            btnAddExpense.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        double amount = Double.parseDouble(tfExpenseAmount.getText());
                        System.out.println(amount);
                        String description = tfExpenseDescription.getText();

                        String categoryStr = String.valueOf(cbCategory.getSelectedItem());
                        Category category = new Category(categoryStr);
                        String frequencyStr = bgFrequency.getSelection().getActionCommand();
                        Frequency frequency = null;
                        switch (frequencyStr) {
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

                        switch (currencyStr) {
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
                        } catch (CostManagerException costManagerException) {
                            costManagerException.printStackTrace();
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println(ex);
                        //adding the relevant exception..
                    }
                }
            });
        }

        /**
         * Gets screen's panel
         * @return JPanel object
         */
        public JPanel getPanel() {
            return mainPanel;
        }
    }

    public class EditExpenseScreen {
        private JPanel mainPanel;

        private GroupLayout layout;
        private GroupLayout.SequentialGroup vGroup;

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

        /**
         * Edit expense screen constructor
         */
        public EditExpenseScreen() {
            mainPanel = new JPanel();
            layout = new GroupLayout(mainPanel);

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

            btnAddExpense = new JButton("Update Expense");
            btnAddExpense.setBorder(new RoundedBorder(5));

            mainPanel.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            GroupLayout.ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.CENTER);

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
            createListeners();
        }

        public void createListeners() {
            btnAddCategory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String result = (String)JOptionPane.showInputDialog(
                            mainPanel,
                            "Add your own category",
                            "Add New Category",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "Enter your category"
                    );
                    if ((result != null) && (result.length() > 0)) {
                        model.addElement(result);
                        cbCategory.setSelectedItem(result);
                    }
                }
            });

//        btnAddExpense.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    float amount = Float.parseFloat(tfExpenseAmount.getText());
//                    String description = tfExpenseDescription.getText();
//
//                    String categoryStr = String.valueOf(cbCategory.getSelectedItem());
//                    Category category = new Category(categoryStr);
//                    String frequencyStr = bgFrequency.getSelection().getActionCommand();
//                    Frequency frequency = null;
//                    switch (frequencyStr) {
//                        case "ONE_TIME":
//                            frequency = Frequency.ONE_TIME;
//                            break;
//                        case "MONTHLY":
//                            frequency = Frequency.MONTHLY;
//                            break;
//                        default:
//                            frequency = Frequency.YEARLY;
//                    }
//                    String currencyStr = bgCurrency.getSelection().getActionCommand();
//                    Currency currency = null;
//                    switch (currencyStr) {
//                        case "USD":
//                            currency = Currency.USD;
//                            break;
//                        case "EURO":
//                            currency = Currency.EURO;
//                            break;
//                        case "YEN":
//                            currency = Currency.YEN;
//                            break;
//                        case "POUND":
//                            currency = Currency.POUND;
//                            break;
//                        default:
//                            currency = Currency.NIS;
//                    }
//                    String date = tfExpenseDate.getText();
//
//                    //Need to send Expense parameter after confirming with Erez and updating the branch.
//                    Expense expense = new Expense(0,amount,category,currency,description,date,frequency);
//                    try {
//                        vm.addExpense(expense);
//                    } catch (SQLException throwable) {
//                        throwable.printStackTrace();
//                    } catch (ClassNotFoundException classNotFoundException) {
//                        classNotFoundException.printStackTrace();
//                    }
//                } catch (NumberFormatException ex) {
//                    System.out.println(ex);
//                    //adding the relevant exception..
//                }
//            }
//        });

        }

        public JPanel getPanel () {
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
        private PieChartComponent pieChart;
        private GroupLayout layout;
        private GroupLayout.SequentialGroup vGroup;
        private Box temp;

        /**
         * Pie chart constructor
         */
        public GeneratePieScreen() {
            panel = new JPanel();
            temp = Box.createHorizontalBox();
            pieChart = new PieChartComponent();
            temp.add(Box.createRigidArea(new Dimension(190, 0)));
            temp.add(pieChart);

            layout = new GroupLayout(panel);

            temp.setVisible(false);

            startDateLabel = new JLabel("Start Date: ");

            startDateField = new HintTextField("dd/mm/yyyy");
            startDateField.setMaximumSize(new Dimension(200, 25));

            endDateLabel = new JLabel("End Date: ");

            endDateField = new HintTextField("dd/mm/yyyy");
            endDateField.setMaximumSize(new Dimension(200, 25));

            Generate = new JButton("Generate");

            panel.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);

            GroupLayout.ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.CENTER);

            hGroup.addGroup(layout.createSequentialGroup()

                    .addGroup(layout.createParallelGroup()
                            .addComponent(startDateLabel)
                            .addComponent(endDateLabel))


                    .addGroup(layout.createParallelGroup()
                            .addComponent(startDateField)
                            .addComponent(endDateField)));

            hGroup.addComponent(Generate);
            hGroup.addComponent(temp);

            layout.setHorizontalGroup(hGroup);

            vGroup = layout.createSequentialGroup();

            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(startDateLabel)
                    .addComponent(startDateField));

            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(endDateLabel)
                    .addComponent(endDateField));

            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(Generate));

            vGroup.addGap(20);

            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(temp));

            layout.setVerticalGroup(vGroup);

            createListeners();
        }

        /**
         * Creates all listeners of the screen
         */
        private void createListeners() {
            Generate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    temp.setVisible(true);
                }
            });
        }

        /**
         * Gets screen's panel
         * @return JPanel object
         */
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

        /**
         * Login screen constructor
         */

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
            btnSignUp = new JButton("  Sign up  ");

            btnLogin.setBorder(new RoundedBorder(3));
            btnSignUp.setBorder(new RoundedBorder(3));

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
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            createListeners();
        }

        /**
         * Creates all listeners of the screen
         */
        private void createListeners() {
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

            btnSignUp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Creating sign up screen
                    SignUpScreen signUp = new SignUpScreen();
                    frame.dispose();
                }
            });
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

        /**
         * Main screen constructor of the project
         */

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
            frame.setVisible(true);

            createListeners();
        }

        /**
         * Sets main screen UI to new screen
         * @param newPanel JPanel of new screen
         */
        public void resetView(JPanel newPanel) {
            container.remove(mainPanel);
            container.repaint();
            container.revalidate();
            mainPanel = newPanel;

            container.add(newPanel,BorderLayout.CENTER);
            container.repaint();
            container.revalidate();
        }

        /**
         * Creates all listeners of the screen
         */
        public void createListeners() {
            viewBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ViewScreen viewScreen = null;
                    try {
                        viewScreen = new ViewScreen();
                    } catch (CostManagerException ex) {
                       ex.getCause();
                    }


                    resetView(viewScreen.getPanel());

                }
            });

            editBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    View.EditExpenseScreen editScreen = new View.EditExpenseScreen();
                    resetView(editScreen.getPanel());
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
                    frame.dispose();
                }
            });
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

        /**
         * Sign Up screen constructor
         */
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

<<<<<<< HEAD
            //Sign up listener
            btnSignUp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String pass=String.valueOf(tfPassword.getPassword());
                    if(!tfUserName.getText().isEmpty() && !pass.isEmpty()) {
                        Username username = new Username(tfUserName.getText());
                        Password password = new Password(Arrays.toString(tfPassword.getPassword()));
                        User user= new User(username,password);
                        try {
                            vm.addUser(user);
                        } catch (CostManagerException costManagerException) {
                            System.out.println(costManagerException.getMessage());
                            showMessage("Input Error", "Login Error");
                        }
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
=======
>>>>>>> 662700b1250f6a7e0c60b873e8a579c483d2ee9f
            btnSignUp.setBorder(new RoundedBorder(3));

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
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            createListeners();
        }

        /**
         * Creates all listeners of the screen
         */
        public void createListeners() {
            btnSignUp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String pass = String.valueOf(tfPassword.getPassword());
                    if(!tfUserName.getText().isEmpty() && !pass.isEmpty()) {
                        Username username = new Username(tfUserName.getText());
                        Password password = new Password(tfPassword.getPassword().toString());

                        User user= new User(username,password);
                        vm.addUser(user);

                        View.MainScreen mainScreen = new View.MainScreen();
                        frame.dispose();
                    }
                    else {
                        showMessage("user name or password is incorrect","Login error");
                    }
                }
            });
        }
    }

    public class ViewScreen {
        private JPanel panel;
        private JTable table;
        private JScrollPane sp;

<<<<<<< HEAD
        public ViewScreen() throws CostManagerException {
=======
        /**
         * View screen constructor (table)
         * @throws SQLException if there was an error with a query
         * @throws ClassNotFoundException if database wasn't initiated properly
         */
        public ViewScreen() throws SQLException, ClassNotFoundException {
>>>>>>> 662700b1250f6a7e0c60b873e8a579c483d2ee9f
            panel = new JPanel(new BorderLayout());
            getTable();
        }

<<<<<<< HEAD
        public void getTable() throws CostManagerException {
=======
        /**
         * Sets table data by requesting all expenses of a user from the database
         * @throws SQLException if there was an error with a query
         * @throws ClassNotFoundException if database wasn't initiated properly
         */
        public void getTable() throws SQLException, ClassNotFoundException {
>>>>>>> 662700b1250f6a7e0c60b873e8a579c483d2ee9f
            String data[][] = vm.getUserExpenses();
            String column[] = {"Id", "Cost", "Category", "Currency", "Description", "CreatedAt", "dueDate", "Frequency"};
            table = new JTable(data, column);
            table.setFillsViewportHeight(true);
            sp = new JScrollPane(table);
            panel.add(sp, BorderLayout.CENTER);
        }

        /**
         * Gets screen's panel
         * @return JPanel object
         */

        public JPanel getPanel() {
            return panel;
        }
    }
}
