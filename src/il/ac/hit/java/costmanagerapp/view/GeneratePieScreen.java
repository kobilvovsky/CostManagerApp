package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.view.viewutils.HintTextField;
import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;




import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneratePieScreen implements IView {

    private JPanel panel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private static JLabel startDateLabel;
    private static JTextField startDateField;
    private static JLabel endDateLabel;
    private static JTextField endDateField;
    private static JButton Generate;
    private PieChartComponent pieChart;

    public GeneratePieScreen() {
        panel = new JPanel();
        start();
    }

    private void start() {
        panel.setLayout(null);
        panel.setBounds(new Rectangle(700, 700));

        startDateLabel = new JLabel("Start Date: ");
        startDateLabel.setBounds(10,20,80,25);
        panel.add(startDateLabel, BorderLayout.WEST);

        startDateField = new HintTextField("dd/mm/yyyy");
        startDateField.setBounds(100,20,165,25);
        panel.add(startDateField, BorderLayout.WEST);

        endDateLabel = new JLabel("End Date: ");
        endDateLabel.setBounds(10,50,80,25);
        panel.add(endDateLabel, BorderLayout.WEST);

        endDateField = new HintTextField("dd/mm/yyyy");
        endDateField.setBounds(100,50,165,25);
        panel.add(endDateField, BorderLayout.WEST);

        Generate = new JButton("Generate");
        Generate.setBounds(95, 100, 100, 25);

        Generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pieChart = new PieChartComponent();
                pieChart.setBounds(new Rectangle(300, 300));
                panel.add(pieChart, BorderLayout.EAST);
            }
        });
        panel.add(Generate, BorderLayout.WEST);
        
        //rightPanel.add(pieChart);

        //JSplitPane pane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT,
                //leftPanel, rightPanel );

        //panel.add( leftPanel, BorderLayout.WEST );
        //panel.add( rightPanel, BorderLayout.EAST );
        //panel.add(leftPanel);

    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void setViewModel(IViewModel viewModel) {

    }

    @Override
    public void showMessage(String strMessage, String strTitle) {

    }
}
