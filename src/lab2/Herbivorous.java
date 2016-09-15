package lab2;

public class Herbivorous extends Animal {
	private GrassType mEatableType;

	public Herbivorous(int size, GrassType etype) {
		super();
		this.mSize = size;
		mEatableType = etype;
	}
	
	@Override
	public void SearchForFood() {
		System.out.println("Animal eating " + mEatableType.toString() + ", is searching for food");
		Grass food = Forest.SearchForGrassOfType(mEatableType);
		if (food != null) {
			System.out.println("Kill " + food.getId());
			food.KillGrass();
		} else {
			System.out.println("No food");
		}
	}
}
