package menu.elements.other;

import lab2.Forest;
import lab2.Grass;
import menu.Menu;

public class KillGrassElement extends MenuElement {
    private Grass mGrass = null;
    private SwitchMenuElement mElement = null;
    private Menu mOwnerMenu = null;

    public KillGrassElement(String name, Grass grass, SwitchMenuElement element, Menu ownerMenu) {
        super(name);
        this.mGrass = grass;
        this.mElement = element;
        this.mOwnerMenu = ownerMenu;
    }

    @Override
    public void execute() {
        if (getConfirmation("remove grass?")) {
            mGrass.killGrass();
            Forest.switchToMenu(mOwnerMenu);
            mOwnerMenu.removeMenuElement(mElement.getId());
            System.out.println("grass removed successfully...");
        }
        else {
            System.out.println("grass remove canceled... ");
        }
    }
}
