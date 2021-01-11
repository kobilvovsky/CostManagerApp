package il.ac.hit.java.costmanagerapp.view.viewutils;

import javax.swing.*;

public class MessageBox
{

    /**
     * Generates UI message box
     * @param infoMessage body text
     * @param titleBar title text
     */
    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

}
