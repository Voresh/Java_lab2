package menu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by vl on 9/21/2016.
 *
 */
public class Menu {
    private static ArrayList<MenuElement> elements = new ArrayList<MenuElement>();

    public static void main(String[] args) {
        new MenuElement("Add new predator");
        new ExitElement("Exit");
        GetUserInput();
    }

    public static void GetUserInput() {
        Scanner scanner  = new Scanner(System.in);
        while (true) {
            clearScreen();
            ShowMenuElements();
            if (scanner.hasNextInt()) {
                int inputInt = scanner.nextInt() - 1;
                if( inputInt < elements.size())
                    elements.get(inputInt).Execute();
            } else {
                System.out.println("not int");
            }
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void AddMenuElement(MenuElement element) {
        elements.add(element);
    }

    public static void ShowMenuElements(){
        int i = 1;
        for (MenuElement element : elements) {
            System.out.println(i + "} " + element.getName());
            i++;
        }
    }
}
