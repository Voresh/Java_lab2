package lab2;

public class Herbivorous extends Animal {
	private GrassType[] eatableType;

	public Herbivorous(int size, GrassType[] etypes) {
		super();
		this.size = size;
		eatableType = etypes;
	}
	
	@Override
	public void SearchForFood() {
		
	}
}
