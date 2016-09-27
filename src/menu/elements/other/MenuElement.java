package menu.elements.other;


/**
 * Created by vl on 9/21/2016.
 *
 */
public class MenuElement {
    protected String name = "default";

    private int id = 0;

    public MenuElement(String name) {
        this.name = name;
    }

    public MenuElement() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void Execute() {

    }
}
