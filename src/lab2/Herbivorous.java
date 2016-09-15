package lab2;

public class Herbivorous extends Animal {
	private GrassType eatableType;

	public Herbivorous(int size, GrassType etype) {
		super();
		this.size = size;
		eatableType = etype;
	}
	
	@Override
	public void SearchForFood() {
		System.out.println("Animal eating " + eatableType.toString() + ", is searching for food");
		Grass food = Forest.SearchForGrassOfType(eatableType);
		if (food != null) {
			System.out.println("Kill " + food.getId());
			food.KillGrass();
		} else {
			System.out.println("No food");
		}
	}
}
