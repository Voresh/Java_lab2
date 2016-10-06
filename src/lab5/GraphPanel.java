package lab5;

import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel{
    private int amount;
    private long[] y;

    public GraphPanel(int amount, long[] y) {
        super();
        this.amount = amount;
        this.y = y;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Polygon polygon = new Polygon();
        for (int x = 0; x < amount; x++) {
            polygon.addPoint(x*2,(int)y[x]);
        }
        graphics.setColor(Color.blue);
        graphics.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
    }
}
