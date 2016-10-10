package menu.elements.other;

import java.util.Scanner;

public class MenuElement {
    protected String mName = "default";

    private int id = 0;

    public MenuElement(String name) {
        this.mName = name;
    }

    public MenuElement() {
    }

    public String getName() {
        return mName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void execute() {

    }

    public static boolean getConfirmation(String question) {
        System.out.print(question.concat(" [y/n]: "));
        Scanner scanner  = new Scanner(System.in);

        if (scanner.hasNext())
        {
            String answer = scanner.next();
            if (answer.equals("y")) {
                return true;
            } else {
                return false;
            }

        }
        return  false;
    }
}
