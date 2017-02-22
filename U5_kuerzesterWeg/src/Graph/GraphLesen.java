package graph;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/** Klasse mit Funktionen zum Erzeugen von Graphen anhand von Dateien
 *
 * @author ripphausen
 * @version 1.0
 */
public class GraphLesen {
	/**
	 Funktion FileToGraphArray liest Werte aus Datei 'dat' und erzeugt ein Array, in dem
	 der Graph abgelegt ist.

	 @param dat eine Datei mit int-Werten in folgendem Format:
	 	1. Zeile: Anzahl der Knoten (n)
	 	2. Zeile: Anzahl der Kanten (m)
	 	3. Zeile - (m+2)-te Zeile: Endknoten der Kante durch ein Leerzeichen getrennt
	 	Hinweis: die Knoten m�ssen von 0 bis n-1 durchnummeriert, sonst Ausnahme
	 @return Array mit dem aus 'dat' gelesenen Graphen in folgendem Format
	 	Jede Komponente dieses Arrays besteht aus einem Array mit 2 int-Werten
	 	Komponente 0 enthält die Knotenanzahl n (Index 0) und die Kantenanzahl m (Index 1)
	 	Komponenten 1 .. m enthalten die Kanten {u, v} bzw. bei gerichteten Graphen (u,v)
	 		Knoten u ist dabei in Komponente mit Index 0, Knoten v in Komponente mit Index 1
	 		abgelegt.
	 */
	private static int [][] FileToGraphArray(String dat) {
		int [][] A = null;
		FileInputStream fis = null;
		try {
		  fis = new FileInputStream(dat);
		}
		catch ( Exception e) {
			System.out.println(dat + " konnte nicht geoeffnet werden");
			System.out.println(e.getMessage());
		}
		try {
		      InputStreamReader isr   = new InputStreamReader(fis);
		      BufferedReader    br = new BufferedReader   (isr);

		      // Knotenanzahl lesen
		      String einZeile;
		      einZeile = br.readLine();
		      int n = new Integer(einZeile);

		      // Kantenanzahl lesen
		      einZeile = br.readLine();
		      int m = new Integer(einZeile);
		      A = new int [m+1][2];

		      // Knoten- und Kantenanzahl -> Array
		      A[0][0] = n;
		      A[0][1] = m;

		      // Kanten lesen
		      for (int i = 1; i <= m; i++) {
		    	  einZeile = br.readLine();
		    	  int sepIndex = einZeile.indexOf(' ');
		    	  String vStr = einZeile.substring(0, sepIndex);
		    	  String uStr = einZeile.substring(sepIndex+ 1, einZeile.length());
		    	  int v = new Integer(vStr);
		    	  int u = new Integer(uStr);
		    	  if (!(u >= 0 && u < n && v >= 0 && v < n))
		    		  throw new Exception("Falsche Knotennummer");
		    	  A[i][0] = v;
		    	  A[i][1] = u;
		      }
		  }
		  catch (Exception e) {
				System.out.println("Einlesen nicht erfolgreich");
				System.out.println(e.getMessage());
		  }

		return A;
	}

	/**
	 Funktion FileToWeightedGraphArray liest Werte aus Datei 'dat' und erzeugt ein Array, in dem
	 der Graph abgelegt ist.

	 @param dat eine Datei mit int-Werten in folgendem Format:
	 	1. Zeile: Anzahl der Knoten (n)
	 	2. Zeile: Anzahl der Kanten (m)
	 	3. Zeile - (m+2)-te Zeile: Endknoten der Kante, sowie das Kantengewicht durch ein Leerzeichen getrennt
	 	Hinweis: die Knoten m�ssen von 0 bis n-1 durchnummeriert, sonst Ausnahme
	 @return Array mit dem aus 'dat' gelesenen Graphen in folgendem Format
	 	Jede Komponente dieses Arrays besteht aus einem Array mit 3 int-Werten
	 	Komponente 0 enth�lt die Knotenanzahl n (Index 0) und die Kantenanzahl m (Index 1)
	 	Komponenten 1 .. m enthalten die Kanten {u, v} bzw. bei gerichteten Graphen (u,v)
	 		mit ihrem Kantengewicht;
	 		Knoten u ist dabei in Komponente mit Index 0, Knoten v in Komponente mit Index 1,
	 		das Kantengewicht in Komponente mit Index 2 abgelegt.
	 */
	private static int [][] FileToWeightedGraphArray(String dat) {
		int [][] A = null;
		FileInputStream fis = null;
		try {
		  fis = new FileInputStream(dat);
		}
		catch ( Exception e) {
			System.out.println(dat + " konnte nicht geoeffnet werden");
			System.out.println(e.getMessage());
		}
		try {
		      InputStreamReader isr   = new InputStreamReader(fis);
		      BufferedReader    br = new BufferedReader   (isr);

		      // Knotenanzahl lesen
		      String einZeile;
		      einZeile = br.readLine();
		      int n = new Integer(einZeile);

		      // Kantenanzahl lesen
		      einZeile = br.readLine();
		      int m = new Integer(einZeile);
		      A = new int [m+1][3];

		      // Knoten- und Kantenanzahl -> Array
		      A[0][0] = n;
		      A[0][1] = m;

		      // Kanten lesen
		      for (int i = 1; i <= m; i++) {
		    	  einZeile = br.readLine();
		    	  int sepIndex1 = einZeile.indexOf(' ');
		    	  int sepIndex2 = einZeile.indexOf(' ', sepIndex1+1);
		    	  String vStr = einZeile.substring(0, sepIndex1);
		    	  String uStr = einZeile.substring(sepIndex1+ 1, sepIndex2);
		    	  String wStr = einZeile.substring(sepIndex2+ 1, einZeile.length());
		    	  int v = new Integer(vStr);
		    	  int u = new Integer(uStr);
		    	  int w = new Integer(wStr);
		    	  if (!(u >= 0 && u < n && v >= 0 && v < n))
		    		  throw new Exception("Falsche Knotennummer");
		    	  A[i][0] = v;
		    	  A[i][1] = u;
		    	  A[i][2] = w;
		      }
		  }
		  catch (Exception e) {
				System.out.println("Einlesen nicht erfolgreich");
				System.out.println(e.getMessage());
		  }

		return A;
	}

