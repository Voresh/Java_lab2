package lab2;

enum GrassType { 
	BLUEBERRY, //черника
	STRAWBERRY, //земляника
	RASPBERRY //малина
}

public class Grass extends Plant {
	private GrassType type;

	public Grass(GrassType type) {
		super();
		this.type = type;
		Forest.AddGrassToForest(this);
	}

	public GrassType getType() {
		return type;
	}
}
