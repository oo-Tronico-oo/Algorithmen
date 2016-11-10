/**
 * @Modul: Algorithmen
 * @Dozentin: Prof. Dr. H. Ripphausen-Lipa Beuth Hochschule für Technik
 * @Uebungsaufgabe: Implementierung des Sortierverfahren Mergesort
 * @author Lisa Bitterling, Katrin Schulze, Nico Nauendorf
 * @erstellt am 27. Oktober 2016
 */
import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testklasse zum testen des Sortierverfahrens Mergesort
 */
public class SortierverfahrenTest {
    //Array mit Dateien
    File[] dateienArray;
    
    public SortierverfahrenTest() {
    }
    
    @Before
    public void setUp() {
        //gib alle Dateien im Ordner src/DateinIntZahlen
        dateienArray = new File("src/DateinIntZahlen").listFiles();
    }
    
    /**
     * Test of sortiereNachMergeSort method, of class Sortierverfahren.
     */
    @Test
    public void testSortiereNachMergeSort() {
        System.out.println("sortiereNachMergeSort");
        int[] objektMenge;
        int[] result;
        //prüfe die Arrays aller Dateien
        for(File i : dateienArray){
            //lese die Datei ein und generiere ein int[]
            objektMenge = FileIntArray.FileToIntArray(i.getPath());
            //starte sortiere das Ausgangsarry
            result = Sortierverfahren.sortiereNachMergeSort(objektMenge);
            assertTrue("Sortierung der Datei " + i.getName(), pruefung(result));
        }
    }
    
    /**
     * ausgelagerte prüfung ob die Reihenfolge der Daten richtig ist
     * @param array erwartet eine Menge von intZahlen
     * @return true wenn Reihenfolge korrekt, ansonsten false
     */
    private boolean pruefung(int[] array){
        //überprüft jedes Element im array ob es kleiner als das Element rechts daneben ist
        for(int i = 0; i < array.length - 1; i++){
            if(array[i] > array[i+1]){
                return false;
            }
        }
        return true;
    }
}
