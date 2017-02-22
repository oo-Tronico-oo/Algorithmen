import graph.Graph;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import graph.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Modul: Algorithmen
 * @Dozentin: Prof. Dr. H. Ripphausen-Lipa Beuth Hochschule für Technik
 * @Uebungsaufgabe: Implementierung des Dijkstras Algorithmus
 * @author Lisa Bitterling, Katrin Schulze, Nico Nauendorf
 * @erstellt am 12. Januar 2017
 */

/**
 *Diese Klasse dient zum Testen des Dijkstras Algorithmus
 */
public class DijkstrasTest {
    //Graph aus der TXT-Datei auslesen
    String dat = "src/BeispieleGewichtet/graphwsu.txt";
    Graph graph = GraphLesen.FileToWeightedGraph(dat, true);

    public DijkstrasTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Testet, ob bei einem Graphen mit negativen Kantengewichte eine Exception
     * geworfen wird
     */ 
    @Test(expected = IllegalArgumentException.class)
    public void test_Exception_neg_Gewicht() {
        Graph badGraph = GraphLesen.FileToWeightedGraph("src/BeispieleGewichtet/"
                + "graphwbf.txt", true);
        Dijkstras.kuerzesterWeg(badGraph, badGraph.getVertex(0), badGraph.getVertex(1));
    }
    
    /**
     * testet die Methode für die Entfernung des kürzesten Weges
     */
    @Test
    public void test_kuerzesteWegeEntfernung() {
        double kWE = Dijkstras.kuerzesteWegeEntfernung(graph, graph.getVertex(0),
                graph.getVertex(5));
        
        assertTrue(kWE == 6.0);
        assertFalse(kWE == 4.0);
    }
    
    /**
     * testet die Methode um den kürzesten Weg zu finden
     */
    @Test
    public void test_kuerzesterWeg() {
        List<Vertex> kW = Dijkstras.kuerzesterWeg(graph, graph.getVertex(0),
                graph.getVertex(5));
        
        List<Vertex> expRes = new ArrayList<>();
        expRes.add(graph.getVertex(0));
        expRes.add(graph.getVertex(1));
        expRes.add(graph.getVertex(3));
        expRes.add(graph.getVertex(4));
        expRes.add(graph.getVertex(5));
        
        List<Vertex> falseRes = new ArrayList<>();
        falseRes.add(graph.getVertex(0));
        falseRes.add(graph.getVertex(1));
        falseRes.add(graph.getVertex(3));
        falseRes.add(graph.getVertex(2));
        falseRes.add(graph.getVertex(5));
        
        assertTrue(kW.equals(expRes));
        assertFalse(kW.equals(falseRes));
    }
}
