package lab2;

public class Predator extends Animal {
	
	public Predator(int size) {
		super();
		this.mSize = size;
		Forest.addPredatorToForest(this);
	}
	
	@Override
	public void searchForFood() {
		System.out.println("Animal of size " + mSize + ", is searching for food");
		Animal food = Forest.searchForAnimalsWithSizeLess(mSize);
		if (food != null) {
			System.out.println("Kill " + food.getId());
			food.killAnimal();
		} else {
			System.out.println("No food");
		}
	}

	@Override
    public void killAnimal() {
        Forest.removePredatorFromForest(mId);
    }
}
