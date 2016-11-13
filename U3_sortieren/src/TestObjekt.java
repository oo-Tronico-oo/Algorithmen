/**
 * @Modul: Algorithmen
 * @Dozentin: Prof. Dr. H. Ripphausen-Lipa Beuth Hochschule für Technik
 * @Uebungsaufgabe: Implementierung des Sortierverfahren Mergesort
 * @author Lisa Bitterling, Katrin Schulze, Nico Nauendorf
 * @erstellt am 27. Oktober 2016
 */

/**
 * erzeugt ein Objekt mit einer Intergerzahl zur prüfung der Zusatzaufgabe
 */
public class TestObjekt implements Comparable{
    public int wert;
    
    public TestObjekt(int wert){
        this.wert = wert;
    }

    @Override
    public int compareTo(Object o) {
        int vergleichswert = ((TestObjekt)o).wert;
        if(wert < vergleichswert) return -1;
        else if(wert == vergleichswert) return 0;
        else return 1;
    }
    
    @Override
    public String toString(){
        return String.valueOf(wert);
    }
}
