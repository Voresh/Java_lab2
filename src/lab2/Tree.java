package lab2;

enum TreeType { 
	OAK, //дуб
	PINE, //сосна
	BIRCH //береза
}

public class Tree extends Plant {
	private TreeType type;
	
	public Tree(TreeType type) {
		super();
		this.type = type;
	}

	public TreeType getType() {
		return type;
	}
}
