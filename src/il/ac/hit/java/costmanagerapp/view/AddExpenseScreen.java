package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.model.Category;
import il.ac.hit.java.costmanagerapp.model.Currency;
import il.ac.hit.java.costmanagerapp.model.Expense;
import il.ac.hit.java.costmanagerapp.model.Frequency;
import il.ac.hit.java.costmanagerapp.view.viewutils.HintTextField;
import il.ac.hit.java.costmanagerapp.view.viewutils.RoundedBorder;
import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;
import il.ac.hit.java.costmanagerapp.viewmodel.ViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class AddExpenseScreen implements IView {

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

    private IViewModel vm;

    {
        try {
            vm = new ViewModel();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


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

    public void start() {
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

        btnAddExpense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(tfExpenseAmount.getText());
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
                    Expense expense = new Expense(0,amount,category,currency,description,date,frequency);
                    vm.addExpense(expense);
                } catch (NumberFormatException ex) {
                    System.out.println(ex);
                    //adding the relevant exception..
                }
            }
        });

    }

    public JPanel getPanel () {
        return mainPanel;
    }

    @Override
    public void setViewModel(IViewModel viewModel) {
        System.out.println("Reached set ViewModel");
        this.vm=viewModel;
    }

    @Override
    public void showMessage(String strMessage, String strTitle) {

    }
}

