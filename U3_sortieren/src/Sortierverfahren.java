import java.util.Iterator;
import java.util.List;

/**
 * @Modul: Algorithmen
 * @Dozentin: Prof. Dr. H. Ripphausen-Lipa Beuth Hochschule für Technik
 * @Uebungsaufgabe: Implementierung des Sortierverfahren Mergesort
 * @author Lisa Bitterling, Katrin Schulze, Nico Nauendorf
 * @erstellt am 27. Oktober 2016
 */

/**
 * Dies ist eine Serviceklasse, die eine beliebige Menge von Integerzahlen oder beliebigen Objekten sortiert
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
     * @param objektMenge erwartet eine unsortierte Menge von Integerzahlen vom Typ int[]
     * @return gibt eine sortierte Menge von Integerzahlen als int[] zurueck
     */
    public static int[] sortiereNachMergeSort(int[] objektMenge){
        //wenn objektMenge <= 1 oder null ist, gib die Menge bzw. null sofort zurück
        if(objektMenge == null || objektMenge.length <= 1) return objektMenge;
        else return sortiereNachMergeSortRekursiv(objektMenge, 0, objektMenge.length - 1);
    }
    
    /**
     * statische Methode zum sortieren von beliebigen Objekten nach dem Sortierverfahren Mergesort
     * Bedingung, die zu sortierenden Objekte müssen das Interface Comparable implementieren
     * 
     * @param objektMenge erwartet eine unsortierte Menge vom Typ List
     * @return gibt eine sortierte Menge von Objekten als List zurueck
     */
    public static List sortiereNachMergeSort(List objektMenge) throws IllegalArgumentException{
        //überprüfe, ob jedes Objekt das Interface Comparable implementiert. Wenn
        //nicht, werfe Exception
        Iterator it = objektMenge.iterator();
        while(it.hasNext()){
            if(!(it.next() instanceof Comparable)) throw new IllegalArgumentException("Objekt "
                    + it.toString() + "implementiert das Interface Comparable nicht");
        }
        //wenn objektMenge <= 1 oder null ist, gib die Menge bzw. null sofort zurück
        if(objektMenge == null || objektMenge.size() <= 1) return objektMenge;
        else return sortiereNachMergeSortRekursiv(objektMenge, 0, objektMenge.size() - 1);
    }
    
    /**
     * private statische Methode zum sortieren von Integerzahlen nach
     * dem Sortierverfahren Mergesort innerhalb der linken und rechten Grenze
     * 
     * @param objektMenge erwartet eine unsortierte Menge von Integerzahlen vom Typ int[]
     * @param linkeGrenze erwartet die Indexzahl der linken Grenze von der Menge
     * @param rechteGrenze erwartet die Indexzahl der rechten Grnze von der Menge
     * @return gibt eine sortierte Menge von Integerzahlen als int[] zurueck
     */
    private static int[] sortiereNachMergeSortRekursiv(int[] objektMenge, int linkeGrenze, int rechteGrenze){
        //wenn objektMenge <= 1 gib die Menge sofort zurück
        if((rechteGrenze - linkeGrenze) < 1) return objektMenge;
        //Zerlege n-elementige Teilfolge A in zwei Teilfolgen A1, A2 der Länge n/2
        int mitte = (rechteGrenze - linkeGrenze) / 2 + linkeGrenze;
        //Sortiere die zwei Teilfolgen rekursiv durch Merge-Sort; erhalte A´1, A´2
        //sortiere linke Haelfte
        if(linkeGrenze < mitte){
            sortiereNachMergeSortRekursiv(objektMenge, linkeGrenze, mitte);
        }
        //sortiere rechte Haelfte
        if((mitte + 1) < rechteGrenze){
            sortiereNachMergeSortRekursiv(objektMenge, mitte + 1, rechteGrenze);
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
        // Sicherung des Wertes der linken Grenze
        int backupLinkeGrenze = linkeGrenze;
        // Teilfeld A1 geht von Index linkeGrenze bis mitte
        // Teilfeld A2 geht von Index mitte+1 bis rechteGrenze
        // Vorbedingung: A1 ist sortiert und A2 ist sortiert
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
            if(objektMenge[pointerA1] < objektMenge[pointerA2]){
                tempArray[pointerTempArray] = objektMenge[pointerA1];
                // erhöhe akt. Index von Teilfeld mit kleinerem Wert
                pointerA1++;
            } else{
                tempArray[pointerTempArray] = objektMenge[pointerA2];
                // erhöhe akt. Index von Teilfeld mit kleinerem Wert
                pointerA2++;
            }
            // erhöhe aktuellen Index von tempArray
            pointerTempArray++;
        }
        // übertrage Werte des noch nicht vollständig durchlaufenen Teilfelds A1 nach tempArray
        while(pointerA1 <= mitte){
            tempArray[pointerTempArray] = objektMenge[pointerA1];
            // erhöhe akt. Index von Teilfeld A1
            pointerA1++;
            // erhöhe aktuellen Index von tempArray
            pointerTempArray++;
        }
        // übertrage Werte des noch nicht vollständig durchlaufenen Teilfelds A2 nach tempArray
        while(pointerA2 <= rechteGrenze){
            tempArray[pointerTempArray] = objektMenge[pointerA2];
            // erhöhe akt. Index von Teilfeld A2
            pointerA2++;
            // erhöhe aktuellen Index von tempArray
            pointerTempArray++;
        }
        // kopiere tempArray nach A (von Index backupLinkeGrenze bis Index rechteGrenze)
        for(int i : tempArray){
            objektMenge[backupLinkeGrenze] = i;
            backupLinkeGrenze++;
        }
    }
    
    /**
     * private statische Methode zum sortieren von beliebigen Objekten nach
     * dem Sortierverfahren Mergesort innerhalb der linken und rechten Grenze
     * 
     * @param objektMenge erwartet eine unsortierte Menge vom Typ List
     * @param linkeGrenze erwartet die Indexzahl der linken Grenze von der Menge
     * @param rechteGrenze erwartet die Indexzahl der rechten Grnze von der Menge
     * @return gibt eine sortierte Menge als List zurueck
     */
    private static List sortiereNachMergeSortRekursiv(List objektMenge, int linkeGrenze, int rechteGrenze){
        //wenn objektMenge <= 1 gib die Menge sofort zurück
        if((rechteGrenze - linkeGrenze) < 1) return objektMenge;
        //Zerlege n-elementige Teilfolge A in zwei Teilfolgen A1, A2 der Länge n/2
        int mitte = (rechteGrenze - linkeGrenze) / 2 + linkeGrenze;
        //Sortiere die zwei Teilfolgen rekursiv durch Merge-Sort; erhalte A´1, A´2
        //sortiere linke Haelfte
        if(linkeGrenze < mitte){
            sortiereNachMergeSortRekursiv(objektMenge, linkeGrenze, mitte);
        }
        //sortiere rechte Haelfte
        if((mitte + 1) < rechteGrenze){
            sortiereNachMergeSortRekursiv(objektMenge, mitte + 1, rechteGrenze);
        }  
        //Mische die beiden sortierten Teilfolgen A´1 und A´2 zu einer sortierten Gesamtfolge A´
        merge(objektMenge, linkeGrenze, mitte, rechteGrenze);
        
        return objektMenge;
    }
        
    /**
     * private Methode zum mischen sortierter Teilfolgen von linker bis rechter Grenze
     * 
     * @param objektMenge erwartet eine Menge von beliebigen Objekten
     * @param linkeGrenze ist die linke Grenze vom der Teilfolge
     * @param mitte ist die Mitte der Teilfolge
     * @param rechteGrenze ist die rechte Grenze der Teilfolge
     */
    private static void merge(List objektMenge, int linkeGrenze, int mitte, int rechteGrenze) {
        // Sicherung des Wertes der linken Grenze
        int backupLinkeGrenze = linkeGrenze;
        // Teilfeld A1 geht von Index linkeGrenze bis mitte
        // Teilfeld A2 geht von Index mitte+1 bis rechteGrenze
        // Vorbedingung: A1 ist sortiert und A2 ist sortiert
        // tempArray sei Hilfsfeld
        List tempList = objektMenge.subList(linkeGrenze, rechteGrenze);
        tempList.clear();
        // linkeGrenze aktueller Index A1, mitte+1 aktueller Index A2
        int pointerA1 = linkeGrenze;
        int pointerA2 = mitte + 1;
        // while (weder A1 noch A2 ist vollständig von links nach
        // rechts durchlaufen)
        while(pointerA1 <= mitte && pointerA2 <= rechteGrenze ){
            // kopiere kleineren aktuellen Wert von A1, A2 nach tempList
            if(((Comparable)objektMenge.get(pointerA1)).compareTo((Comparable)objektMenge.get(pointerA2)) == -1){
                tempList.add(objektMenge.get(pointerA1));
                // erhöhe akt. Index von Teilfeld mit kleinerem Wert
                pointerA1++;
            } else{
                tempList.add(objektMenge.get(pointerA2));
                // erhöhe akt. Index von Teilfeld mit kleinerem Wert
                pointerA2++;
            }
        }
        // übertrage Werte des noch nicht vollständig durchlaufenen Teilfelds A1 nach tempList
        while(pointerA1 <= mitte){
            tempList.add(objektMenge.get(pointerA1));
            // erhöhe akt. Index von Teilfeld A1
            pointerA1++;
        }
        // übertrage Werte des noch nicht vollständig durchlaufenen Teilfelds A2 nach tempList
        while(pointerA2 <= rechteGrenze){
            tempList.add(objektMenge.get(pointerA2));
            // erhöhe akt. Index von Teilfeld A2
            pointerA2++;
        }
        // kopiere tempList nach A (von Index backupLinkeGrenze bis Index rechteGrenze)
        Iterator it = tempList.iterator();
        while(it.hasNext()){
            objektMenge.set(backupLinkeGrenze, it.next());
            backupLinkeGrenze++;
        }
    }
}
