import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Modul: Algorithmen
 * @Dozentin: Prof. Dr. H. Ripphausen-Lipa Beuth Hochschule für Technik
 * @Uebungsaufgabe: Implementierung des Sortierverfahren Mergesort
 * @author Lisa Bitterling, Katrin Schulze, Nico Nauendorf
 * @erstellt am 27. Oktober 2016
 */

/**
 * Dies ist eine Serviceklasse, die ein Sammlung von Objekten sortiert
 * Vorbedingung (ausgenommen int[]): die zu sortierenden Objekte implementieren Comparable
 */
public class Sortierverfahren {

    /**
     * Konstruktor der Klasse wurde als privat deklariert, da es eine statische
     * Klasse ist und keine Instanz notwendig ist.
     */
    private Sortierverfahren(){
    }
    
    /**
     * statische Methode zum sortieren von Integerzahlen nach dem Sortierverfahren Mergesort
     * 
     * @param intSammlung erwartet eine Sammlung von Integerzahlen vom Typ int[]
     * @return gibt eine sortierte Sammlung von Integerzahlen als int[] zurueck
     */
    public static int[] sortiereNachMergeSort(int[] intSammlung){
        //wenn Anzahl der int-Werte <= 1 oder null ist, gib die Sammlung bzw. null sofort zurück
        if(intSammlung == null || intSammlung.length <= 1) return intSammlung;
        else return sortiereNachMergeSortRekursiv(intSammlung, 0, intSammlung.length - 1);
    }
    
    /**
     * statische Methode zum sortieren von Objekten nach dem Sortierverfahren Mergesort
     * Vorbedingung, die zu sortierenden Objekte müssen das Interface Comparable implementieren
     * 
     * @param objektSammlung erwartet eine Sammlung vom Typ List
     * @return gibt eine sortierte Sammlung von Objekten als List zurueck
     */
    public static List sortiereNachMergeSort(List objektSammlung) throws IllegalArgumentException{
        //überprüfe, ob jedes Objekt das Interface Comparable implementiert. Wenn
        //nicht, werfe Exception
        Iterator it = objektSammlung.iterator();
        while(it.hasNext()){
            if(!(it.next() instanceof Comparable)) throw new IllegalArgumentException("Objekt "
                    + it.toString() + "implementiert das Interface Comparable nicht");
        }
        //wenn objektSammlung <= 1 oder null ist, gib die Sammlung bzw. null sofort zurück
        if(objektSammlung == null || objektSammlung.size() <= 1) return objektSammlung;
        else return sortiereNachMergeSortRekursiv(objektSammlung, 0, objektSammlung.size() - 1);
    }
    
    /**
     * private statische Methode zum sortieren von Teilfolgen aus Integerzahlen nach
     * dem Sortierverfahren Mergesort
     * 
     * @param intSammlung erwartet eine Sammlung von Integerzahlen vom Typ int[]
     * @param linkeGrenze erwartet einen Integerwert für die linken Grenze der zu betrachtenden Teilfolgen
     * @param rechteGrenze erwartet einen Integerwert für die rechten Grenze der zu betrachtenden Teilfolgen
     * @return gibt eine sortierte Sammlung von Integerzahlen als int[] zurueck
     */
    private static int[] sortiereNachMergeSortRekursiv(int[] intSammlung, int linkeGrenze, int rechteGrenze){
        //wenn intSammlung <= 1 gib die Sammlung sofort zurück
        if((rechteGrenze - linkeGrenze) < 1) return intSammlung;
        //Zerlege n-elementige Teilfolge A in zwei Teilfolgen A1, A2 der Länge n/2
        int mitte = (rechteGrenze - linkeGrenze) / 2 + linkeGrenze;
        //Sortiere die zwei Teilfolgen rekursiv durch Merge-Sort; erhalte A´1, A´2
        //sortiere linke Haelfte
        if(linkeGrenze < mitte){
            sortiereNachMergeSortRekursiv(intSammlung, linkeGrenze, mitte);
        }
        //sortiere rechte Haelfte
        if((mitte + 1) < rechteGrenze){
            sortiereNachMergeSortRekursiv(intSammlung, mitte + 1, rechteGrenze);
        }  
        //Mische die beiden sortierten Teilfolgen A´1 und A´2 zu einer sortierten Gesamtfolge A´
        merge(intSammlung, linkeGrenze, mitte, rechteGrenze);
        
        return intSammlung;
    }
    
