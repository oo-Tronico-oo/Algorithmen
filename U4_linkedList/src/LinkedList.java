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
    private int size = 0;
    
    public LinkedList(){
        this.head = new ListElement<>();
        this.tail = new ListElement<>();
        head.addNext(this.tail);
        tail.addPrev(this.head);
    }

    /* * appends the specified value to the end of this list
    * @param value the value to be appended
    */
    @Override
    public void add(Object value) {
        ListElement<T> neuesElem = new ListElement<>();
        try{
        neuesElem.addValue((T)value);
        }
        catch(Exception ex){
            throw new IllegalArgumentException("Der Methode add(Object value) wurde "
                    + "ein Objekt vom falschen Type als Parameter übergeben!");
        }
        
        tail.getPrev().addNext(neuesElem);
        neuesElem.addPrev(tail.getPrev());
        tail.addPrev(neuesElem);
        neuesElem.addNext(tail);
        
        size++;
    }

    /**
    * inserts the specified value at the specified position in this list
    * @param index - index at which the specified value has to be inserted
    * @param value - value to be inserted
    * @throws IndexOutOfBoundsException if the index is out of range (<0 or >size())
    * @throws IllegalArgumentException if the type of value don't equals <T> of LinkedList
    */ 
    @Override
    public void add(int index, Object value) throws IndexOutOfBoundsException, IllegalArgumentException{
        ListElement<T> elem = getListElement(index);
        try{
            elem.addValue((T)value);
        }
        catch(Exception ex){
            throw new IllegalArgumentException("Der Methode add(int index, Object value) "
                    + "wurde ein Objekt vom falschen Type als Parameter übergeben!");
        }
    }

    /**
    * tests if the specified value is contained in this list
    * @param value - value whose presence in this list is to be tested
    * @return the value, if value is contained in this list;
    *         null, otherwise
    */
    @Override
    public Object contains(Object value) {
        int count = this.size;
        ListElement<T> nextListElement = head.getNext();
        while(count > 0){
            if(nextListElement.getValue().equals(value)){
                return nextListElement.getValue();
            }
            else{
                nextListElement = nextListElement.getNext();
                count--;
            }
        }
        return null;
    }

    /**
    * returns the index of the first occurrence of the specified value in this list
    * @param value - value to search for
    * @return the index of the first occurrence of the specified value in this list, 
    *          or -1, if this list does not contain the value
    */
    @Override
    public int indexOf(Object value) {
        int count = 0;
        ListElement<T> nextListElement = head.getNext();
        while(count < this.size){
            if(nextListElement.getValue().equals(value)){
                return count;
            }
            else {
                nextListElement = nextListElement.getNext();
                count++;
            }
        }
        return -1;
    }

    /**
    * removes the first occurrence of the specified value from this list
    * @param value - the value to be removed from this list, if present
    * @return the removed value, it it is contained in the list;
    *        null, otherwise
    */
    @Override
    public Object remove(Object value) {
        int count = 0;
        ListElement<T> nextListElement = head.getNext();
        while(count < this.size){
            if(nextListElement.getValue().equals(value)){
                
                nextListElement.getPrev().addNext(nextListElement.getNext());
                nextListElement.getNext().addPrev(nextListElement.getPrev());
                
                return nextListElement.getValue();
            }
            else {
                nextListElement = nextListElement.getNext();
                count++;
            }
        }
        
        return null;
    }

    /**
    * removes the value at the specified index in this list
    * @param index - the index of the element to be removed
    * @return the element previously at the specified position
    * @throws IndexOutOfBoundsException if the index is out of range (<0 or >=size())
    */
    @Override
    public Object remove(int index) throws IndexOutOfBoundsException{
        ListElement<T> tempListElem = getListElement(index);
        
        tempListElem.getPrev().addNext(tempListElem.getNext());
        tempListElem.getNext().addPrev(tempListElem.getPrev());
        
        return tempListElem.getPrev().getValue();
    }

    /**
    * returns the value at the specified position in this list
    * @param index - the index of the value to be returned
    * @return the value at the specified position in this list
    * @throws IndexOutOfBoundsException if the index is out of range (<0 or >=size())
    */
    @Override
    public Object get(int index) throws IndexOutOfBoundsException{
        return getListElement(index).getValue();
    }

    /**
    * gives the number of elements in this list
    * @return the number of values in this list
    */
    @Override
    public int size() {
        return this.size;
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
        
        if(index < ((this.size - 1)/2) ){
            nextListElement = head.getNext();
            while(true){
                if(index == 0){
                    return nextListElement;
                }
                else{
                    nextListElement = nextListElement.getNext();
                    index--;
                }
            }
        }
        else{
            index = this.size - 1 - index;
            nextListElement = tail.getPrev();
            while(true){
                if(index == 0){
                    return nextListElement;
                }
                else{
                    nextListElement = nextListElement.getNext();
                    index--;
                }
            }
        }
    }
}
