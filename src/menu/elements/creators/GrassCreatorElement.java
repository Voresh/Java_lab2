package menu.elements.creators;

import lab2.GrassType;
import lab2.Grass;
import menu.elements.other.MenuElement;

import java.util.Scanner;

public class GrassCreatorElement extends MenuElement {

    public GrassCreatorElement(String name) {
        super(name);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        GrassType type;

        printGrassTypes();
        System.out.print("enter grass type number: ");

        if (scanner.hasNextInt()) {
            int inputInt = scanner.nextInt() - 1;
            if ((inputInt < GrassType.values().length) && (inputInt >= 0)) {
                type = GrassType.values()[inputInt];
            } else {
                System.out.println("incorrect input... canceled");
                return;
            }
        } else {
            System.out.println("incorrect input... canceled");
            return;
        }

        new Grass(type);
        System.out.println("grass of type " + type.name().toLowerCase() + " created");
    }

    private void printGrassTypes() {
        int i = 1;
        for (GrassType type : GrassType.values()) {
            System.out.println(i + ") " + type.name().toLowerCase());
            i++;
        }
    }
}
