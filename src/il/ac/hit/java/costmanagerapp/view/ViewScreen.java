package il.ac.hit.java.costmanagerapp.view;

import javax.swing.*;
import java.awt.*;

public class ViewScreen {
    private JPanel panel;
    private JTable table;
    private JScrollPane sp;

    public ViewScreen() {
        panel = new JPanel(new BorderLayout());

        start();
    }

    public void start() {
        String data[][] = {
                {"1", "1", "expName", "100", "2", "USD", "abba...", "2020-12-17", "2020-12-21", "1"},
                {"2", "1", "expName2", "200", "3", "EURO", "qqqq...", "2020-12-17", "2020-01-20", "1"},
                {"3", "2", "expName3", "300", "1", "USD", "zzzz...", "2020-12-17", "2020-12-23", "1"},
                {"4", "3", "expName4", "400", "1", "NIS", "aaaa...", "2020-12-17", "2020-12-25", "2"},
                {"5", "1", "expName5", "500", "2", "USD", "nnnnnnnn...", "2020-12-17", "2020-01-01", "3"},
        };

        String column[] = {"Id", "OwnerId", "Name", "Cost", "Category", "Currency", "Description", "CreatedAt", "dueDate", "Type"};
        table = new JTable(data, column);
        table.setFillsViewportHeight(true);
        sp = new JScrollPane(table);
        panel.add(sp, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }
}
