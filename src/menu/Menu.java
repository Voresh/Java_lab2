package menu;

import menu.elements.other.ExitElement;
import menu.elements.other.MenuElement;
import menu.elements.other.SwitchMenuElement;

import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuElement> mElements = new ArrayList<MenuElement>();
    private String mName = "default";

    private String mDescription = "";

    private Menu mLastMenu = null;
    private MenuElement mExitElement = null;

    private int mCurrentIndex = 0;

    public Menu() {
        createExitElement();
    }

    public Menu(String name) {
        this.mName = name;
        createExitElement();
    }

    public Menu(String name, String description) {
        this.mName = name;
        this.mDescription = description;
        createExitElement();
    }

    public Menu(String name, Menu lastMenu) {
        this.mName = name;
        this.mLastMenu = lastMenu;
        createExitElement();
    }

    public Menu(String name, String description, Menu lastMenu) {
        this.mName = name;
        this.mDescription = description;
        this.mLastMenu = lastMenu;
        createExitElement();
    }

    public String getDescription() {
        return mDescription;
    }

    public void createExitElement() {
        if (mLastMenu != null) {
            mExitElement = new SwitchMenuElement("back", mLastMenu);
        } else {
            mExitElement = new ExitElement();
        }
    }

    public String getName() {
        return mName;
    }

    public void addMenuElement(MenuElement element) {
        mElements.add(element);
        element.setId(mCurrentIndex);
        mCurrentIndex++;
    }

    public void removeMenuElement(int id) {
        for (int i = 0; i < mElements.size(); i++) {
            if (mElements.get(i).getId() == id) {
                mElements.remove(i);
            }
        }
    }

    public void printMenuElements() {
        int i = 1;
        for (MenuElement element : mElements) {
            System.out.println(i + ") " + element.getName());
            i++;
        }
        System.out.println(i + ") " + mExitElement.getName());
    }

    public void executeElement(int id) {
        if (id < mElements.size()) {
            mElements.get(id).execute();
        } else {
            if (id == mElements.size()) {
                mExitElement.execute();
            } else {
                System.out.println("no such variant... canceled");
            }
        }
    }
}
