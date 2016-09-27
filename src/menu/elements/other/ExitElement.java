package menu.elements.other;

import lab2.Forest;

/**
 * Created by vl on 9/21/2016.
 *
 */
public class ExitElement extends MenuElement {

    public ExitElement(String name) {
        super(name);
    }

    public ExitElement() {
        name = "exit";
    }

    @Override
    public void Execute() {
        //System.exit(0);
        Forest.SwitchToMenu(null);
    }
}
