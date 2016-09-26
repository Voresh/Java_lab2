package menu;

import java.util.Scanner;

public class MenuManager {
    private static Menu currentMenu;

    public static void main(String[] args) {
        currentMenu = new Menu();

        Menu subMenu = new Menu();
        subMenu.AddMenuElement(new PredatorCreatorElement("create predator"));
        subMenu.AddMenuElement(new SwitchMenuElement("back", currentMenu));
        currentMenu.AddMenuElement(new SwitchMenuElement("create animal", subMenu));

        currentMenu.AddMenuElement(new ExitElement());
        getUserInput();
    }

    public static void SwitchToMenu(Menu menu) {
        currentMenu = menu;
    }

    private static void getUserInput() {
        Scanner scanner  = new Scanner(System.in);
        while (currentMenu != null) {
            System.out.println("====================");
            currentMenu.printMenuElements();
            System.out.print("enter your choice: ");
            if (scanner.hasNextInt()) {
                currentMenu.executeElement(scanner.nextInt() - 1);
            } else {
                System.out.println("not int... exit");
                System.exit(0);
            }
        }
    }
}
