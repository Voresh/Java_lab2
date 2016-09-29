package menu.elements.other;

import lab2.Forest;
import lab2.Predator;
import menu.Menu;

/**
 * Created by v on 27.09.16.
 */
public class KillPredatorElement extends MenuElement {
    private Predator mPredator = null;
    private SwitchMenuElement mElement = null;
    private Menu mOwnerMenu = null;

    public KillPredatorElement(String name, Predator predator, SwitchMenuElement element, Menu ownerMenu) {
        super(name);
        this.mPredator = predator;
        this.mElement = element;
        this.mOwnerMenu = ownerMenu;
    }

    @Override
    public void execute() {
        mPredator.killAnimal();
        Forest.switchToMenu(mOwnerMenu);
        mOwnerMenu.removeMenuElement(mElement.getId());
    }
}
