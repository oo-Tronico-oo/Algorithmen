package graph;

import java.util.*;

/** Eine Klasse, die die Datenstruktur eines Graphen repräsentiert. Intern werden
 * hier Graphen durch Adjazenzlisten dargestellt
 * @author ripphausen
 * @version 1.0
 * @param <V> eine Unterklasse der Klasse Vertex fuer Knoten eines Graphen
 * @param <E> eine Unterklasse der Klasse Edge<V> fuer die Kanten eines Graphen
 */
public class Graph <V extends Vertex, E extends Edge<V>> {
	private HashMap<Integer, Integer> vertexIndex;
		// gibt zu einer Knoten-Id den Index an, an dem der Knoten in vertices
		// und die inzidenten Kanten in adjList abgelegt ist
	private ArrayList<V> vertices;	// Liste mit allen Knoten
	private ArrayList<LinkedList<E>> adjList;
		// Liste mit allen Nachbarlistten zu allen Knoten

	/**
	 * Generiert einen leeren Graph
	 */
	public Graph() {
		vertexIndex = new HashMap<Integer, Integer>();
		vertices = new ArrayList<V> ();
		adjList = new ArrayList<LinkedList<E>> ();
	}

	/**
	 * Generiert einen leeren Graph, der initial n Knoten enthalten soll
	 * @param n Anzahl der Knoten
	 */
	public Graph(int n) {
		vertexIndex = new HashMap<Integer, Integer>((int) Math.round(Math.ceil(n * 1.25)));
			// fuer Effizienz: Hashtabelle etwas groesser anlegen als benoetigt
		vertices = new ArrayList<V> ();
		adjList = new ArrayList<LinkedList<E>> ();
	}

	/**
	 * Generiert einen Graph aus der uebergebenen Knoten- und Kantenmenge
	 * @param vertexset die Knotenmenge des Graphen (Knoten-id's muessen paarweise
	 * verschieden sein)
	 * @param edgeset die Kantenmenge des Graphen
	 */
	public Graph(Collection<V> vertexset, Collection<E> edgeset) {
		int n = vertexset.size();
		vertexIndex = new HashMap<Integer, Integer>((int) Math.round(Math.ceil(n * 1.25)));
		vertices = new ArrayList<V> (n);
		adjList = new ArrayList<LinkedList<E>> (n);

		// Fuer jeden Knoten leere Adjazenzliste anlegen
		for (int i = 0; i < n; i++) {
			adjList.add(new LinkedList<E>());
		}

		// Fuer jeden Knoten: Knoten in Knotenliste einfuegen;
		// Index des Knotens in der Knotenliste in die HashMap ablegen
		int i = 0;
		for (V v : vertexset) {
			Integer idx = vertexIndex.put(v.getId(), i); // Knoten f�r Index-Findung in Hashmap einf�gen
			if (idx != null) throw new RuntimeException("Doppelte Knoten-ID");
			vertices.add(v);	// Knoten in Knotenliste einf�gen
			i++;
		}

		// Jede Kante e = (a,b) in die Adjazenzliste des Knoten a einfuegen
		for (E e: edgeset) {
			Vertex a = e.getVertexA();
			Vertex b = e.getVertexB();
			Integer IIndA = vertexIndex.get(a.getId());
			if (IIndA== null)
				throw new RuntimeException("Knoten a der Kante ex. nicht");
			Integer IIndB = vertexIndex.get(b.getId());
			if (IIndB== null)
				throw new RuntimeException("Knoten b der Kante ex. nicht");

			int indA = IIndA;
			LinkedList<E> neighbours = adjList.get(indA);
			neighbours.add(e);
		}
	}


	/**
	 * Bestimmt die Anzahl der Knoten
	 * @return die Anzahl der Knoten des Graphen
	 */
	public int getNumberVertices() {
		return vertexIndex.size();
	}

