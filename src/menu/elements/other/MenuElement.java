package menu.elements.other;


/**
 * Created by vl on 9/21/2016.
 */
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
}
