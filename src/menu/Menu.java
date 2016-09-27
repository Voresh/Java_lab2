package menu;

import menu.elements.other.ExitElement;
import menu.elements.other.MenuElement;
import menu.elements.other.SwitchMenuElement;

import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuElement> elements = new ArrayList<MenuElement>();
    private String name = "default";

    private String description = "";

    private Menu lastMenu = null;
    private MenuElement exitElement = null;

    private int currentIndex = 0;

    public Menu() {
        createExitElement();
    }

    public Menu(String name) {
        this.name = name;
        createExitElement();
    }

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
        createExitElement();
    }

    public Menu(String name, Menu lastMenu) {
        this.name = name;
        this.lastMenu = lastMenu;
        createExitElement();
    }

    public Menu(String name, String description, Menu lastMenu) {
        this.name = name;
        this.description = description;
        this.lastMenu = lastMenu;
        createExitElement();
    }

    public String getDescription() {
        return description;
    }

    public void createExitElement() {
        if (lastMenu != null) {
            exitElement = new SwitchMenuElement("back", lastMenu);
        } else {
            exitElement = new ExitElement();
        }
    }

    public String getName() {
        return name;
    }

    public void AddMenuElement(MenuElement element) {
        elements.add(element);
        element.setId(currentIndex);
        currentIndex++;
    }

    public void RemoveMenuElement(int id) {
        for(int i=0; i < elements.size(); i++) {
            if (elements.get(i).getId() == id)
            {
                elements.remove(i);
            }
        }
    }

    public void printMenuElements() {
        int i = 1;
        for (MenuElement element : elements) {
            System.out.println(i + ") " + element.getName());
            i++;
        }
        System.out.println(i + ") " + exitElement.getName());
    }

    public void executeElement(int id) {
        if (id < elements.size()) {
            elements.get(id).Execute();
        } else {
            if (id == elements.size()) {
                exitElement.Execute();
            } else {
                System.out.println("no such variant... canceled");
            }
        }
    }
}
