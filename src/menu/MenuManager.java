package menu;

import java.util.Scanner;

public class MenuManager {
    private static Menu currentMenu;

    public static void main(String[] args) {
        Menu mainMenu = new Menu("main");
        currentMenu = mainMenu;

        Menu animalMenu = new Menu("animals");
        Menu predatorMenu = new Menu("predators");
        predatorMenu.AddMenuElement(new PredatorCreatorElement("create predator"));
        predatorMenu.AddMenuElement(new SwitchMenuElement("back", animalMenu));
        Menu herbivorousMenu = new Menu("herbivorous");
        herbivorousMenu.AddMenuElement(new HerbivorousCreatorElement("create herbivorous"));
        herbivorousMenu.AddMenuElement(new SwitchMenuElement("back", animalMenu));

        animalMenu.AddMenuElement(new SwitchMenuElement("predators", predatorMenu));
        animalMenu.AddMenuElement(new SwitchMenuElement("herbivorous", herbivorousMenu));
        animalMenu.AddMenuElement(new SwitchMenuElement("back", mainMenu));

        currentMenu.AddMenuElement(new SwitchMenuElement("animals", animalMenu));

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
            System.out.println("| Menu: " + currentMenu.getName()+" |");
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