	/**
	 * Bestimmt den Knoten mit gegebener ID
	 * @param id eine ID
	 * @return Knoten mit dieser ID, falls existiert; null sonst
	 */
	public V getVertex(int id) {
		Integer idx = vertexIndex.get(id);
		if (idx != null) {
			V v = vertices.get(idx);
			return v;
		}
		else // Knoten mit vorgeg. ID ex. nicht
			return null;
	}

	/**
	 * Bestimmt die Menge aller Knoten
	 * @return Menge der Knoten des Graphen
	 */
	public Collection<V> getVertices() {
		ArrayList<V> vertices = new ArrayList<V> (this.getNumberVertices());
		for (V v : this.vertices) {
			if (v != null) {
				vertices.add(v);
			}
		}
		return vertices;
	}

	/**
	 * Bestimmt die Menge aller Kanten
	 * @return Menge der Kanten des Graphen
	 */
	public Collection<E> getEdges() {
		ArrayList<E> edges = new ArrayList<E>();
		for (LinkedList<E> nachbarn : adjList) {
			for (E e: nachbarn) {
				edges.add(e);
			}
		}
		return edges;
	}

	/**
	 * Bestimmt alle Nachbarn eines Knotens
	 * @param v der Knoten
	 * @return alle mit diesem Knoten adjazenten Knoten - die Nachbarn
	 */
	public Collection<V> getNeighbours(V v) {
		int id = v.getId();
		return getNeighbours(id);
	}

	/**
	 * Bestimmt alle Nachbarn eines Knotens gegeben durch eine id
	 * @param id ID eines Knotens
	 * @return die Menge aller Nachbarn eines Knotens,
	 * falls dieser Knoten existiert; null sonst
	 */
	public Collection<V> getNeighbours(int id) {
		LinkedList<V> neighbours = new LinkedList<V>();
		Integer IIndex = vertexIndex.get(id);
		if (IIndex == null) return null;
		int index = IIndex;
		LinkedList<E>	neighbourEdges = adjList.get(index);
		for (E e : neighbourEdges) {
			V b = e.getVertexB();
			if (b.getId() == id) {
				// kann nur bei ungerichteten Graphen auftreten, bei
				// denen ungerichtete Kante nur einmal erzeugt wird und
				// nicht durch zwei gerichtete ersetzt wird;
				// zweiten Endknoten bestimmen; dieser ist der Nachbar
				b = e.getVertexA();
			}
			neighbours.addLast(b);
		}
		return neighbours;
	}

	/**
	 * Bestimmt alle mit einem Knoten inzidenten Kanten
	 * @param v der Knoten
	 * @return eine Menge mit allen zu v inzidenten Kanten
	 */
	public Collection<E> getIncidentEdges(V v) {
		int id = v.getId();
		return getIncidentEdges(id);
	}

	/**
	 * Bestimmt alle mit einem Knoten - gegeben durch eine ID - inzidenten Kanten
	 * @param id eines Knotens
	 * @return eine Menge mit allen zu Knoten mit ID id inzidenten Kanten,
	 *         falls dieser existiert; null sonst
	 */
	public Collection<E> getIncidentEdges(int id) {
		ArrayList<E> edges = new ArrayList<E> ();
		Integer IIdx = vertexIndex.get(id);
		if (IIdx == null) // existiert kein Knoten mit id
			return null;

		for (E e : adjList.get(IIdx)) {
			edges.add(e);
		}
		return edges;
	}

	/**
	 * Hinzuf�gen eines Knotens
	 * @param v Knoten, der dem Graph hinzugef�gt werden soll
	 * @return true g.d.w. Knoten hinzugefuegt werden konnte, d.h. noch
	 * 			kein Knoten mit derselben ID wie v existiert;
	 */
	public boolean addVertex(V v) {
		// Testen, ob Knoten mit derselben id wie v schon vorhanden
		boolean uniqueId = ! vertexIndex.containsKey(v.getId());
		if (!uniqueId) { // Abbruch: Knoten wird nicht eingefuegt
			return false;
		}
		// Knoten in Knotenliste hinzufuegen
		vertices.add(v);
		// Leere Adjazenzliste hinzufuegen
		adjList.add(new LinkedList<E>());
		int index = vertices.size() - 1;
		// Index in Indexliste ablegen
		vertexIndex.put(v.getId(), index);

		return true;
	}

