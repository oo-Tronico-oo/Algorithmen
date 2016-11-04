
import java.util.ArrayList;
import java.util.List;

/**
 * @Modul: Algorithmen
 * @Dozentin: Prof. Dr. H. Ripphausen-Lipa Beuth Hochschule für Technik
 * @Uebungsaufgabe: Implementierung des Sortierverfahren Mergesort
 * @author Lisa Bitterling, Katrin Schulze, Nico Nauendorf
 * @erstellt am 27. Oktober 2016
 */
public class Main {

    /**
     * Methode zum starten der Anwendung
     *
     * @param args Array von Parametern, die beim start der Anwendung übergeben
     * wurden
     */
    public static void main(String[] args) {
        //lese die Datei ein und generiere ein int[]
        int[] ausgangsArray = FileIntArray.FileToIntArray("src/DateinIntZahlen/Rand10_1");
        Sortierverfahren.sortiereNachMergeSort(ausgangsArray, 0, ausgangsArray.length - 1);
    }

    /**
     * private Hilfsmethode zur Konvertierung von int[] zu List
     * Vorbereitung für Zusatzaufgabe
     * @param intMenge erwartet das eingelesene intArray von FileIntArray
     * @return gibt das neue List-objekt mit den Integer-werten zurück
     */
    private static List intArrayToList(int[] intMenge){
        List intList = new ArrayList(intMenge.length);
        
        //kopieren der Elemente aus int[] in List
        for (int i = 0; i < intMenge.length; i++) {
            intList.add(intMenge[i]);
        }
        
        return intList;
    }
}
