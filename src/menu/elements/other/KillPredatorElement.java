package menu.elements.other;

import lab2.Forest;
import lab2.Predator;
import menu.Menu;

/**
 * Created by v on 27.09.16.
 */
public class KillPredatorElement extends  MenuElement{
    private Predator predator = null;
    private SwitchMenuElement element = null;
    private Menu ownerMenu = null;

    public KillPredatorElement(String name, Predator predator, SwitchMenuElement element, Menu ownerMenu) {
        super(name);
        this.predator = predator;
        this.element = element;
        this.ownerMenu = ownerMenu;
    }

    @Override
    public void Execute() {
        predator.killAnimal();
        Forest.SwitchToMenu(ownerMenu);
        ownerMenu.RemoveMenuElement(element.getId());
    }
}
