package menu.elements.other;

import lab2.Forest;

public class LoadFromDataBaseElement extends MenuElement {

    public LoadFromDataBaseElement(String name) {
        super(name);
    }

    @Override
    public void execute() {
        Forest.loadForestDateFromDataBase();
    }
}
