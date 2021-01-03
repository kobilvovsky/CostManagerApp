package il.ac.hit.java.costmanagerapp.view;

import il.ac.hit.java.costmanagerapp.view.viewutils.HintTextField;
import il.ac.hit.java.costmanagerapp.viewmodel.IViewModel;

import javax.swing.*;
public class GeneratePieScreen implements IView {

    private JPanel panel;
    private static JLabel startDateLabel;
    private static JTextField startDateField;
    private static JLabel endDateLabel;
    private static JTextField endDateField;
    private static JButton Generate;

    public GeneratePieScreen() {
        panel = new JPanel();
        start();
    }

    private void start() {
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

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
