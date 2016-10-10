package menu.elements.creators;

import lab2.Predator;
import menu.elements.other.MenuElement;

import java.util.Scanner;

public class PredatorCreatorElement extends MenuElement {
    private int minSize = 1;
    private int maxSize = 1000;

    public PredatorCreatorElement(String name) {
        super(name);
    }

    public PredatorCreatorElement() {
    }

    @Override
    public void execute() {
        System.out.print("enter predator size or random symbol to cancel: ");
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int size = scanner.nextInt();
            if ((size > maxSize) || (size < minSize)) {
                System.out.println("incorrect size... canceled");
                return;
            }
            new Predator(size);
            System.out.println("predator of size " + size + " created");
        } else {
            System.out.println("incorrect input... canceled");
        }
    }
}
