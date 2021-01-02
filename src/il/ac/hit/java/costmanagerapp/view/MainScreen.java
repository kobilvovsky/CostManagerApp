package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainScreen implements IView {
    private JFrame frame;
    private JPanel leftPanel;
    private JPanel leftGridPanel;
    private JPanel mainPanel;
    private JPanel dynamicPanel;

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
        dynamicPanel = new JPanel();
        container = frame.getContentPane();

        editBtn = new JButton("Edit Expense");
        reportBtn = new JButton("Generate Report");
        viewBtn = new JButton("View Expenses");
        addBtn = new JButton("Add Expense");
        logoutBtn = new JButton("Logout");

        viewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewScreen viewScreen = new ViewScreen();
                resetView(viewScreen.getPanel());
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddExpenseScreen addScreen = new AddExpenseScreen();
                resetView(addScreen.getPanel());
            }
        });

        start();
    }

    public void resetView(JPanel newPanel) {
        container.remove(mainPanel);
        container.repaint();
        container.revalidate();

        mainPanel = newPanel;
        container.add(newPanel);
        container.repaint();
        container.revalidate();
    }

    public void start() {
        container.setLayout(new BorderLayout());
        //frame.getRootPane().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.DARK_GRAY));

        leftPanel.setBorder(new LineBorder(Color.BLACK, 2));
        leftPanel.setLayout(new FlowLayout(4, 4,4));
        //leftPanel.setBackground(Color.CYAN);

        leftGridPanel.setLayout(new GridLayout(5, 1, 5, 5));
        //leftGridPanel.setBorder(new LineBorder(Color.BLACK, 2));
        //leftGridPanel.setBackground(Color.CYAN);

        leftGridPanel.add(viewBtn);
        leftGridPanel.add(addBtn);
        leftGridPanel.add(editBtn);
        leftGridPanel.add(reportBtn);
        leftGridPanel.add(logoutBtn);
        leftPanel.add(leftGridPanel);

        mainPanel.setBorder(new LineBorder(Color.BLACK, 2));
        mainPanel.setBackground(Color.YELLOW);
        //mainPanel.add(dynamicPanel);
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
        frame.setSize(950, 500);
        frame.setLocation(500, 500);
        frame.setVisible(true);
    }
  
      @Override
    public void setViewModel(IViewModel viewModel) {

    }

    @Override
    public void showMessage() {

    }

    @Override
    public void showItem() {
    }
}
