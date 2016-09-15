package lab2;

public class Predator extends Animal {
	
	public Predator(int size) {
		super();
		this.mSize = size;
	}
	
	@Override
	public void SearchForFood() {
		System.out.println("Animal of size " + mSize + ", is searching for food");
		Animal food = Forest.SearchForAnimalsWithSizeLess(mSize);
		if (food != null) {
			System.out.println("Kill " + food.getId());
			food.KillAnimal();
		} else {
			System.out.println("No food");
		}
	}
}
