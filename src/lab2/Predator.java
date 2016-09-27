package lab2;

public class Predator extends Animal {
	
	public Predator(int size) {
		super();
		this.mSize = size;
		Forest.addPredatorToForest(this);
	}
	
	@Override
	public void searchForFood() {
		Animal food = Forest.searchForAnimalsWithSizeLess(mSize);
		if (food != null) {
			System.out.println("animal of size " + food.getSize() + " eaten");
			food.killAnimal();
		} else {
			System.out.println("no food found");
		}
	}

	@Override
    public void killAnimal() {
        Forest.removePredatorFromForest(mId);
    }
}
