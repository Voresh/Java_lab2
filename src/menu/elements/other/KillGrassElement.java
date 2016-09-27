package menu.elements.other;

import lab2.Forest;
import lab2.Grass;
import menu.Menu;

import java.util.Scanner;

public class KillGrassElement extends MenuElement {
    private Grass grass = null;
    private SwitchMenuElement element = null;
    private Menu ownerMenu = null;

    public KillGrassElement(String name, Grass grass, SwitchMenuElement element, Menu ownerMenu) {
        super(name);
        this.grass = grass;
        this.element = element;
        this.ownerMenu = ownerMenu;
    }

    @Override //добавить подтверждение удаления
    public void Execute() {
        /*System.out.println("remove grass? [y/n]: ");
        Scanner scanner  = new Scanner(System.in);

        if (scanner.hasNext())
        {

            if (scanner.next() == "y") {
                grass.killGrass();
                Forest.RemoveGrassMenu(menu);
                System.out.println("grass removed succesfully...");
            } else if (scanner.next() == "n") {
                System.out.println("canceled... ");
            } else {
                System.out.println("incorrect input... canceled");
            }

        }*/
        grass.killGrass();
        Forest.SwitchToMenu(ownerMenu);
        ownerMenu.RemoveMenuElement(element.getId());
    }
}
