package lab6;

import lab2.GrassType;

import javax.swing.*;
import java.awt.*;

public class ForestGUI extends JFrame {

    public ForestGUI(String s,int width, int height) throws HeadlessException {
        super(s);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(width, height);
        //setResizable(false);
        JPanel mainPanel = new JPanel(new GridBagLayout());

        JButton addHerbivorousButton = new JButton("Add");
        GridBagConstraints constraints = new GridBagConstraints();

        JTextField sizeField= new JTextField(10);
        JComboBox eatableType = new JComboBox(getEatableTypesNames());
        JLabel eatableTypeLabel = new JLabel("Eatable type: ", SwingConstants.RIGHT);
        JLabel sizeLabel = new JLabel("Size: ", SwingConstants.RIGHT);
        JLabel sizeTip = new JLabel("Wrong size!");
        sizeTip.setForeground(Color.red);

        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;

        mainPanel.add(sizeLabel,constraints);
        mainPanel.add(eatableTypeLabel,constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.CENTER;

        mainPanel.add(sizeField,constraints);
        mainPanel.add(eatableType,constraints);
        mainPanel.add(addHerbivorousButton,constraints);

        constraints.gridx = 2;

        mainPanel.add(sizeTip,constraints);
        sizeTip.setVisible(false);

        mainPanel.setVisible(true);
        add(mainPanel);
        pack();
        setVisible(true);
    }

    public String[] getEatableTypesNames(){
        String[] typesNames = new String[GrassType.values().length];
        int i = 0;
        for (GrassType type : GrassType.values()) {
            typesNames[i] = type.name().toLowerCase();
            i++;
        }
        return  typesNames;
    }
}
