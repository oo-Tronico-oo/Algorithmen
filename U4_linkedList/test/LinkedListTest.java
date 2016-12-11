/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Tronico
 */
public class LinkedListTest {
    
    private final LinkedList<String> liste = new LinkedList<>();
    private final int[] ausgangsWerte = new int[]{2, 5, 8, 15, 19, 50, 78, 105, 555, 1600};
    
    public LinkedListTest() {
    }
    
    @Before
    public void setUp() {
        for(int i : ausgangsWerte){
            liste.add(i);
        }
    }
    
    /**
     * Test of add method, of class LinkedList.
     */
    @Test
    public void testAdd_Object() {
        System.out.println("add");
        liste.add(16);
        assertEquals(16, liste.contains(16));
        assertEquals(11, liste.size());
    }

    /**
     * Test of add method, of class LinkedList.
     */
    @Test
    public void testAdd_int_Object() {
        System.out.println("add with index");
        liste.add(1, 12);
        assertEquals(12, liste.contains(12));
        assertEquals(10, liste.size());
    }

    /**
     * Test of contains method, of class LinkedList.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        assertEquals(78, liste.contains(78));
        assertEquals(null, liste.contains(3));
    }

    /**
     * Test of indexOf method, of class LinkedList.
     */
    @Test
    public void testIndexOf() {
        System.out.println("indexOf");
        assertEquals(1, liste.indexOf(5));
        assertEquals(-1, liste.indexOf(3));
    }

    /**
     * Test of remove method, of class LinkedList.
     */
    @Test
    public void testRemove_Object() {
        System.out.println("remove");
        assertEquals(8, liste.remove(new Integer(8)));
        assertEquals(null, liste.remove(new Integer(3)));
        assertEquals(9, liste.size());
    }

    /**
     * Test of remove method, of class LinkedList.
     */
    @Test
    public void testRemove_int() {
        System.out.println("remove on index");
        assertEquals(78, liste.remove(7));
        assertEquals(9, liste.size());
    }

    /**
     * Test of get method, of class LinkedList.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        assertEquals(2, liste.get(0));
        assertEquals(19, liste.get(4));
        assertEquals(1600, liste.get(9));
    }

    /**
     * Test of size method, of class LinkedList.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        assertEquals(10, liste.size());
    }
    
}