	/**
	 * Hinzuf�gen einer Kante, wobei die Knoten dieser Kante schon im
	 * Graph vorhanden sein m�ssen
	 * @param e die hinzuzuf�gende Kante
	 * @return true, g.d.w. Kante hinzugef�gt werden konnte
	 */
	public boolean addEdge(E e) {
		V a = e.getVertexA();
		if (a == null)
			return false;
		V b = e.getVertexB();
		if (b == null)
			return false;
		int idA = a.getId();
		int idB = a.getId();
		/* Ueberpruefen, ob Knoten in Graph */
		Integer idxA = vertexIndex.get(idA);
		Integer idxB = vertexIndex.get(idB);
		if (idxA == null || idxB == null) // Knoten a oder b nicht in G
			return false;
		return adjList.get(vertexIndex.get(idA)).add(e);
	}

	/**
	 * Entfernung eines Knotens v mit allen incidenten Kanten
	 * @param v zu entfernender Knoten
	 * @return true, g.d.w. Knoten entfernt werden konnte
	 */
	public boolean removeVertex(V v) {
		// zun�chst alle Kanten mit Knoten v als Startknoten entfernen
		Integer IIndex = vertexIndex.get(v.getId());
		if (IIndex == null) {
			return false;
		}
		int index = IIndex;
		adjList.get(index).clear();

		// dann alle Kanten mit Knoten v als Endknoten entfernen
		for (LinkedList<E> list: adjList) {
			ListIterator<E> it = list.listIterator();
			while (it.hasNext()) {
				Edge e = it.next();
				if (((Vertex)e.getVertexB()).getId() == v.getId()){
					it.remove();
					break;
				}
			}
		}

		// Knoten v aus ArrayList entfernen, indem Eintrag in ArrayList auf null
		// gesetzt wird; ansonsten w�rden Indices anderer Knoten nicht mehr stimmen.
		vertices.set(index, null);

		// nun Knoten aus Indexliste entfernen
		vertexIndex.remove(v.getId());
		return true;
	}

	/**
	 * Entfernung einer Kante
	 * @param e die zu entfernende Kante
	 * @return true g.d.w. Kante entfernt werden konnte
	 */
	public boolean removeEdge(E e) {
		Vertex a = e.getVertexA();
		Vertex b = e.getVertexB();

		// Achtung: Modifikationen sind notwendig, wenn bei ungerichteten
		// Graphen eine Kante {a, b} nur einmal existiert und sowohl in
		// der Nachbarliste von a als auch in der von b abgelegt wurde
		int aId = a.getId();
		Integer aIdx = vertexIndex.get(aId);
		if (aIdx == null) {
			return false; // Knoten konnte nicht gefunden werden
		}

		// f�r alle Kanten der Nachbarliste von a
		ListIterator<E> it = adjList.get(aIdx).listIterator();
		while (it.hasNext()) {
			Edge le = it.next();
			if (((Vertex)le.getVertexB()).getId() == b.getId()){
				// gesuchte Kante gefunden
				it.remove();
				return true;
			}
		}
		return false;
	}

	public String toString() {
		StringBuffer s = new StringBuffer("Graph mit " + vertexIndex.size() + "Knoten\n");
		int i = 0;
		for (V v: vertices) {
			if (v != null) {
				s.append(v + ": ");
				for (E e: adjList.get(i)) {
					s.append(e.getVertexB() + " ");
				}
				s.append("\n");
			}
			i++;
		}
		return new String(s);
	}
}