    /**
     * private statische Methode zum mischen sortierter Teilfolgen (Integersammlung)
     * Vorbedingung: Teilfolgen sind sortiert
     * 
     * @param intSammlung erwartet eine Sammlung von Integerzahlen Typ int[]
     * @param linkeGrenze erwartet einen Integerwert für die linken Grenze der zu betrachtenden Teilfolgen
     * @param mitte erwartet einen Integerwert der die Mitte der Teilfolge representiert
     * @param rechteGrenze erwartet einen Integerwert für die rechten Grenze der zu betrachtenden Teilfolgen
     */
    private static void merge(int[] intSammlung, int linkeGrenze, int mitte, int rechteGrenze) {
        // Sicherung des Wertes der linken Grenze
        int backupLinkeGrenze = linkeGrenze;
        // Teilfeld A1 geht von Index linkeGrenze bis mitte
        // Teilfeld A2 geht von Index mitte+1 bis rechteGrenze
        // tempArray sei Hilfsfeld
        int[] tempArray = new int[(rechteGrenze - linkeGrenze) + 1];
        // linkeGrenze aktueller Index A1, mitte+1 aktueller Index A2
        int pointerA1 = linkeGrenze;
        int pointerA2 = mitte + 1;
        // speichert den zuletzt beschriebenen Index im tempArray
        int pointerTempArray = 0;
        // while (weder A1 noch A2 ist vollständig von links nach
        // rechts durchlaufen)
        while(pointerA1 <= mitte && pointerA2 <= rechteGrenze ){
            // kopiere kleineren aktuellen Wert von A1, A2 nach tempArray
            if(intSammlung[pointerA1] < intSammlung[pointerA2]){
                tempArray[pointerTempArray] = intSammlung[pointerA1];
                // erhöhe akt. Index von Teilfeld mit kleinerem Wert
                pointerA1++;
            } else{
                tempArray[pointerTempArray] = intSammlung[pointerA2];
                // erhöhe akt. Index von Teilfeld mit kleinerem Wert
                pointerA2++;
            }
            // erhöhe aktuellen Index von tempArray
            pointerTempArray++;
        }
        // übertrage Werte des noch nicht vollständig durchlaufenen Teilfelds A1 nach tempArray
        while(pointerA1 <= mitte){
            tempArray[pointerTempArray] = intSammlung[pointerA1];
            // erhöhe akt. Index von Teilfeld A1
            pointerA1++;
            // erhöhe aktuellen Index von tempArray
            pointerTempArray++;
        }
        // übertrage Werte des noch nicht vollständig durchlaufenen Teilfelds A2 nach tempArray
        while(pointerA2 <= rechteGrenze){
            tempArray[pointerTempArray] = intSammlung[pointerA2];
            // erhöhe akt. Index von Teilfeld A2
            pointerA2++;
            // erhöhe aktuellen Index von tempArray
            pointerTempArray++;
        }
        // kopiere tempArray nach A (von Index backupLinkeGrenze bis Index rechteGrenze)
        for(int i : tempArray){
            intSammlung[backupLinkeGrenze] = i;
            backupLinkeGrenze++;
        }
    }
    
