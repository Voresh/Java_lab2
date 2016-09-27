package lab2;

public class Tree extends Plant {
    private TreeType mType;
    private int id;

    public TreeType getType() {
        return mType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tree(TreeType type) {
        super();
        this.mType = type;
        Forest.addTreeToForest(this);
    }

    public void killTree() {
        Forest.removeTreeFromForest(id);
    }
}
