import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Modul: Algorithmen
 * @Dozentin: Prof. Dr. H. Ripphausen-Lipa Beuth Hochschule für Technik
 * @Uebungsaufgabe: Implementierung der doppelt verketteten Liste
 * @author Lisa Bitterling, Katrin Schulze, Nico Nauendorf
 * @erstellt am 22. November 2016
 */

/**
 * Diese Klasse dient der Speicherung von Objekten in einer doppelt verketteten Liste
 * Sie implementiert das Interface List und Iterator
 * @param <T> erwartet den Typ des abzuspeichernden Objektes
 */
public class LinkedList<T> implements List,  Iterator<T> {
    
    private final ListElement<T> head, tail;
    private int size = 0;
    
    // Zeiger auf das zuletzt betrachtete ListElement
    private ListElement<T> iteratorPointer;
    
    public LinkedList(){
        this.head = new ListElement<>();
        this.tail = new ListElement<>();
        
        // setze die Nachfolger- und Vorgängerreferenzen
        head.setNext(this.tail);
        tail.setPrev(this.head);
        
        iteratorPointer = head;
    }

    /**
     * Fügt den übergebenen Wert am Ende der Liste an
     * @param value der hinzuzufügende Wert
     */
    @Override
    public void add(Object value) {
        ListElement<T> neuesElem = new ListElement<>();
        try{
        neuesElem.setValue((T)value);
        }
        catch(Exception ex){
            throw new IllegalArgumentException("Der Methode add(Object value) wurde "
                    + "ein Objekt vom falschen Type als Parameter übergeben!");
        }
        
        // setze die Nachfolger- und Vorgängerreferenzen vom tail(dummy), desen Vorgänger
        // und vom neuen Element
        tail.getPrev().setNext(neuesElem);
        neuesElem.setPrev(tail.getPrev());
        tail.setPrev(neuesElem);
        neuesElem.setNext(tail);
        
        // vergrößere die Repräsentation der Listengröße um 1
        size++;
    }

    /**
    * Fügt den übergebenen Wert am vorgegebenen Index in die Liste ein
    * @param index - Index an dem der übergebene Wert eingefügt werden soll
    * @param value - Wert der eingefügt werden soll
    * @throws IndexOutOfBoundsException wenn der Index außerhalb der Liste liegt(<0 or >=size())
    * @throws IllegalArgumentException wenn der Typ des Wertes nicht <T> der LinkedList entspricht
    */ 
    @Override
    public void add(int index, Object value) throws IndexOutOfBoundsException, IllegalArgumentException{
        ListElement<T> elem = getListElement(index);
        try{
            elem.setValue((T)value);
        }
        catch(Exception ex){
            throw new IllegalArgumentException("Der Methode add(int index, Object value) "
                    + "wurde ein Objekt vom falschen Type als Parameter übergeben!");
        }
    }

    /**
    * Testet, ob der übergebene Wert in der Liste vorhanden ist
    * @param value - der zu überprüfende Wert, ob in der Liste vorhanden ist
    * @return den Wert, wenn er in der Liste vorhanden ist;
    *         null, wenn nicht
    */
    @Override
    public Object contains(Object value) {
        ListElement<T> nextListElement = head.getNext();
        
        // führe die Schleife so lange aus, bis der Wert gefunden wurde oder
        // jedes Element einmal betrachtet wurde
        while(nextListElement.getNext() != null && !(nextListElement.getValue().equals(value))){
            nextListElement = nextListElement.getNext();
        }
        
        // return null, wenn der Wert nicht in der Liste existiert, ansonsten den gefundenen Wert
        return (nextListElement.getNext() == null)?null:nextListElement.getValue();
    }

    /**
    * Liefert den Index des erstens Wertes innerhalb der Liste, der dem gesuchten Wert entspricht
    * @param value - gesuchter Wert
    * @return der Index des ersten Wertes innerhalb der Liste, der dem gesuchten Wert entspricht, 
    *          oder -1, wenn der Wert nicht in der Liste vorhanden ist 
    */
    @Override
    public int indexOf(Object value) {
        int count = 0;
        ListElement<T> nextListElement = head.getNext();
        
        // führe die Schleife so lange aus, bis der Wert gefunden wurde oder
        // jedes Element einmal betrachtet wurde
        while(nextListElement.getNext() != null && !(nextListElement.getValue().equals(value))){
            nextListElement = nextListElement.getNext();
            count++;
        }
        
        // return -1, wenn der Wert in der Liste nicht gefunden wurde, ansonsten count
        return (nextListElement.getNext() == null)?-1:count;
    }