    /**
     * statische Methode zum sortieren von Objekten nach dem Sortierverfahren Mergesort
     * Vorbedingung, die zu sortierenden Objekte müssen das Interface Comparable implementieren
     * 
     * @param objektSammlung erwartet eine Sammlung von Objekten vom Typ List
     * @param linkeGrenze erwartet einen Integerwert für die linken Grenze der zu betrachtenden Teilfolgen
     * @param rechteGrenze erwartet einen Integerwert für die rechten Grenze der zu betrachtenden Teilfolgen
     * @return gibt eine sortierte Sammlung als List zurueck
     */
    private static List sortiereNachMergeSortRekursiv(List objektSammlung, int linkeGrenze, int rechteGrenze){
        //wenn objektSammlung <= 1 gib die Menge sofort zurück
        if((rechteGrenze - linkeGrenze) < 1) return objektSammlung;
        //Zerlege n-elementige Teilfolge A in zwei Teilfolgen A1, A2 der Länge n/2
        int mitte = (rechteGrenze - linkeGrenze) / 2 + linkeGrenze;
        //Sortiere die zwei Teilfolgen rekursiv durch Merge-Sort; erhalte A´1, A´2
        //sortiere linke Haelfte
        if(linkeGrenze < mitte){
            sortiereNachMergeSortRekursiv(objektSammlung, linkeGrenze, mitte);
        }
        //sortiere rechte Haelfte
        if((mitte + 1) < rechteGrenze){
            sortiereNachMergeSortRekursiv(objektSammlung, mitte + 1, rechteGrenze);
        }  
        //Mische die beiden sortierten Teilfolgen A´1 und A´2 zu einer sortierten Gesamtfolge A´
        merge(objektSammlung, linkeGrenze, mitte, rechteGrenze);
        
        return objektSammlung;
    }
        
    /**
     * private statische Methode zum mischen sortierter Teilfolgen bestehend aus Objektsammlungen
     * Vorbedingung: Teilfolgen sind sortiert
     * 
     * @param objektSammlung erwartet eine Sammlung von Objekten vom Typ List
     * @param linkeGrenze erwartet einen Integerwert für die linken Grenze der zu betrachtenden Teilfolgen
     * @param mitte erwartet einen Integerwert der die Mitte der Teilfolge representiert
     * @param rechteGrenze erwartet einen Integerwert für die rechten Grenze der zu betrachtenden Teilfolgen
     */
    private static void merge(List objektSammlung, int linkeGrenze, int mitte, int rechteGrenze) {
        // Sicherung des Wertes der linken Grenze
        int backupLinkeGrenze = linkeGrenze;
        // Teilfeld A1 geht von Index linkeGrenze bis mitte
        // Teilfeld A2 geht von Index mitte+1 bis rechteGrenze
        // tempList sei Hilfsfeld
        List tempList = new LinkedList();
        // linkeGrenze aktueller Index A1, mitte+1 aktueller Index A2
        int pointerA1 = linkeGrenze;
        int pointerA2 = mitte + 1;
        // while (weder A1 noch A2 ist vollständig von links nach
        // rechts durchlaufen)
        while(pointerA1 <= mitte && pointerA2 <= rechteGrenze ){
            // kopiere kleineren aktuellen Wert von A1, A2 nach tempList
            if(((Comparable)objektSammlung.get(pointerA1)).compareTo(objektSammlung.get(pointerA2)) == -1){
                tempList.add(objektSammlung.get(pointerA1));
                // erhöhe akt. Index von Teilfeld mit kleinerem Wert
                pointerA1++;
            } else{
                tempList.add(objektSammlung.get(pointerA2));
                // erhöhe akt. Index von Teilfeld mit kleinerem Wert
                pointerA2++;
            }
        }
        // übertrage Werte des noch nicht vollständig durchlaufenen Teilfelds A1 nach tempList
        while(pointerA1 <= mitte){
            tempList.add(objektSammlung.get(pointerA1));
            // erhöhe akt. Index von Teilfeld A1
            pointerA1++;
        }
        // übertrage Werte des noch nicht vollständig durchlaufenen Teilfelds A2 nach tempList
        while(pointerA2 <= rechteGrenze){
            tempList.add(objektSammlung.get(pointerA2));
            // erhöhe akt. Index von Teilfeld A2
            pointerA2++;
        }
        // kopiere tempList nach A (von Index backupLinkeGrenze bis Index rechteGrenze)
        Iterator it = tempList.iterator();
        while(it.hasNext()){
            objektSammlung.set(backupLinkeGrenze, it.next());
            backupLinkeGrenze++;
        }
    }
}
