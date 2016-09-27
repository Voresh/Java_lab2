package menu.elements.creators;

import lab2.Tree;
import lab2.TreeType;
import menu.elements.other.MenuElement;

import java.util.Scanner;

/**
 * Created by v on 27.09.16.
 */
public class TreeCreatorElement extends MenuElement {

    public TreeCreatorElement(String name) {
        super(name);
    }

    public TreeCreatorElement() {
    }

    @Override
    public void Execute() {
        Scanner scanner  = new Scanner(System.in);
        TreeType type = TreeType.BIRCH;

        printTreeTypes();
        System.out.print("enter grass type number: ");

        if (scanner.hasNextInt()) {
            int inputInt = scanner.nextInt() - 1;
            if (inputInt < TreeType.values().length) {
                type = TreeType.values()[inputInt];
            } else {
                System.out.println("incorrect input... canceled");
                return;
            }
        } else {
            System.out.println("incorrect input... canceled");
            return;
        }

        new Tree(type);
        System.out.println("tree of type " + type.name().toLowerCase() + " created");
    }

    private void printTreeTypes() {
        int i = 1;
        for (TreeType type : TreeType.values()) {
            System.out.println(i + ") " + type.name().toLowerCase());
            i++;
        }
    }
}

/*
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
 */