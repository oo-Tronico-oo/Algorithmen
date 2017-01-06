/**
 * @Modul: Algorithmen
 * @Dozentin: Prof. Dr. H. Ripphausen-Lipa Beuth Hochschule für Technik
 * @Uebungsaufgabe: Implementierung der doppelt verketteten Liste
 * @author Lisa Bitterling, Katrin Schulze, Nico Nauendorf
 * @erstellt am 22. November 2016
 */

/**
 * Dies ist eine Hilfsklasse zur Erzeugung einer doppelt verketteten Liste
 * Sie speichert das Listenelement, den Vorgänger und den Nachfolger von dem Listenelement
 * @param <T> erwartet den Typ des abzuspeichernden Elements
 */
public class ListElement<T> {
    
    private T value;
    private ListElement<T> next, prev;
    
    //***** getter und setter-Methoden ******
    
    /**
     * Methode setValue(T value)
     * @param value erwartet den einzutragenden Wert
     */
    public void setValue(T value){
        this.value = value;
    }
    
    public void setNext(ListElement<T> next){
        this.next = next;
    }
    
    public void setPrev(ListElement<T> prev){
        this.prev = prev;
    }

    public T getValue() {
        return value;
    }

    public ListElement<T> getNext() {
        return next;
    }

    public ListElement<T> getPrev() {
        return prev;
    }
}
