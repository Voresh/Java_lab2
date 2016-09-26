package menu;

import lab2.Predator;

import java.util.Scanner;

public class PredatorCreatorElement extends MenuElement {

    public PredatorCreatorElement(String name) {
        super(name);
    }

    public PredatorCreatorElement() {
    }

    @Override
    public void Execute() {
        System.out.print("enter predator size or random symbol to cancel: ");
        Scanner scanner  = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int inputInt = scanner.nextInt();
            new Predator(inputInt);
            System.out.println("predator of size " + inputInt + " created");
        } else {
            System.out.println("incorrect input... canceled");
        }
    }
}
