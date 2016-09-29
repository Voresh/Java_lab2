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

    @Override //добавить подтверждение удаления
    public void execute() {
        /*System.out.println("remove mGrass? [y/n]: ");
        Scanner scanner  = new Scanner(System.in);

        if (scanner.hasNext())
        {

            if (scanner.next() == "y") {
                mGrass.killGrass();
                Forest.RemoveGrassMenu(menu);
                System.out.println("mGrass removed succesfully...");
            } else if (scanner.next() == "n") {
                System.out.println("canceled... ");
            } else {
                System.out.println("incorrect input... canceled");
            }

        }*/
        mGrass.killGrass();
        Forest.switchToMenu(mOwnerMenu);
        mOwnerMenu.removeMenuElement(mElement.getId());
    }
}
