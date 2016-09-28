package menu.elements.other;

import lab2.Forest;

/**
 * Created by v on 28.09.16.
 */
public class LoadFromDataBaseElement extends  MenuElement {

    public LoadFromDataBaseElement(String name) {
        super(name);
    }

    @Override
    public void Execute() {
        Forest.loadForestDateFromDataBase();
    }
}
