package il.ac.hit.java.costmanagerapp.view.viewutils;

import javax.swing.*;

public class MessageBox {

        public static void infoBox(String infoMessage, String titleBar)
        {
            JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
        }

}
