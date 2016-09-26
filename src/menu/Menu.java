package menu;

import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuElement> elements = new ArrayList<MenuElement>();

    public void AddMenuElement(MenuElement element) {
        elements.add(element);
    }

    public void printMenuElements(){
        int i = 1;
        for (MenuElement element : elements) {
            System.out.println(i + "} " + element.getName());
            i++;
        }
    }

    public void executeElement(int id) {
        if( id < elements.size()) {
            elements.get(id).Execute();
        }
    }
}
