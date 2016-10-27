
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Modul: Algorithmen
 * @Dozentin: Prof. Dr. H. Ripphausen-Lipa Beuth Hochschule für Technik
 * @Uebungsaufgabe: Implementierung des Sortierverfahren ...??????????????...
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

        List ausgangsArray = intArrayToList(FileIntArray.FileToIntArray("src/DateinIntZahlen/Rand10_1"));
        
        System.out.println(Sortierverfahren.sortiereListe(ausgangsArray));
    }

    /**
     * private Hilfsmethode zur Konvertierung von int[] zu List
     * Vorbereitung für Zusatzaufgabe
     * @param intMenge erwartet das eingelesene intArray von FileIntArray
     * @return gibt das neue List-objekt mit den Integer-werten zurück
     */
    private static List intArrayToList(int[] intMenge){
        List intList = new ArrayList(intMenge.length);

        for (int i = 0; i < intMenge.length; i++) {
            intList.add(intMenge[i]);
        };
        return intList;
    }
}
