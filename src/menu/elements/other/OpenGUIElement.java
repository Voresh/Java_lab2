package menu.elements.other;

import lab6.ForestGUI;

import javax.swing.*;

public class OpenGUIElement extends MenuElement {
    private ForestGUI currentGUI;

    public OpenGUIElement(String name) {
        super(name);
    }

    @Override
    public void execute() {
        if (currentGUI != null) {
            currentGUI.setVisible(true);
        } else {
            currentGUI = new ForestGUI("Add herbivorous",300,110);
        }
    }
}
