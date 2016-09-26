package lab2;

enum TreeType { 
	OAK, //дуб
	PINE, //сосна
	BIRCH //береза
}

public class Tree extends Plant {
	private TreeType mType;
	
	public Tree(TreeType type) {
		super();
		this.mType = type;
	}
}
