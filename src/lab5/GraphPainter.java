package lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphPainter {
    private JFrame currentFrame;
    private int amount;
    private long[] y;
    private int axisOffset;

    public GraphPainter(String windowName,int width, int height, int axisOffset) {
        currentFrame = new JFrame(windowName);
        currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentFrame.setSize(width, height);
        this.axisOffset = axisOffset;
    }

    public void paintGraph(int amount, long[] y) {
        this.amount = amount;
        this.y = y;
        BufferedImage bufferedImage = new BufferedImage(currentFrame.getWidth(), currentFrame.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0, 0, currentFrame.getWidth(), currentFrame.getHeight());
        drawGraph(graphics2D);
        drawAxes(graphics2D);
        JLabel label = new JLabel(new ImageIcon(bufferedImage));
        currentFrame.add(label);
        currentFrame.pack();
        currentFrame.setVisible(true);
    }

    private void drawAxes(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawLine(axisOffset, currentFrame.getHeight(), axisOffset, axisOffset);
        graphics.drawLine(0, currentFrame.getHeight() - axisOffset, currentFrame.getWidth() - axisOffset, currentFrame.getHeight() - axisOffset);
        int arrowOffset = 5;
        int arrowLength = 10;//!! to final
        graphics.drawLine(axisOffset, axisOffset, axisOffset - arrowOffset, axisOffset + arrowLength);
        graphics.drawLine(axisOffset, axisOffset, axisOffset + arrowOffset, axisOffset + arrowLength);

        graphics.drawLine(currentFrame.getWidth() - axisOffset, currentFrame.getHeight() - axisOffset, currentFrame.getWidth() - axisOffset - arrowLength, currentFrame.getHeight() - axisOffset - arrowOffset);
        graphics.drawLine(currentFrame.getWidth() - axisOffset, currentFrame.getHeight() - axisOffset, currentFrame.getWidth() - axisOffset - arrowLength, currentFrame.getHeight() - axisOffset + arrowOffset);
    }

    private void drawGraph(Graphics graphics) {
        Polygon polygon = new Polygon();
        for (int x = 0; x < amount; x++) {
            polygon.addPoint(x + axisOffset, currentFrame.getHeight() - ((int) y[x] + axisOffset));
        }
        graphics.setColor(Color.blue);
        graphics.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
    }
}
