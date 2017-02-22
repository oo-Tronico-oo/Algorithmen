package graph;

/** Eine Klasse die Kanten eines Graphen repräsentiert
 * 
 * @author ripphausen
 * @version 1.0
 * @param <V> eine Unterklasse der Knotenklasse Vertex zur Repräsentation der Endknoten der Kante
 */
public class Edge <V extends Vertex> {
	private V vertexA;
	private V vertexB;
	private int weight = 1;
	
	public Edge(V vertexA, V vertexB, int weight) {
		super();
		this.vertexA = vertexA;
		this.vertexB = vertexB;
		this.weight = weight;
	}

	public Edge(V a, V b) {
		vertexA = a;
		vertexB = b;
		weight = 1; // Standardgewicht
	}
	public V getVertexA() {
		return vertexA;
	}

	public V getVertexB() {
		return vertexB;
	}	
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String toString() {
		return "(" + vertexA.getId() + "," + vertexB.getId() + "; g:" + this.weight + ")";
	}
}


