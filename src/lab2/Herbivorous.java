package lab2;

enum HerbivorousType { 
	DEER, //олень
	COW, //корова
	HORSE //лошадь
}

public class Herbivorous extends Animal {
	private HerbivorousType type;
	private GrassType[] eatableType;

	public Herbivorous(HerbivorousType type,int size, GrassType[] etypes) {
		super();
		this.type = type;
		this.size = size;
		eatableType = etypes;
	}
	
	public HerbivorousType getType() {
		return type;
	}
	
	@Override
	public void SearchForFood() {
		
	}
}
