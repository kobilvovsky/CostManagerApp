/*
    Submitted by:
    Erez Mizrahi 316267087
    Lvovsky Yakov 315825380
    Netanel Tarnorudsky 315424689
 */

package il.ac.hit.java.costmanagerapp.view.viewutils;

import javax.swing.border.Border;
import java.awt.*;

public class RoundedBorder implements Border {

    private int radius;

    /**
     * RoundedBorder constructor
     * @param radius radius number
     */
    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    /**
     * Gets button's border edges
     * @param c Component object
     * @return Insets object
     */
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    /**
     * Checks if button is transparent
     * @return true
     */
    public boolean isBorderOpaque() {
        return true;
    }

    /**
     * Paints the border of a button
     * @param c Component object
     * @param g Graphics object
     * @param x x point location border
     * @param y y point location border
     * @param width width of the border
     * @param height height height of the border
     */
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}