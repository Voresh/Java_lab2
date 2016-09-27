package menu;

import lab2.Forest;

/**
 * Created by v on 26.09.16.
 */
public class SwitchMenuElement extends MenuElement {
    private Menu target;

    public SwitchMenuElement(String name, Menu target) {
        super(name);
        this.target = target;
    }

    public SwitchMenuElement(Menu target) {
        this.target = target;
    }

    @Override
    public void Execute() {
        Forest.SwitchToMenu(target);
    }
}
