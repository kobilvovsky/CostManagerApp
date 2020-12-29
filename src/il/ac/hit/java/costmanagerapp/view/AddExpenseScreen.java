package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.model.Category;
import il.ac.hit.java.costmanagerapp.model.Currency;
import il.ac.hit.java.costmanagerapp.model.Frequency;
import il.ac.hit.java.costmanagerapp.view.viewutils.HintTextField;
import il.ac.hit.java.costmanagerapp.view.viewutils.RoundedBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;

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
        tfExpenseAmount = new JTextField(10);
        tfExpenseAmount.setPreferredSize(new Dimension(20, 20));
        //tfExpenseAmount.setBorder(new RoundedBorder(3));


        lbExpenseCurrency = new JLabel("Select Expense Currency");
        bgCurrency = new ButtonGroup();
        bxCurrency = Box.createHorizontalBox();
        rbEURO = new JRadioButton(Currency.EURO.name());
        rbUSD = new JRadioButton(Currency.USD.name());
        rbYEN = new JRadioButton(Currency.YEN.name());
        rbPOUND = new JRadioButton(Currency.POUND.name());
        rbNIS = new JRadioButton(Currency.NIS.name());

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
        tfExpenseDescription = new JTextField(20);
        //tfExpenseDescription.setBorder(new RoundedBorder(3));

        lbExpenseCategory = new JLabel("Select Expense Category: ");
        defaultCategory = new Vector();
        Collections.addAll(defaultCategory, Category.INITIAL_CATEGORIES);
        model = new DefaultComboBoxModel(defaultCategory);
        cbCategory = new JComboBox(model);
        //cbCategory.setBorder(new RoundedBorder(3));
        btnAddCategory = new JButton("Add Category");
        //btnAddCategory.setBorder(new RoundedBorder(12));

        lbExpenseFrequency = new JLabel("Select Expense Frequency");
        bgFrequency = new ButtonGroup();
        bxFrequency = Box.createHorizontalBox();

        rbOneTime = new JRadioButton(Frequency.ONE_TIME.name());
        rbMonthly = new JRadioButton(Frequency.MONTHLY.name());
        rbYearly = new JRadioButton(Frequency.YEARLY.name());

        bgFrequency.add(rbOneTime);
        bgFrequency.add(rbMonthly);
        bgFrequency.add(rbYearly);

        bxFrequency.add(rbOneTime);
        bxFrequency.add(rbMonthly);
        bxFrequency.add(rbYearly);

        lbExpenseDate = new JLabel("Enter Expense Date: ");
        tfExpenseDate = new HintTextField("dd/mm/yyyy");
        //tfExpenseDate.setBorder(new RoundedBorder(3));

        btnAddExpense = new JButton("Add Expense");
        //btnAddExpense.setBorder(new RoundedBorder(12));
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
                        .addComponent(cbCategory)
                        .addComponent(bxFrequency)
                        .addComponent(tfExpenseDate))
                .addGroup(layout.createParallelGroup()
                        .addComponent(btnAddCategory)));
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
                .addComponent(cbCategory)
                .addComponent(btnAddCategory));

        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(lbExpenseFrequency)
                .addComponent(bxFrequency));

        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(lbExpenseDate)
                .addComponent(tfExpenseDate));

        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(btnAddExpense));

        layout.setVerticalGroup(vGroup);

    }

    public JPanel getPanel () {
        return mainPanel;
    }

}


