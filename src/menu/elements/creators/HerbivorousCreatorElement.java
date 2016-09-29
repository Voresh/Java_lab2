package menu.elements.creators;

import lab2.GrassType;
import lab2.Herbivorous;
import menu.elements.other.MenuElement;

import java.util.Scanner;

/**
 * Created by v on 26.09.16.
 */
public class HerbivorousCreatorElement extends MenuElement {

    public HerbivorousCreatorElement(String name) {
        super(name);
    }

    public HerbivorousCreatorElement() {
    }

    @Override
    public void execute() {
        System.out.print("enter herbivorous size or random symbol to cancel: ");
        Scanner scanner = new Scanner(System.in);
        int size = 0;
        GrassType eatableType = GrassType.BLUEBERRY;

        if (scanner.hasNextInt()) {
            size = scanner.nextInt();

        } else {
            System.out.println("incorrect input... canceled");
            return;
        }

        printEatableTypes();
        System.out.print("enter herbivorous eatable plant number: ");

        if (scanner.hasNextInt()) {
            int inputInt = scanner.nextInt() - 1;
            if (inputInt < GrassType.values().length) {
                eatableType = GrassType.values()[inputInt];
            } else {
                System.out.println("incorrect input... canceled");
                return;
            }
        } else {
            System.out.println("incorrect input... canceled");
            return;
        }

        new Herbivorous(size, eatableType);
        System.out.println("herbivorous of size " + size + " eating " + eatableType.name().toLowerCase() + " created");
    }

    private void printEatableTypes() {
        int i = 1;
        for (GrassType type : GrassType.values()) {
            System.out.println(i + ") " + type.name().toLowerCase());
            i++;
        }
    }
}
