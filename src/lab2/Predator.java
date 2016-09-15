package lab2;

enum PredatorType { 
	BEAR, //медведь
	WOLF, //волк
	FOX //лиса
}

public class Predator extends Animal {
	private PredatorType type;
	
	public Predator(PredatorType type, int size) {
		super();
		this.type = type;
		this.size = size;
	}
	
	public PredatorType getType() {
		return type;
	}

	@Override
	public void SearchForFood() {
	}
}
