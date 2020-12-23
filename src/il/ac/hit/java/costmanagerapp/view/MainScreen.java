package il.ac.hit.java.costmanagerapp.view;

import javax.swing.*;
import java.awt.*;

public class MainScreen {
    private JFrame frame;
    private JPanel panel;
    private static JButton editBtn;
    private static JButton addBtn;
    private static JButton reportBtn;
    private static JButton logoutBtn;

    public MainScreen() {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(285,240);
        frame.setTitle("CostManagerApp - Menu");

        panel = new JPanel();
        placeComponents(panel);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        editBtn = new JButton("<html>Edit<br /> Expense</html>");
        editBtn.setFont(new Font("Arial", Font.PLAIN, 17));
        editBtn.setBounds(20, 20, 100, 75);
        panel.add(editBtn);

        reportBtn = new JButton("<html>Generate<br />Report</html>");
        reportBtn.setFont(new Font("Arial", Font.PLAIN, 17));
        reportBtn.setBounds(20, 105, 100, 75);
        panel.add(reportBtn);

        addBtn = new JButton("<html>Add<br />Expense</html>");
        addBtn.setFont(new Font("Arial", Font.PLAIN, 17));
        addBtn.setBounds(145, 20, 100, 75);
        panel.add(addBtn);

        logoutBtn = new JButton("<html>Logout");
        logoutBtn.setFont(new Font("Arial", Font.PLAIN, 17));
        logoutBtn.setBounds(145, 105, 100, 75);
        panel.add(logoutBtn);
    }

}
