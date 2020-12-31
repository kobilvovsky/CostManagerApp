package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.view.viewutils.HintTextField;
import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;

import javax.swing.*;
import java.awt.*;


public class GeneratePieScreen implements IView {

    private JFrame frame;
    private JPanel panel;
    private static JLabel startDateLabel;
    private static JTextField startDateField;
    private static JLabel endDateLabel;
    private static JTextField endDateField;
    private static JButton Generate;

    public GeneratePieScreen() {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,200);
        frame.setTitle("CostManagerApp - Generate pie");

        panel = new JPanel();
        frame.add(panel, BorderLayout.CENTER);
        placeComponents(panel);

        frame.setVisible(true);

    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


//        Creating Username Label
        startDateLabel = new JLabel("Start Date: ");
        startDateLabel.setBounds(10,20,80,25);
        panel.add(startDateLabel);

        startDateField = new HintTextField("dd/mm/yyyy");
        startDateField.setBounds(100,20,165,25);
        panel.add(startDateField);

        endDateLabel = new JLabel("End Date: ");
        endDateLabel.setBounds(10,50,80,25);
        panel.add(endDateLabel);

        endDateField = new HintTextField("dd/mm/yyyy");
        endDateField.setBounds(100,50,165,25);
        panel.add(endDateField);


        Generate = new JButton("Generate");
        Generate.setBounds(95, 100, 100, 25);
        panel.add(Generate);
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
