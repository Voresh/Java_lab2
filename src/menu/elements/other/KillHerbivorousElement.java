package menu.elements.other;

import lab2.Forest;
import lab2.Herbivorous;
import menu.Menu;

/**
 * Created by v on 27.09.16.
 */
public class KillHerbivorousElement extends MenuElement {
    private Herbivorous herbivorous = null;
    private SwitchMenuElement element = null;
    private Menu ownerMenu = null;

    public KillHerbivorousElement(String name, Herbivorous herbivorous, SwitchMenuElement element, Menu ownerMenu) {
        super(name);
        this.herbivorous = herbivorous;
        this.element = element;
        this.ownerMenu = ownerMenu;
    }

    @Override
    public void Execute() {
        herbivorous.killAnimal();
        Forest.SwitchToMenu(ownerMenu);
        ownerMenu.RemoveMenuElement(element.getId());
    }
}