    /**
    * Lösche den erten Wert innerhalb der Liste, der dem gesuchten Wert entspricht
    * @param value - der von der Liste zu löschende Wert
    * @return den entfernten Wert, wenn er in der Liste vorhanden war;
    *        null, wenn der Wert nicht gefunden wurde
    */
    @Override
    public Object remove(Object value) {
        ListElement<T> nextListElement = head.getNext();
        
        // führe die Schleife so lange aus, bis der Wert gefunden wurde oder
        // jedes Element einmal betrachtet wurde
        while(nextListElement.getNext() != null){
            if(nextListElement.getValue().equals(value)){
                
                // setze die Nachfolger- und Vorgängerreferenzen vom Vorgänger und Nachfolger
                // neu damit nichts mehr auf das zu löschende Element zeigt
                nextListElement.getPrev().setNext(nextListElement.getNext());
                nextListElement.getNext().setPrev(nextListElement.getPrev());
                
                // vermindere die Repräsentation der Listengröße um 1
                size--;
        
                return nextListElement.getValue();
            }
            else {
                nextListElement = nextListElement.getNext();
            }
        }
        
        // return null wenn der Wert nicht gefunden wurde
        return null;
    }

    /**
    * Entfernt den übergebenen Wert am vorgegebenen Index innerhalb der Liste
    * @param index - Index des zu entfernenden Wertes
    * @return das Element welches zuvor an der durch den Index spezifizierten Stelle war
    * @throws IndexOutOfBoundsException wenn der Index außerhalb der Liste liegt(<0 or >=size())
    */
    @Override
    public Object remove(int index) throws IndexOutOfBoundsException{
        ListElement<T> tempListElem = getListElement(index);
        
        // setze die Nachfolger- und Vorgängerreferenzen vom Vorgänger und Nachfolger
        // neu damit nichts mehr auf das zu löschende Element zeigt
        tempListElem.getPrev().setNext(tempListElem.getNext());
        tempListElem.getNext().setPrev(tempListElem.getPrev());
        
        // vermindere die Repräsentation der Listengröße um 1
        size--;
        
        return tempListElem.getPrev().getValue();
    }

    /**
    * Liefert den Wert, der am übergebenen Index in der Liste steht, zurück
    * @param index - der Index des Wertes, der zurückgeliefert werden soll
    * @return der Wert an der spezifizierten Stelle in der Liste
    * @throws IndexOutOfBoundsException wenn der Index außerhalb der Liste liegt(<0 or >=size())
    */
    @Override
    public Object get(int index) throws IndexOutOfBoundsException{
        return getListElement(index).getValue();
    }

    /**
    * Liefert die Anzahl an Elementen in der Liste zurück
    * @return die Anzahl der Elemente in der Liste
    */
    @Override
    public int size() {
        return this.size;
    }
    
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    public Iterator<T> iterator(){
        return this;
    }
    
    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return iteratorPointer.getNext()!= tail;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public T next() throws NoSuchElementException{
        if(hasNext()){
            iteratorPointer = iteratorPointer.getNext();
            return iteratorPointer.getValue();
        }
        else {
            iteratorPointer = head;
            throw new NoSuchElementException();
        }
    }
    
    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  This method can be called
     * only once per call to {@link #next}.  The behavior of an iterator
     * is unspecified if the underlying collection is modified while the
     * iteration is in progress in any way other than by calling this
     * method.
     */
    @Override
    public void remove() {
        remove(iteratorPointer.getValue());
        iteratorPointer = iteratorPointer.getPrev();
    }

    //######################## private #####################################
    
    /**
    * gibt für den angegebenen Index das ListElement zurück
    * @param index der Index vom ListElement welches zurückgegeben werden soll
    * @return das gefundene ListElement
    * @throws IndexOutOfBoundsException, wenn der Index außerhalb der Liste liegt.
    */
    private ListElement<T> getListElement(int index) throws IndexOutOfBoundsException{
        
        if(index > (this.size - 1) || index < 0) throw new IndexOutOfBoundsException();
        
        ListElement<T> nextListElement;
        
        // beginne die Suche von vorne, wenn index < die Hälfte der ListenElemente ist
        // und ignoriere den head(dummy)
        if(index < ((this.size - 1)/2) ){
            nextListElement = head.getNext();
            
            // durchlaufe die Schleife so oft, wie indexParameter
            for(int i = index; i > 0; i--){
                nextListElement = nextListElement.getNext();
            }
        }
        // ansonsten durchlaufe die Liste von hinten und ignoriere den tail(dummy)
        else{
            nextListElement = tail.getPrev();
            
            // durchlaufe die Schleife so oft, wie (maxIndexWert - indexParameter)
            for(int i = this.size - 1 - index; i > 0; i--){
                nextListElement = nextListElement.getPrev();
            }
        }
        
        return nextListElement;
    }
}
