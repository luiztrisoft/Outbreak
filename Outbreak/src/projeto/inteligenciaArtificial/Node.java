package projeto.inteligenciaArtificial;

public class Node {
	
	private int x, y;
	private float f, g, h;
	
	private Node parent;
	private Node next;
	private Node prev;
	
	Node(int x, int y, Node parent, float g) {
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.g = g;
		f = 0;
		h = 0;
	}
	
	public Node(int x, int y) {
		this(x, y, null, 0);
	}

}
