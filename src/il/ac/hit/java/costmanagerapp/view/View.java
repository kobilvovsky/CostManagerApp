package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.model.*;
import il.ac.hit.java.costmanagerapp.model.Currency;
import il.ac.hit.java.costmanagerapp.model.exceptions.CostManagerException;
import il.ac.hit.java.costmanagerapp.view.viewutils.HintTextField;
import il.ac.hit.java.costmanagerapp.view.viewutils.MessageBox;
import il.ac.hit.java.costmanagerapp.view.viewutils.RoundedBorder;
import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.*;

public class View implements IView {

    private IViewModel vm;
    private LoginScreen ui;
    private MainScreen mainScreen;
    private ViewScreen viewScreen;
    private GeneratePieScreen generatePieScreen;
    private AddExpenseScreen addExpenseScreen;
    private EditExpenseScreen editExpenseScreen;
    private final String DATE_REGEX="^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";

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

    @Override
    public void callGetTable(String[][] data) {
        viewScreen.getTable(data);
    }

    @Override
    public void printExpenseToEditScreen(ArrayList<String> data) {
        editExpenseScreen.uploadDataToComponents(data);
    }

    @Override
    public void printPieToScreen(HashMap<String, Double> data) {
        generatePieScreen.setReport(data);
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

    /**
     * Check if a string is a number and less than 7 digits
     * @param number number as string
     * @return boolean
     */
    public boolean isNumeric(String number) {
        return number.matches("\\d{1,6}");
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

        private Vector defaultCategory;
        private DefaultComboBoxModel comboBoxModel;

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

            rbEURO.setSelected(true);

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
            comboBoxModel = new DefaultComboBoxModel(defaultCategory);
            cbCategory = new JComboBox(comboBoxModel);
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

            rbOneTime.setSelected(true);
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

            vGroup = layout.createSequentialGroup();

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
         * Creates add category listener
         * Creates add expense listener
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
                        comboBoxModel.addElement(result);
                        cbCategory.setSelectedItem(result);
                    }
                }
            });

            btnAddExpense.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        double amount = 0;
                        if (isNumeric(tfExpenseAmount.getText())) {
                            amount = Double.parseDouble(tfExpenseAmount.getText());
                        String description = tfExpenseDescription.getText();

                        String categoryStr = String.valueOf(cbCategory.getSelectedItem());
                        String frequencyStr ="";
                        frequencyStr =bgFrequency.getSelection().getActionCommand();
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
                        String date="";
                        if(tfExpenseDate.getText().matches(DATE_REGEX)) {
                            date = tfExpenseDate.getText();
                            Expense expense = new Expense(amount, categoryStr, currency, description, date, frequency);
                            try {
                                vm.addExpense(expense);
                            } catch (CostManagerException costManagerException) {
                                costManagerException.printStackTrace();
                            }
                        }
                        else{
                                showMessage("You must enter a valid date", "Error");
                            }

                    }else {
                            showMessage("You must enter a number", "Error");
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

        private JLabel lbExpenseId;
        private JLabel lbExpenseAmount;
        private JLabel lbExpenseCurrency;
        private JLabel lbExpenseDescription;
        private JLabel lbExpenseCategory;
        private JLabel lbExpenseFrequency;
        private JLabel lbExpenseDate;

        private JTextField tfExpenseId;
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
        private Box bxLoad;
        private Box bxButtons;

        private JButton btnLoadExpense;
        private JButton btnUpdateExpense;
        private JButton btnAddCategory;
        private JButton btnDeleteExpense;

        private Vector defaultCategory;
        private DefaultComboBoxModel comboBoxModel;

        /**
         * Edit expense screen constructor
         */
        public EditExpenseScreen() {
            mainPanel = new JPanel();
            layout = new GroupLayout(mainPanel);

            lbExpenseId = new JLabel("Enter Expense ID: ");
            tfExpenseId = new JTextField(15);
            tfExpenseId.setMaximumSize(new Dimension(200, 25));

            btnLoadExpense = new JButton("Load Expense");
            btnLoadExpense.setBorder(new RoundedBorder(5));

            btnUpdateExpense = new JButton("Update Expense");
            btnUpdateExpense.setBorder(new RoundedBorder(5));

            btnDeleteExpense = new JButton("Delete Expense");
            btnDeleteExpense.setBorder(new RoundedBorder(5));

            bxLoad = Box.createHorizontalBox();
            bxLoad.add(tfExpenseId);
            bxLoad.add(Box.createRigidArea(new Dimension(5, 0)));
            bxLoad.add(btnLoadExpense);

            bxButtons = Box.createHorizontalBox();
            bxButtons.add(btnUpdateExpense);
            bxButtons.add(Box.createRigidArea(new Dimension(10, 0)));
            bxButtons.add(btnDeleteExpense);

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
            comboBoxModel = new DefaultComboBoxModel(defaultCategory);
            cbCategory = new JComboBox(comboBoxModel);
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
            tfExpenseDate = new JTextField("yyyy-mm-dd");
            tfExpenseDate.setMaximumSize(new Dimension(200, 25));

            mainPanel.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            GroupLayout.ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.CENTER);

            hGroup.addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                            .addComponent(lbExpenseId)
                            .addComponent(lbExpenseAmount)
                            .addComponent(lbExpenseCurrency)
                            .addComponent(lbExpenseDescription)
                            .addComponent(lbExpenseCategory)
                            .addComponent(lbExpenseFrequency)
                            .addComponent(lbExpenseDate))
                    .addGroup(layout.createParallelGroup()
                            .addComponent(bxLoad)
                            .addComponent(tfExpenseAmount)
                            .addComponent(bxCurrency)
                            .addComponent(tfExpenseDescription)
                            .addComponent(bxCategory)
                            .addComponent(bxFrequency)
                            .addComponent(tfExpenseDate)));
            hGroup.addComponent(bxButtons);

            layout.setHorizontalGroup(hGroup);

            vGroup = layout.createSequentialGroup();

            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(lbExpenseId)
                    .addComponent(bxLoad));

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
                    .addComponent(bxButtons));

            layout.setVerticalGroup(vGroup);
            createListeners();
        }

        public void createListeners() {
            btnUpdateExpense.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        if (isNumeric(tfExpenseId.getText())) {
                            int id = Integer.parseInt(tfExpenseId.getText());
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
                            if(tfExpenseDate.getText().matches(DATE_REGEX)) {
                                String date = tfExpenseDate.getText();

                                try {
                                    vm.updateExpense(id, amount, category, currency, description, date, frequency);
                                } catch (CostManagerException costManagerException) {
                                    costManagerException.printStackTrace();
                                }
                            }
                            else {
                                showMessage("You must enter a valid date", "Error");
                            }

                        } else {
                            showMessage("You must enter a number", "Error");
                        }
                }
            });

            btnDeleteExpense.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (isNumeric(tfExpenseId.getText())) {
                        try {
                            vm.deleteExpense(Integer.parseInt(tfExpenseId.getText()));
                        } catch (CostManagerException costManagerException) {
                            costManagerException.printStackTrace();
                        }
                    } else {
                        showMessage("You must enter a number", "Error");
                    }
                }
            });

            btnLoadExpense.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(isNumeric(tfExpenseId.getText())) {
                        try {
                            vm.getExpense(Integer.parseInt(tfExpenseId.getText()));
                        } catch (CostManagerException costManagerException) {
                            costManagerException.printStackTrace();
                        }
                    } else {
                        showMessage("You must enter a number", "Error");
                    }
                }
            });

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
                        comboBoxModel.addElement(result);
                        cbCategory.setSelectedItem(result);
                    }
                }
            });
        }

        /**
         * Updates textviews with the data of the expense id
         * @param data expense data from DB
         */
        public void uploadDataToComponents(ArrayList<String> data) {
            if(!data.isEmpty()) {
                boolean defCategory = false;
                tfExpenseAmount.setText(data.get(0));

                for (Object category : defaultCategory) {
                    if (category.equals(data.get(1))) {
                        cbCategory.setSelectedItem(category); //category
                        defCategory = true;
                        break;
                    }
                }

                if (!defCategory) {
                    defaultCategory.add(data.get(1));
                    cbCategory.setSelectedItem(data.get(1));
                }

                setCurrencyRadio(data.get(2));
                tfExpenseDescription.setText(data.get(3));
                tfExpenseDate.setText(data.get(4));
                setFrequencyRadio(data.get(5));
            } else {
                showMessage("There is no expense with that id", "Error");
            }
        }

        /**
         * Gets screen's panel
         * @return JPanel object
         */
        public JPanel getPanel () {
            return mainPanel;
        }

        /**
         * Sets the category based on Expense's category value
         * @param currency currency type as string
         */
        public void setCurrencyRadio(String currency) {
            bgCurrency.clearSelection();

            switch(currency) {
                case "EURO":
                    rbEURO.setSelected(true);
                    break;

                case "USD":
                    rbUSD.setSelected(true);
                    break;

                case "NIS":
                    rbNIS.setSelected(true);
                    break;

                case "POUND":
                    rbPOUND.setSelected(true);
                    break;

                case "YEN":
                    rbYEN.setSelected(true);
                    break;
            }
        }

        /**
         * Sets the frequency based on Expense's frequency value
         * @param freq frequency type as string
         */
        public void setFrequencyRadio(String freq) {
            bgFrequency.clearSelection();

            if(freq.equals(Frequency.ONE_TIME.name())) {
                rbOneTime.setSelected(true);
            } else if (freq.equals(Frequency.MONTHLY.name())) {
                rbMonthly.setSelected(true);
            } else if (freq.equals(Frequency.YEARLY.name())) {
                rbYearly.setSelected(true);
            }
        }
    }

    public class GeneratePieScreen {
        private JPanel dataPanel;
        private JPanel piePanel;
        private JPanel containerPanel;
        private JLabel startDateLabel;
        private JTextField startDateField;
        private JLabel endDateLabel;
        private JTextField endDateField;
        private JButton generateBtn;
        private GroupLayout layout;
        private GroupLayout.SequentialGroup vGroup;
        private PieChart pieChart;
        private HashMap<String, Double> tempHash;

        public void setReport(HashMap<String, Double> data) {
            pieChart = new PieChart("test", data);
            pieChart.setPreferredSize(new Dimension(280, 280));
            piePanel.add(pieChart);
            piePanel.revalidate();
        }

        /**
         * Pie chart constructor
         */
        public GeneratePieScreen() {
            dataPanel = new JPanel();
            piePanel = new JPanel();
            piePanel.setVisible(false);
            layout = new GroupLayout(dataPanel);
            startDateLabel = new JLabel("Start Date: ");

            startDateField = new HintTextField("yyyy-mm-dd");
            startDateField.setMaximumSize(new Dimension(200, 25));

            endDateLabel = new JLabel("End Date: ");

            endDateField = new HintTextField("yyyy-mm-dd");
            endDateField.setMaximumSize(new Dimension(200, 25));

            generateBtn = new JButton("Generate");

            generateBtn.setBorder(new RoundedBorder(3));

            dataPanel.setLayout(layout);
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

            hGroup.addComponent(generateBtn);

            layout.setHorizontalGroup(hGroup);

            vGroup = layout.createSequentialGroup();

            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(startDateLabel)
                    .addComponent(startDateField));

            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(endDateLabel)
                    .addComponent(endDateField));

            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(generateBtn));

            vGroup.addGap(20);
            layout.setVerticalGroup(vGroup);

            createListeners();
            containerPanel = new JPanel( new BorderLayout() );
            containerPanel.add( dataPanel, BorderLayout.WEST );
            containerPanel.add(piePanel, BorderLayout.EAST );
        }

        /**
         * Creates graph screen listener
         */
        private void createListeners() {
            generateBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    piePanel.removeAll();
                        try {
                            if(startDateField.getText().matches(DATE_REGEX)) {
                                if(endDateField.getText().matches(DATE_REGEX)) {
                                    vm.getSumPerCategory(startDateField.getText(), endDateField.getText());
                                } else {
                                    MessageBox.infoBox("Wrong end date entered", "Error");
                                }
                            } else {
                                MessageBox.infoBox("Wrong start date entered", "Error");
                            }
                        } catch (CostManagerException costManagerException) {
                            costManagerException.printStackTrace();
                        }
                        piePanel.setVisible(true);
                }
            });
        }

        /**
         * Gets screen's panel
         * @return JPanel object
         */
        public JPanel getPanel() {
            return containerPanel;
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
         * Creates user login button listener
         * Creates user sign up listener
         */
        private void createListeners() {
            btnLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String pass=String.valueOf(tfPassword.getPassword());
                    //checking that all fields are filled correctly
                    if(!tfUserName.getText().isEmpty() && !pass.isEmpty()) {
                        View.this.mainScreen = new MainScreen();
                        frame.dispose();
//                        boolean r = vm.isUserMatched(tfUserName.getText(), pass);
//                        System.out.println(r);
//                        if(r) {
//                            MainScreen mainScreen = new MainScreen();
//                            frame.dispose();
//                        } else
                    } else {
                        showMessage("Please enter username and password","EREZ");
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

            editBtn.setBorder(new RoundedBorder(3));
            reportBtn.setBorder(new RoundedBorder(3));
            viewBtn.setBorder(new RoundedBorder(3));
            addBtn.setBorder(new RoundedBorder(3));
            logoutBtn.setBorder(new RoundedBorder(3));


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

            frame.setSize(710, 340);
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
         * Creates view screen listener
         * Creates edit screen listener
         * Creates add screen listener
         * Creates report screen listener
         * Creates log out screen listener
         */
        public void createListeners() {
            viewBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        View.this.viewScreen = new ViewScreen();
                        View.this.viewScreen.generateTable();
                    } catch (CostManagerException ex) {
                       ex.getCause();
                    }

                    resetView(View.this.viewScreen.getPanel());
                }
            });

            editBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    View.this.editExpenseScreen = new EditExpenseScreen();
                    resetView(editExpenseScreen.getPanel());
                }
            });

            addBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    View.this.addExpenseScreen = new AddExpenseScreen();
                    resetView(addExpenseScreen.getPanel());
                }
            });

            reportBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    View.this.generatePieScreen = new GeneratePieScreen();
                    resetView(generatePieScreen.getPanel());
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
        public JFrame getFrame () {
            return frame;
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

            createListeners();
        }

        /**
         * Creates user sign up listener
         */
        public void createListeners() {
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
                        View.MainScreen mainScreen = new View.MainScreen();
                        frame.dispose();
                    }
                    else{
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

        String column[] = {"Id", "Cost", "Category", "Currency", "Description", "CreatedAt", "dueDate", "Frequency"};

         /**
         * View screen constructor (table)
         */
        public ViewScreen() throws CostManagerException {
            panel = new JPanel(new BorderLayout());
        }

         /**
         * Sets table data by requesting all expenses of a user from the database
         */
        public void generateTable() throws CostManagerException {
            vm.getUserExpenses();
        }

        public void getTable(String [][] data) {
            table = new JTable(data, column);
            table.setFillsViewportHeight(true);
            sp = new JScrollPane(table);
            panel.add(sp, BorderLayout.CENTER);
            panel.revalidate();
            panel.repaint();
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
