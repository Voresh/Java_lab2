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
		System.out.println("Animal eating " + mEatableType.toString() + ", is searching for food");
		Grass food = Forest.searchForGrassOfType(mEatableType);
		if (food != null) {
			System.out.println("Kill " + food.getId());
			food.killGrass();
		} else {
			System.out.println("No food");
		}
	}

	@Override
	public void killAnimal() {
		Forest.removeHerbivorousFromForest(mId);
	}
}
