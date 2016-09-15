package lab2;

public abstract class Animal {
	protected int size = 0;
	protected int id = 0;

	public int getSize() {
		return size;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Animal() {
		Forest.AddAnimalToForest(this);
	}
	
	public void KillAnimal() {
		Forest.RemoveAnimalFromForest(id);
	}

	public abstract void SearchForFood();
}
