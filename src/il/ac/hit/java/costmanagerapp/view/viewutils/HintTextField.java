package il.ac.hit.java.costmanagerapp.view.viewutils;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class HintTextField extends JTextField implements FocusListener {

    private final String hint;
    private boolean showingHint;

    /**
     * HintTextField constructor
     * @param hint text hint
     */
    public HintTextField(final String hint) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        this.setColumns(15);
        super.addFocusListener(this);
    }

    /**
     * Checks if text field is in focus
     * @param e focus event
     */
    @Override
    public void focusGained(FocusEvent e) {
        if(this.getText().isEmpty()) {
            super.setText("");
            showingHint = false;
        }
    }

    /**
     * Checks if text field is out of focus
     * @param e focus event
     */
    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().isEmpty()) {
            super.setText(hint);
            showingHint = true;
        }
    }

    /**
     * Gets text-field's text
     * @return text of the text-field
     */
    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }
}
