package lab2;

public class Predator extends Animal {
	
	public Predator(int size) {
		super();
		this.size = size;
	}
	
	@Override
	public void SearchForFood() {
		Animal food = Forest.SearchForAnimalsWithSizeLess(size);
		if (food != null) {
			System.out.println("Kill " + food.getId());
			food.KillAnimal();
		} else {
			System.out.println("No food");
		}
	}
}
