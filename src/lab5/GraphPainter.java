package lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphPainter {
    private JFrame currentFrame;
    private int amount;
    private long[] y;
    private int axisOffset = 20;
    private int arrowOffset = 5;
    private int arrowLength = 10;
    private int axisNameOffset = 15;
    private int scaleInterval = 50;
    private int scaleIntervalAmount = 8;
    private int scaleIntervalOffset = 20;

    public GraphPainter(String windowName,int width, int height) {
        currentFrame = new JFrame(windowName);
        currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentFrame.setSize(width, height);
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
        drawGrid(graphics2D);
        currentFrame.add(new JLabel(new ImageIcon(bufferedImage)));
        currentFrame.pack();
        currentFrame.setVisible(true);
    }

    private void drawAxes(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawLine(axisOffset, currentFrame.getHeight(), axisOffset, axisOffset);
        graphics.drawLine(0, currentFrame.getHeight() - axisOffset, currentFrame.getWidth() - axisOffset, currentFrame.getHeight() - axisOffset);
        graphics.drawLine(axisOffset, axisOffset, axisOffset - arrowOffset, axisOffset + arrowLength);
        graphics.drawLine(axisOffset, axisOffset, axisOffset + arrowOffset, axisOffset + arrowLength);

        graphics.drawLine(currentFrame.getWidth() - axisOffset, currentFrame.getHeight() - axisOffset, currentFrame.getWidth() - axisOffset - arrowLength, currentFrame.getHeight() - axisOffset - arrowOffset);
        graphics.drawLine(currentFrame.getWidth() - axisOffset, currentFrame.getHeight() - axisOffset, currentFrame.getWidth() - axisOffset - arrowLength, currentFrame.getHeight() - axisOffset + arrowOffset);

        graphics.drawString("x",currentFrame.getWidth() - axisOffset - axisNameOffset, currentFrame.getHeight() - axisOffset + axisNameOffset);
        graphics.drawString("t",axisOffset - axisNameOffset, axisOffset + axisNameOffset);
        graphics.drawString("0", axisOffset - axisNameOffset,  currentFrame.getHeight() - axisOffset + axisNameOffset);
    }

    private void drawGrid(Graphics graphics) {
        int centerX = axisOffset;
        int centerY = currentFrame.getHeight() - axisOffset;
        for (int i = 1; i < scaleIntervalAmount + 1; i++) {
            graphics.drawLine(centerX - 4, centerY - scaleInterval*i, centerX + 4, centerY - scaleInterval*i);
            graphics.drawString(Integer.toString(5*i), centerX - scaleIntervalOffset, centerY - scaleInterval*i);
        }
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
