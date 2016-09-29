package menu.elements.other;

import lab2.Forest;

/**
 * Created by vl on 9/21/2016.
 */
public class ExitElement extends MenuElement {

    public ExitElement(String name) {
        super(name);
    }

    public ExitElement() {
        mName = "exit";
    }

    @Override
    public void execute() {
        //System.exit(0);
        Forest.switchToMenu(null);
    }
}
