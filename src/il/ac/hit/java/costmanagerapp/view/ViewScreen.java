package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.model.Frequency;

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
                {"1", "100", "2", "USD", "abba...", "2020-12-17", "2020-12-21", Frequency.ONE_TIME.name()},
                {"2", "200", "3", "EURO", "qqqq...", "2020-12-17", "2020-01-20", Frequency.ONE_TIME.name()},
                {"3", "300", "1", "USD", "zzzz...", "2020-12-17", "2020-12-23", Frequency.ONE_TIME.name()},
                {"4", "400", "1", "NIS", "aaaa...", "2020-12-17", "2020-12-25", Frequency.MONTHLY.name()},
                {"5", "500", "2", "USD", "nnnnnnnn...", "2020-12-17", "2020-01-01", Frequency.YEARLY.name()},
        };

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
