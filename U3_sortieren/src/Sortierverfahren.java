/**
 * @Modul: Algorithmen
 * @Dozentin: Prof. Dr. H. Ripphausen-Lipa Beuth Hochschule für Technik
 * @Uebungsaufgabe: Implementierung des Sortierverfahren Mergesort
 * @author Lisa Bitterling, Katrin Schulze, Nico Nauendorf
 * @erstellt am 27. Oktober 2016
 */

/**
 * Dies ist eine Serviceklasse, die eine beliebige Menge von Integerzahlen sortiert
 */
public class Sortierverfahren {

    /**
     * Konstruktor der Klasse wurde als privat deklariert, da es eine statische
     * Klasse ist und keine Instanz notwendig ist.
     */
    private Sortierverfahren(){
    }
    
    /**
     * statische Methode zum sortieren von Integerzahlen nach
     * dem Sortierverfahren Mergesort innerhalb der linken und rechten Grenze
     * 
     * @param objektMenge erwartet eine unsortierte Menge von Integerzahlen vom Typ int[]
     * @return gibt eine sortierte Menge von Integerzahlen als int[] zurueck
     */
    static int[] sortiereNachMergeSort(int[] objektMenge, int linkeGrenze, int rechteGrenze){
        //Zerlege n-elementige Teilfolge A in zwei Teilfolgen A1, A2 der Länge n/2
        int mitte = 0;
        //Sortiere die zwei Teilfolgen rekursiv durch Merge-Sort; erhalte A´1, A´2
        //sortiere linke Haelfte
        if(linkeGrenze < mitte){
            sortiereNachMergeSort(objektMenge, linkeGrenze, mitte);
        }
        //sortiere rechte Haelfte
        if((mitte + 1) < rechteGrenze){
            sortiereNachMergeSort(objektMenge, mitte + 1, rechteGrenze);
        }  
        //Mische die beiden sortierten Teilfolgen A´1 und A´2 zu einer sortierten Gesamtfolge A´
        merge(objektMenge, linkeGrenze, mitte, rechteGrenze);
        
        return objektMenge;
    }
    
    /**
     * private Methode zum mischen sortierter Teilfolgen von linker bis rechter Grenze
     * 
     * @param objektMenge erwartet eine Menge von Integerzahlen
     * @param linkeGrenze ist die linke Grenze vom der Teilfolge
     * @param mitte ist die Mitte der Teilfolge
     * @param rechteGrenze ist die rechte Grenze der Teilfolge
     */
    private static void merge(int[] objektMenge, int linkeGrenze, int mitte, int rechteGrenze) {
        // Teilfeld A1 geht von Index l bis m
        // Teilfeld A2 geht von Index m+1 bis r
        // Precondition: A1 ist sortiert und A2 ist sortiert
        // H sei Hilfsfeld
        
        // l aktueller Index A1, m+1 aktueller Index A2

        // while (weder A1 noch A2 ist vollständig von links nach
        // rechts durchlaufen)
        // kopiere kleineren aktuellen Wert von A1, A2 nach H
        // erhöhe akt. Index von Teilfeld mit kleinerem Wert
        // erhöhe aktuellen Index von H
        // übertrage Werte des noch nicht vollständig
        // durchlaufenen Teilfelds nach H
        // kopiere H nach A (von Index l bis Index r)
    }
}
