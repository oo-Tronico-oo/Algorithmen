import graph.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @Modul: Algorithmen
 * @Dozentin: Prof. Dr. H. Ripphausen-Lipa Beuth Hochschule für Technik
 * @Uebungsaufgabe: Implementierung des Dijkstras Algorithmus
 * @author Lisa Bitterling, Katrin Schulze, Nico Nauendorf
 * @erstellt am 12. Januar 2017
 */

/**
 * Diese Klasse berechnet den kürzesten Weg und dessen Entfernung ausgehend von
 * einem einzigen Startknoten mit Hilfe des Dijkstras Algorithmus
 */
public class Dijkstras{
    
    /**
     * Ermittelt den kuerzesten Weg
     * @param g erwartet einen Graphen
     * @param s erwartet den Startknoten
     * @param t erwartet den Zielknoten
     * @return die sortierten Vertices(Typ List) vom ermittelten kuerzesten Weges(s,t)
     */
    public static List<Vertex> kuerzesterWeg(Graph g, Vertex s, Vertex t){
        berechneDist(g, s);
        //list = Verlauf von s nach t
        List<Vertex> list = new ArrayList<>();
        list.add(t);
        // gibtPred = true, wenn Vorgänger existiert und t != s ist; sonst false
        boolean gibtPred = (t.getPred() != null && t != s);
        //nextPred ist der nächste zu betrachtende Vorgänger
        Vertex nextPred = t.getPred();
        //wiederhole solange ein Vorgänger existiert
        while(gibtPred){
            // setzte nextPred an die erste Stelle in der Liste
            list.add(0, nextPred);
            //if(es existiert noch ein Vorgänger && s ist noch nicht erreicht)
            if(nextPred.getPred() != null && nextPred != s){
                //setze nächsten Vorgänger
                nextPred = nextPred.getPred();
            }
            //ansonsten beende while-Anweisung
            else gibtPred = false;
        }
//        list.stream().forEach(v -> System.out.print(v.getId() + ", "));
        return list;
    }
    
    /**
     * Ermittelt die Entfernung vom kuerzesten Weg
     * @param g erwartet einen Graphen
     * @param s erwartet den Startknote
     * @param t erwartet den Zielknoten
     * @return die Entfernung(Typ double) des kürsesten Weges(s,t)
     */
    public static double kuerzesteWegeEntfernung(Graph g, Vertex s, Vertex t){
        berechneDist(g, s);
        return t.getDist();
    }

//################ private ##############################################

    /**
     * Ermittelt die Dist-Werte und setzt die Vorgänger aller Vertices
     * @param g erwartet einen Graphen
     * @param s erwartet den Startknoten
     * 
     * Bedingung: alle Kantengewichte sind positiv
     */
    private static void berechneDist(Graph g, Vertex s){
        pruefeEdges(g);
        Collection<Vertex> allVertices = g.getVertices();
        //Q = V(G); //initialization : add allvertices to Q
        List<Vertex> priQ = initPrioQ(allVertices, s);
        //intitialisiert die fehlenden Werte für alle Knoten im Graphen
        initializeSingleSource(allVertices, s);
        //while(Q != Ø)
        while(!priQ.isEmpty()){
            //u = Extract$Min(Q) ; // Minimum w.r.t. dist
            Vertex u = priQ.remove(0);
            //for(all neigbours v of u)
            g.getIncidentEdges(u).stream()
                    .forEach(v -> {
                        Vertex relaxVertex = relax((Edge)v, priQ);
                        if(relaxVertex != null)reorganizePriQ(relaxVertex, priQ);
                    });
        }
    }
    
    /**
     * Private Methode, die den Graphen auf negative Kantengewichte prüft
     * @exception IllegalArgumentException, wenn eine Kante eine negative Gewichtung hat
     * @param g erwartet einen Graphen
     */
    private static void pruefeEdges(Graph g){
        Collection<Edge> allEdges = g.getEdges();
        //durchlaufe alle Edges im Graphen und prüfe auf Kantengewicht
        allEdges.stream()
                .forEach(egde -> {
                    if(egde.getWeight() < 0)
                        throw new IllegalArgumentException("Gewicht der Kante "
                                + "zwischen Knoten" + egde.getVertexA().getId()
                                + " und " + egde.getVertexB().getId() 
                                + " ist negativ!");
                });
    }
    
    /**
     * private Methode zur Relaxation einer Kante (u, v)
     * @param e erwartet eine Kante
     * @param priQ erwartet die priority Queue
     * @return den abgeänderten Vertex, ansonsten null
     */
    private static Vertex relax(Edge e, List<Vertex> priQ){
        //if(dist[v] > dist[u] + w(u,v))
        Vertex vertexA = e.getVertexA();
        Vertex vertexB = e.getVertexB();
        
        if(vertexB.getDist() > vertexA.getDist() + e.getWeight()){
            //dist[v] = dist[u] + w(u,v);
            vertexB.setDist(vertexA.getDist() + e.getWeight());
            //pred[v] = u;
            vertexB.setPred(vertexA);
            return vertexB;
        }
        return null;
    }
    
    /**
     * private Methode dient der reorganisierung der priority Queue
     * @param relaxVertex erwartet den veränderten Vertex
     * @param priQ erwartet die priority Queue
     */ 
    private static void reorganizePriQ(Vertex relaxVertex, List<Vertex> priQ){
        Iterator<Vertex> it = priQ.iterator();
        int index = 0;
        //durchlaufe die priority Queue bis zum dem Vertex, der einen größeren dist-Wert
        //aufweist als der veränderte Vertex und verschiebe den relaxVertex an diese Position
        while(it.hasNext()){
            Vertex temp = it.next();
            if(relaxVertex != temp && relaxVertex.getDist() <= temp.getDist()){
                priQ.remove(relaxVertex);
                priQ.add(index, relaxVertex);
                break;
            }
            index++;
        }
    }
    
    /**
     * intitialisiert die fehlenden Werte für alle Knoten im Graphen
     * @param g erwartet den Graphen
     * @param s erwartet den Startknoten
     */
    private static void initializeSingleSource(Collection<Vertex> allVertices, Vertex s){
        //for(all vertices v in V) dist[v] = MAXDOUBLE; pred[v] = null;
        allVertices.stream()
                .forEach(v -> {
                    v.setDist(Double.MAX_VALUE);
                    v.setPred(null);
                });
        //dist[s] = 0;
        s.setDist(0);
    }
    
    /**
     * initialisiere die priority Queue: der erste Vertex ist der Startknoten
     * @param cV erwartet alle Vertices im Graphen
     * @param s erwartet den Startknoten
     * @return eine geordnete Liste mit allen Vertices
     */
    private static List<Vertex> initPrioQ(Collection<Vertex> cV, Vertex s){
        List<Vertex> list = new ArrayList<>();
        list.add(s);
        //fügt alle Vertices(außer s) der list hinzu
        cV.stream()
                .filter(aktuellerVertex -> !(aktuellerVertex.equals(s)))
                .forEach(aktuellerVertex -> list.add(aktuellerVertex));
        
        return list;
    }
}
