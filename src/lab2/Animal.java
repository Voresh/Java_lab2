package lab2;

public abstract class Animal {
	protected int size = 0;

	public Animal() {
		Forest.AddAnimalToForest(this);
	}

	public int getSize() {
		return size;
	}
	
	public abstract void SearchForFood();
}
