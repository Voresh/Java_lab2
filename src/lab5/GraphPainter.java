package lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphPainter {
    private JFrame currentFrame;
    private int amount;
    private long[] y;

    public GraphPainter(String windowName,int width, int height) {
        currentFrame = new JFrame(windowName);
        currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentFrame.setSize(width, height);
    }

    public void paintGraph(int amount, long[] y) {
        this.amount = amount;
        this.y = y;
        BufferedImage bufferedImage = new BufferedImage(currentFrame.getWidth(), currentFrame.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D)bufferedImage.getGraphics();
        g2d.setColor(Color.white);
        g2d.drawLine(25,225, 25, 25);
        g2d.drawLine(25,225, 475, 225);
        paintComponent(g2d);
        currentFrame.add(new JLabel(new ImageIcon(bufferedImage)));
        currentFrame.pack();
        currentFrame.setVisible(true);
    }

    public void paintComponent(Graphics graphics) {
        Polygon polygon = new Polygon();
        for (int x = 0; x < amount; x++) {
            polygon.addPoint(x+25,currentFrame.getHeight() - ((int)y[x] + 25));
        }
        graphics.setColor(Color.blue);
        graphics.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
    }
}
