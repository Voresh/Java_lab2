package menu.elements.other;

import lab2.Forest;
import menu.Menu;

/**
 * Created by v on 26.09.16.
 */
public class SwitchMenuElement extends MenuElement {
    private Menu mTarget;

    public SwitchMenuElement(String name, Menu target) {
        super(name);
        this.mTarget = target;
    }

    public SwitchMenuElement(Menu target) {
        this.mTarget = target;
    }

    @Override
    public void execute() {
        Forest.switchToMenu(mTarget);
    }
}
