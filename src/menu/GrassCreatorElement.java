package menu;

import lab2.GrassType;
import lab2.Grass;
import java.util.Scanner;

/**
 * Created by v on 27.09.16.
 */
public class GrassCreatorElement extends MenuElement{

    public GrassCreatorElement(String name) {
        super(name);
    }

    public GrassCreatorElement() {
    }

    @Override
    public void Execute() {
        Scanner scanner  = new Scanner(System.in);
        GrassType type = GrassType.BLUEBERRY;

        printGrassTypes();
        System.out.print("enter grass type number: ");

        if (scanner.hasNextInt()) {
            int inputInt = scanner.nextInt() - 1;
            if (inputInt < GrassType.values().length) {
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
