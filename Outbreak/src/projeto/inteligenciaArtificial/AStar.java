package projeto.inteligenciaArtificial;

import java.util.List;

public class AStar {

	public int map[][];
	private final byte TILE = 55;

	int sizeX;
	int sizeY;

	public AStar(int x, int y) {
		map = new int[x][y];
		sizeX = x * TILE;
		sizeY = y * TILE;

		System.out.println("x:" + sizeX + " y:" + sizeY);
	}

	public int startX;
	public int startY;

	public int targetX;
	public int targetY;

	public List<Node> search(int startx, int starty, int targetx, int targety,
			boolean ignoreEdges) {
		Node startNode = new Node(startx, starty);
		Node targetNode = new Node(targetx, targety);
		return search(startNode, targetNode, ignoreEdges);
	}

	private List<Node> search(Node startNode, Node targetNode,
			boolean ignoreEdges) {

		return null;
	}
	
	
	/*
	 * private static final int[][] MAP = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, {
	 * 1, 0, 0, 0, 0, 0, 1, 1, 1, 1 }, { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1 }, { 1, 0,
	 * 1, 1, 1, 0, 0, 0, 1, 1 }, { 1, 0, 0, 0, 1, 1, 1, 0, 1, 1 }, { 1, 1, 1, 0,
	 * 1, 1, 1, 0, 0, 0 }, { 1, 0, 1, 0, 0, 0, 0, 0, 1, 0 }, { 1, 0, 1, 1, 1, 1,
	 * 1, 1, 1, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1,
	 * 1, 0 } };
	 */
}
