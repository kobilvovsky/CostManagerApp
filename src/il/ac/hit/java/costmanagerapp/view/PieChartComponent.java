package il.ac.hit.java.costmanagerapp.view;

import javax.swing.*;
import java.awt.*;

class Part {
    double value;
    Color color;

    public Part(double value) {
        this.value = value;
        this.color = new Color((int)(Math.random() * 0x1000000));
    }
}

public class PieChartComponent extends JComponent {
    Rectangle area = new Rectangle( 130, 130);
    Part[] slices = {
            new Part(15), new Part(30), new Part(25), new Part(30)
    };

    PieChartComponent() { }

    public void paint(Graphics g) {
        drawPie((Graphics2D) g, area, slices);
    }

    void drawPie(Graphics2D g, Rectangle area, Part[] slices) {
        double total = 0.0D;
        for (Part slice : slices) {
            total += slice.value;
        }
        double curValue = 0.0D;
        int startAngle = 0;
        for (Part slice : slices) {
            startAngle = (int) (curValue * 360 / total);
            int arcAngle = (int) (slice.value * 360 / total);

            g.setColor(slice.color);
            g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
            curValue += slice.value;
        }
    }
}