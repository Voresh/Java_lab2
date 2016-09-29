package menu.elements.other;

import lab2.Forest;
import lab2.Tree;
import menu.Menu;

/**
 * Created by v on 27.09.16.
 */
public class KillTreeElement extends MenuElement {
    private Tree mTree = null;
    private SwitchMenuElement mElement = null;
    private Menu mOwnerMenu = null;

    public KillTreeElement(String name, Tree tree, SwitchMenuElement element, Menu ownerMenu) {
        super(name);
        this.mTree = tree;
        this.mElement = element;
        this.mOwnerMenu = ownerMenu;
    }

    @Override
    public void execute() {
        mTree.killTree();
        Forest.switchToMenu(mOwnerMenu);
        mOwnerMenu.removeMenuElement(mElement.getId());
    }
}