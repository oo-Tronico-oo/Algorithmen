/**
 * @Modul: Algorithmen
 * @Dozentin: Prof. Dr. H. Ripphausen-Lipa Beuth Hochschule für Technik
 * @Uebungsaufgabe: Implementierung des Sortierverfahren Mergesort
 * @author Lisa Bitterling, Katrin Schulze, Nico Nauendorf
 * @erstellt am 27. Oktober 2016
 */

/**
 * erzeugt ein Objekt das Comparable implementier zur prüfung der Zusatzaufgabe
 * das Objekt speichert einen Integerwert
 */
public class TestObjekt implements Comparable{
    public int wert;
    
    /**
     * Konstruktor der Klasse
     * 
     * @param wert erwartet den zu speichernden Integerwert
     */
    public TestObjekt(int wert){
        this.wert = wert;
    }

    /**
     * zu überschreibende Methode des Interface Comparable
     * 
     * @param o erwartet das zu vergleichende Objekt
     * @return -1 wenn o < ist als this, 0 wenn o == this ansonsten 1 (o > this)
     */
    @Override
    public int compareTo(Object o) {
        int vergleichswert = ((TestObjekt)o).wert;
        if(wert < vergleichswert) return -1;
        else if(wert == vergleichswert) return 0;
        else return 1;
    }
    
    /**
     * zu überschreibende Methode des Interface Comparable
     *
     * @return den wert des Objektes als String
     */
    @Override
    public String toString(){
        return String.valueOf(wert);
    }
}
