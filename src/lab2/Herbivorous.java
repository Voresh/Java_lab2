package lab2;

public class Herbivorous extends Animal {
	private GrassType mEatableType;

	public Herbivorous(int size, GrassType etype) {
		super();
		this.mSize = size;
		mEatableType = etype;
		Forest.addHerbivorousToForest(this);
	}

	public GrassType getEatableType() {
		return mEatableType;
	}

	//TODO добавить поедание деревьев
	@Override
	public void searchForFood() {
		Grass food = Forest.searchForGrassOfType(mEatableType);
		if (food != null) {
			System.out.println("grass of type " + food.getType() + " eaten");
			food.killGrass();
		} else {
			System.out.println("no food found");
		}
	}

	@Override
	public void killAnimal() {
		Forest.removeHerbivorousFromForest(mId);
	}
}
