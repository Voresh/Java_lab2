package lab2;

public abstract class Animal {
    protected int mSize = 0;
    protected int mId = 0;

    public int getSize() {
        return mSize;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public Animal() {}

    public void killAnimal() {}

    public abstract void searchForFood();
}
