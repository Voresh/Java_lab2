package menu.elements.other;

import lab2.Forest;
import lab2.Herbivorous;
import menu.Menu;

/**
 * Created by v on 27.09.16.
 */
public class KillHerbivorousElement extends MenuElement {
    private Herbivorous mHerbivorous = null;
    private SwitchMenuElement mElement = null;
    private Menu mOwnerMenu = null;

    public KillHerbivorousElement(String name, Herbivorous herbivorous, SwitchMenuElement element, Menu ownerMenu) {
        super(name);
        this.mHerbivorous = herbivorous;
        this.mElement = element;
        this.mOwnerMenu = ownerMenu;
    }

    @Override
    public void execute() {
        if (getConfirmation("remove herbivorous?")) {
            mHerbivorous.killAnimal();
            Forest.switchToMenu(mOwnerMenu);
            mOwnerMenu.removeMenuElement(mElement.getId());
            System.out.println("herbivorous removed successfully...");
        }
        else {
            System.out.println("herbivorous remove canceled... ");
        }
    }
}
