package lab2;

public class Tree extends Plant {
    private TreeType mType;
    private int mId;

    public TreeType getType() {
        return mType;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public Tree(TreeType type) {
        super();
        this.mType = type;
        Forest.addTreeToForest(this);
    }

    public void killTree() {
        Forest.removeTreeFromForest(mId);
    }
}
