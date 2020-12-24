package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.model.Category;
import il.ac.hit.java.costmanagerapp.model.Currency;
import il.ac.hit.java.costmanagerapp.model.Frequency;
import il.ac.hit.java.costmanagerapp.view.viewutils.HintTextField;
import il.ac.hit.java.costmanagerapp.view.viewutils.RoundedBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import java.util.List;

public class AddExpenseScreen {

    public AddExpenseScreen() {
        createGUI();
    }
        
    private void createGUI() {

        JFrame mainFrame = new JFrame("Add Expense");
        mainFrame.setSize(690,450);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Creating the menu bar
        /*JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);*/

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setLayout(null);
        //mainPanel.setBackground(Color.decode("#ffba93"));

        JLabel header = new JLabel("Add Expense");
        header.setBounds(260, 10, 150, 20);
        header.setFont(new Font("San Francisco", Font.BOLD, 20));
        header.setForeground(Color.decode("#ec5858"));

        JLabel expenseAmount = new JLabel("Enter Expense Amount:");
        expenseAmount.setBounds(90,50,150,20);
        JTextField amountTextField = new JTextField(16);
        amountTextField.setBounds(250, 50 , 200, 25);
        amountTextField.setBorder(new RoundedBorder(3));

        JLabel expenseCurrency = new JLabel("Select Expense Currency:");
        expenseCurrency.setBounds(90, 100, 200, 25);

        JRadioButton usd = new JRadioButton(Currency.USD.name());
        usd.setBounds(250, 100, 50, 20);

        JRadioButton euro = new JRadioButton(Currency.EURO.name());
        euro.setBounds(300, 100, 60, 20);

        JRadioButton yen = new JRadioButton(Currency.YEN.name());
        yen.setBounds(360, 100, 50, 20);

        JRadioButton pound = new JRadioButton(Currency.POUND.name());
        pound.setBounds(410, 100, 70, 20);

        JRadioButton nis = new JRadioButton(Currency.NIS.name());
        nis.setBounds(480, 100, 50, 20);

        ButtonGroup currencyGroup = new ButtonGroup();

        currencyGroup.add(usd);
        currencyGroup.add(euro);
        currencyGroup.add(yen);
        currencyGroup.add(pound);
        currencyGroup.add(nis);


        JLabel expenseDescription = new JLabel("Enter Expense Description:");
        expenseDescription.setBounds(90, 150, 200, 20);
        JTextField descriptionTextField = new JTextField(16);
        descriptionTextField.setBounds(250, 150,200, 25);
        descriptionTextField.setBorder(new RoundedBorder(3));

        JLabel expenseCategory = new JLabel("Select Expense Category:");
        expenseCategory.setBounds(90, 200, 200, 20);

        JComboBox category = new JComboBox(Category.INITIAL_CATEGORIES);
        category.setBounds(250, 200, 200 ,25);
        category.setBorder(new RoundedBorder(3));

        JButton newCategoryBtn = new JButton("Add Category");
        newCategoryBtn.setBounds(460, 200 ,110, 25);
        newCategoryBtn.setBorder(new RoundedBorder(13));
        newCategoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = (String)JOptionPane.showInputDialog(
                        mainFrame,
                        "Add your own category",
                        "Add New Category",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "Enter your category"
                );
                if ((result != null) && (result.length() > 0)) {
                    category.setVisible(false);
                    JTextField privateCategory = new JTextField(result);
                    privateCategory.setBounds(250, 200, 200, 25);
                    privateCategory.setBorder(new RoundedBorder(3));

                    JButton reverseBtn = new JButton("Default");
                    reverseBtn.setBounds(580, 200, 70, 25);
                    reverseBtn.setBorder(new RoundedBorder(13));
                    reverseBtn.setVisible(true);
                    mainPanel.add(reverseBtn);
                    mainPanel.repaint();
                    reverseBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            privateCategory.setVisible(false);
                            category.setVisible(true);
                        }
                    });
                    mainPanel.add(privateCategory);
                }
            }
        });

        JLabel expenseFrequency = new JLabel("Select Expense Frequency:");
        expenseFrequency.setBounds(90, 250, 200, 20);

        JRadioButton onetime = new JRadioButton(Frequency.ONE_TIME.name());
        onetime.setBounds(250, 250, 90, 20);

        JRadioButton monthly = new JRadioButton(Frequency.MONTHLY.name());
        monthly.setBounds(340, 250, 90, 20);

        JRadioButton yearly = new JRadioButton(Frequency.YEARLY.name());
        yearly.setBounds(430, 250, 90, 20);

        ButtonGroup frequencyGroup = new ButtonGroup();

        frequencyGroup.add(onetime);
        frequencyGroup.add(monthly);
        frequencyGroup.add(yearly);

        JLabel expenseDate = new JLabel("Enter Expense Date:");
        expenseDate.setBounds(90, 300, 200, 25);
        JTextField date = new HintTextField("dd/mm/yyyy");
        date.setBounds(250, 300, 200 ,25);
        date.setBorder(new RoundedBorder(3));

        JButton addBtn = new JButton("Add Expense");
        addBtn.setBounds(260, 350, 180, 30);
        addBtn.setBorder(new RoundedBorder(13));


        mainPanel.add(header);
        mainPanel.add(expenseAmount);
        mainPanel.add(amountTextField);
        mainPanel.add(expenseDescription);
        mainPanel.add(descriptionTextField);
        mainPanel.add(expenseCategory);
        mainPanel.add(category);
        mainPanel.add(expenseDate);
        mainPanel.add(date);
        mainPanel.add(expenseCurrency);
        mainPanel.add(usd);
        mainPanel.add(euro);
        mainPanel.add(yen);
        mainPanel.add(pound);
        mainPanel.add(nis);
        mainPanel.add(expenseFrequency);
        mainPanel.add(onetime);
        mainPanel.add(monthly);
        mainPanel.add(yearly);
        mainPanel.add(addBtn);
        mainPanel.add(newCategoryBtn);


        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

}


