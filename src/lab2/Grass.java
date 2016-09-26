package lab2;

enum GrassType { 
	BLUEBERRY, //черника
	STRAWBERRY, //земляника
	RASPBERRY //малина
}

public class Grass extends Plant {
	private GrassType mType;
	private int mId;

	public GrassType getType() {
		return mType;
	}
	
	public int getId() {
		return mId;
	}

	public void setId(int id) {
		this.mId = id;
	}

	public Grass(GrassType type) {
		super();
		this.mType = type;
		Forest.addGrassToForest(this);
	}
	
	public void killGrass() {
		Forest.removeGrassFromForest(mId);
	}
}
