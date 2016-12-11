import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * @Modul: Algorithmen
 * @Dozentin: Prof. Dr. H. Ripphausen-Lipa Beuth Hochschule für Technik
 * @Uebungsaufgabe: Implementierung der doppelt verketteten Liste
 * @author Lisa Bitterling, Katrin Schulze, Nico Nauendorf
 * @erstellt am 22. November 2016
 */

/**
 *Diese Klasse dient zum Testen der LinkedList.
 */
public class LinkedListTest {
    
    private final LinkedList<Integer> fullList = new LinkedList<>();
    private final LinkedList<Integer> emptyListe = new LinkedList<>();
    private final int[] ausgangsWerte = new int[]{2, 5, 8, 15, 19, 50, 78, 105, 555, 1600};
    
    public LinkedListTest() {
    }

    /**
     * initialisiert vor jeder test-Methode die gefüllte LinkedList
     * Inhalt ist das Array ausgangsWerte
     */
    @Before
    public void setUp() {
        for(int i : ausgangsWerte){
            fullList.add(i);
        }
    }

    /**
     * Test der add Methode, der Klasse LinkedList.
     */
    @Test
    public void testAdd_Object() {
        System.out.println("add");
        fullList.add(16);
        assertEquals(16, fullList.contains(16));
        assertEquals(11, fullList.size());
    }

    /**
     * Test der add Methode, der Klasse LinkedList.
     */
    @Test
    public void testAdd_int_Object() {
        System.out.println("add with index");
        fullList.add(1, 12);
        assertEquals(12, fullList.contains(12));
        assertEquals(10, fullList.size());
    }

    /**
     * Test der contains Methode, der Klasse LinkedList.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        assertEquals(78, fullList.contains(78));
        assertEquals(null, fullList.contains(3));
    }

    /**
     * Test der indexOf Methode, der Klasse LinkedList.
     */
    @Test
    public void testIndexOf() {
        System.out.println("indexOf");
        assertEquals(1, fullList.indexOf(5));
        assertEquals(-1, fullList.indexOf(3));
    }

    /**
     * Test der remove Methode, der Klasse LinkedList.
     */
    @Test
    public void testRemove_Object() {
        System.out.println("remove");
        assertEquals(8, fullList.remove(new Integer(8)));
        assertEquals(null, fullList.remove(new Integer(3)));
        assertEquals(9, fullList.size());
    }

    /**
     * Test der remove Methode, der Klasse LinkedList.
     */
    @Test
    public void testRemove_int() {
        System.out.println("remove on index");
        assertEquals(78, fullList.remove(7));
        assertEquals(9, fullList.size());
    }

    /**
     * Test der get Methode, der Klasse LinkedList.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        assertEquals(2, fullList.get(0));
        assertEquals(19, fullList.get(4));
        assertEquals(1600, fullList.get(9));
    }

    /**
     * Test der size Methode, der Klasse LinkedList.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        assertEquals(10, fullList.size());
    }

    /**
     * Test der hasNext Methode(Iterator), der Klasse LinkedList.
     */
    @Test
    public void testHasNext() {
        System.out.println("hasNext(Iterator)");
        Iterator it = fullList.iterator();
        assertTrue(it.hasNext());
        it = emptyListe.iterator();
        assertFalse(it.hasNext());
    }

    /**
     * Test der Next Methode(Iterator), der Klasse LinkedList.
     */
    @Test
    public void testNext() {
        System.out.println("next(Iterator)");
        Iterator it = fullList.iterator();
        int index = 0;
        while(it.hasNext()){
            assertEquals(ausgangsWerte[index], it.next());
            index++;
        }
    }

    /**
     * Test der remove Methode(Iterator), der Klasse LinkedList.
     */
    @Test
    public void testRemove_0args() {
        System.out.println("remove(Iterator)");
        Iterator it = fullList.iterator();
        int index = 0;
        int removed = 0;
        while(it.hasNext()){
            it.next();
            if(index % 2 == 0){
                it.remove();
                removed++;
                assertEquals(10 - removed, fullList.size());
                assertEquals(null ,fullList.contains(ausgangsWerte[index]));
            }
            index++;
        }
    }
    
}
