package menu.elements.other;

import lab2.Forest;
import lab2.Tree;
import menu.Menu;

/**
 * Created by v on 27.09.16.
 */
public class KillTreeElement extends MenuElement {
    private Tree tree = null;
    private SwitchMenuElement element = null;
    private Menu ownerMenu = null;

    public KillTreeElement(String name, Tree tree, SwitchMenuElement element, Menu ownerMenu) {
        super(name);
        this.tree = tree;
        this.element = element;
        this.ownerMenu = ownerMenu;
    }

    @Override
    public void Execute() {
        tree.killTree();
        Forest.SwitchToMenu(ownerMenu);
        ownerMenu.RemoveMenuElement(element.getId());
    }
}