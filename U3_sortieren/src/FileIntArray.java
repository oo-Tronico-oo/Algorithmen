import java.io.*;
import java.util.*;


public class FileIntArray {
	/*****************************************************************************
	 * Funktion FileToIntAttay liest Werte aus Datei dat und erzeugt ein int-Array
	 * in der die gelesenen Werte in derselben Reihenfolge wie in dat abgelegt werden
	 * @param dat eine Datei mit int-Werten in folgendem Format: 1. Zeile Anzahl Werte (n)
	 * Zeile 2 .. (n+1) : jeweils ein int-Wert
	 * @return int-Array mit gelesenen Werten
	 */
	public static int[] FileToIntArray(String dat) {
		int [] A = null;
		FileInputStream fis = null;
		try {
		  fis = new FileInputStream(dat);
		}
		catch ( Exception e) {
			System.out.println("'dat' konnte nicht geoeffnet werden");

		}
		try {
		      InputStreamReader isr   = new InputStreamReader(fis);
		      BufferedReader    br = new BufferedReader   (isr);

		      String einZeile;
		      einZeile = br.readLine();
		      int anz = new Integer(einZeile);

		      A = new int[anz];
		      for (int i = 0; i < anz; i++){
		         einZeile = br.readLine();

		         if (einZeile == null) break;     // Ende der Datei erreicht
		         System.out.println(einZeile);
		         int j = new Integer(einZeile);
		         A[i] = j;
		      }
		  }
		  catch (Exception e) {
				System.out.println("Einlesen nicht erfolgreich");
		  }

		return A;
	}

}