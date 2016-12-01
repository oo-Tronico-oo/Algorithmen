/**
 * @Modul: Algorithmen
 * @Dozentin: Prof. Dr. H. Ripphausen-Lipa Beuth Hochschule für Technik
 * @Uebungsaufgabe: Implementierung der doppelt verketteten Liste
 * @author Lisa Bitterling, Katrin Schulze, Nico Nauendorf
 * @erstellt am 22. November 2016
 */

/**
 * Diese Klasse dient der Speicherung von Objekten in einer doppelt verketteten Liste
 * Sie implementiert das Interface List 
 * @param <T> erwartet den Typ des abzuspeichernden Objektes
 */
public class LinkedList<T> implements List{
    
    private final ListElement<T> head, tail;
    
    public LinkedList(){
        this.head = new ListElement<>();
        this.tail = new ListElement<>();
        head.addNext((T)this.tail);
        tail.addPrev((T)this.head);
    }

    @Override
    public void add(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(int index, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object contains(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int indexOf(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object remove(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
