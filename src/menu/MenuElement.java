package menu;


/**
 * Created by vl on 9/21/2016.
 *
 */
public class MenuElement {
    protected String name = "default";

    public MenuElement(String name) {
        this.name = name;
        Menu.AddMenuElement(this);
    }

    public MenuElement() {
        Menu.AddMenuElement(this);
    }

    public String getName() {
        return name;
    }

    public void Execute() {

    }
}