	/**
	 Erzeugt einen ungewichteten Graph aus Werten die in einer Datei abgelegt sind

	 @param dat eine Datei mit int-Werten in folgendem Format:
	 	1. Zeile: Anzahl der Knoten (n)
	 	2. Zeile: Anzahl der Kanten (m)
	 	3. Zeile - (m+2)-te Zeile: Endknoten der Kante durch ein Leerzeichen getrennt
	 	Hinweis: die Knoten m�ssen von 0 bis n-1 durchnummeriert, sonst Ausnahme
	 @param directed true, wenn Graph gerichtet sein soll;
	 	dann wird jede in dat angegebene Kante (a,b) nur einmal erzeugt und
	 	in einer Nachbarliste abgelegt;
	 	false, wenn Graph ungerichtet sein soll; dann wird jede in dat angegebene
	 	Kante {a,b} durch zwei gerichtete Kanten (a,b) und (b,a) dargestellt
	 @return der Graph mit Standardgewicht 1 f�r die Kanten
	 */
	public static Graph<Vertex,Edge<Vertex>> FileToGraph(String dat, boolean directed) {
		int[][] GArray = FileToGraphArray(dat);
		int n = GArray[0][0];
		int m = GArray[0][1];
		Graph<Vertex,Edge<Vertex>> G = new Graph(n);

		// Knoten hinzufuegen
		for (int i = 0; i < n; i++) {
			G.addVertex(new Vertex(i));
		}

		/* Kanten hinzufuegen */
		for (int i = 1; i <= m; i++) {
			int idxa = GArray[i][0];
			int idxb = GArray[i][1];
			Vertex a = G.getVertex(idxa);
			Vertex b = G.getVertex(idxb);
			G.addEdge(new Edge<Vertex>(a,b));
			if (!directed) {
				G.addEdge(new Edge<Vertex>(b,a));
			}
		}
		return G;
	}
	/**
	 Erzeugt einen gewichteten Graph aus Werten die in einer Datei abgelegt sind

	 @param dat eine Datei mit int-Werten in folgendem Format:
	 	1. Zeile: Anzahl der Knoten (n)
	 	2. Zeile: Anzahl der Kanten (m)
	 	3. Zeile - (m+2)-te Zeile: Endknoten der Kante, sowie das Kantengewicht
	 	durch ein Leerzeichen getrennt
	 	Hinweis: die Knoten m�ssen von 0 bis n-1 durchnummeriert, sonst Ausnahme
	 @param directed true, wenn Graph gerichtet sein soll;
	 	dann wird jede in dat angegebene Kante (a,b) nur einmal erzeugt und
	 	in einer Nachbarliste abgelegt;
	 	false, wenn Graph ungerichtet sein soll; dann wird jede in dat angegebene
	 	Kante {a,b} durch zwei gerichtete Kanten (a,b) und (b,a) dargestellt
	 @return der Graph mit Standardgewicht 1 f�r die Kanten
	 */
	public static Graph<Vertex,Edge<Vertex>> FileToWeightedGraph(String dat, boolean directed) {
		int[][] GArray = FileToWeightedGraphArray(dat);
		int n = GArray[0][0];
		int m = GArray[0][1];
		Graph<Vertex,Edge<Vertex>> G = new Graph(n);

		// Knoten hinzufuegen
		for (int i = 0; i < n; i++) {
			G.addVertex(new Vertex(i));
		}

		/* Kanten hinzufuegen */
		for (int i = 1; i <= m; i++) {
			int idxa = GArray[i][0];
			int idxb = GArray[i][1];
			int w = GArray[i][2];
			Vertex a = G.getVertex(idxa);
			Vertex b = G.getVertex(idxb);
			G.addEdge(new Edge<Vertex>(a,b,w));
			if (!directed) {
				G.addEdge(new Edge<Vertex>(b,a,w));
			}
		}
		return G;
	}
}
