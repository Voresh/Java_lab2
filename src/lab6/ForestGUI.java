package lab6;

import lab2.GrassType;
import lab2.Herbivorous;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ForestGUI extends JFrame {
    private int width;
    private int height;
    private JFrame thisFrame = this;

    public ForestGUI(String s,int width, int height) throws HeadlessException {
        super(s);
        this.width = width;
        this.height = height;

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setLayout(null);
        add(mainPanel);
        createGUI(mainPanel);
        setVisible(true);
    }

    private void createGUI(Container container) {
        JLabel sizeLabel = new JLabel("Size: ");
        sizeLabel.setBounds(68,10,50,20);
        JTextField sizeField= new JTextField(20);
        sizeField.setBounds(108,10,100,20);
        JLabel sizeTip = new JLabel("Wrong size!");
        sizeTip.setBounds(218,10,100,20);
        sizeTip.setForeground(Color.red);
        JLabel eatableTypeLabel = new JLabel("Eatable type: ");
        eatableTypeLabel.setBounds(10,30,100,20);
        JComboBox eatableType = new JComboBox(getEatableTypesNames());
        eatableType.setBounds(108,30,99,20);
        JButton addHerbivorousButton = new JButton("Add");
        addHerbivorousButton.setBounds(108,60,99,20);

        container.add(sizeLabel);
        container.add(sizeField);
        container.add(sizeTip);
        sizeTip.setVisible(false);
        container.add(eatableTypeLabel);
        container.add(eatableType);
        container.add(addHerbivorousButton);

        addHerbivorousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isNumeric(sizeField.getText())) {
                    int size = Integer.parseInt(sizeField.getText());
                    if (size < 1000) {
                        new Herbivorous(size, GrassType.values()[eatableType.getSelectedIndex()]);
                        JOptionPane.showMessageDialog(thisFrame, "Herbivorous created.");
                    }
                    else {
                        sizeTip.setVisible(true);
                    }
                }
                else
                {
                    sizeTip.setVisible(true);
                }
            }
        });

        sizeField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                sizeTip.setVisible(false);
            }
        });
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

    public boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}