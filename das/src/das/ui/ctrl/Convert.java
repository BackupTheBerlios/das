package das.ui.ctrl;

import das.DasException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;
import java.util.Map;


/**
 * Utility methoden zur konvertierung und validierung. Alle methoden in dieser klasse
 * nehmen unter anderem eine fehler Map als parameter, in die sie im fehlerfall eine
 * fehlermeldung einfuegen. Die konverterung in beide richtungen erfolgt entsprechend
 * der laenderkonventionen die durch die LOCALE konstante gegeben sind.
 *
 * @author k
 */
public class Convert {
	
	public static final Locale LOCALE = Locale.GERMAN;
	
	/**
	 * Validiert einen string.
	 *
	 * @param value der zu validierende wert.
	 * @param field der name des formularfeldes aus dem der wert stammt.
	 * @param allowNull true wenn das feld leer sein darf, sonst false.
	 * @param minLen die minimale laenge des strings.
	 * @param maxLen die maximale laenge des strings.
	 * @param errors die fehler Map.
	 */
	public static String toString(String value, String field, boolean allowNull,
		int minLen, int maxLen, Map<String,String> errors){
		
		value = normalize(value);
		if (value == null){
			checkNull(value, field, allowNull, errors);
		}
		else {
			int len = value.length();
			if (len < minLen || len > maxLen)
				errors.put(field, field + " muss zwischen " + minLen 
					+ " und " + maxLen + " zeichen haben");
		}
		
		return value;
	}

	/**
	 * Konvertiert einen string von der internen repraesentation in eine HTML taugliche.
	 */
	public static String fromString(String value){
		return WebUtil.htmlEscape(value);
	}
		
	/**
	 * Konvertiert einen String wert in einen Integer und validiert ihn entsprechend der gegebenen
	 * parameter.
	 *
	 * @param value der zu validierende wert.
	 * @param field der name des formularfeldes aus dem der wert stammt.
	 * @param allowNull true wenn das feld leer sein darf, sonst false.
	 * @param min der kleinste erlaubte wert
	 * @param max der groesste erlaubte wert
	 * @param errors die fehler Map.
	 */
	public static Integer toInteger(String value, String field, boolean allowNull,
		int min, int max, Map<String,String> errors){
		
		BigDecimal bd = parseNumber(value, field, allowNull, true, 
			BigDecimal.valueOf(min), BigDecimal.valueOf(max), errors);
		
		if (bd != null)
			return bd.intValue();
		
		return null;
	}

	/**
	 * Konvertiert einen String wert in einen Long und validiert ihn entsprechend der gegebenen
	 * parameter.
	 *
	 * @param value der zu validierende wert.
	 * @param field der name des formularfeldes aus dem der wert stammt.
	 * @param allowNull true wenn das feld leer sein darf, sonst false.
	 * @param min der kleinste erlaubte wert
	 * @param max der groesste erlaubte wert
	 * @param errors die fehler Map.
	 */	
	public static Long toLong(String value, String field, boolean allowNull,
		long min, long max, Map<String,String> errors){
		
		BigDecimal bd = parseNumber(value, field, allowNull, true, 
			BigDecimal.valueOf(min), BigDecimal.valueOf(max), errors);
		
		if (bd != null)
			return bd.longValue();
		
		return null;
	}	
	
	/**
	 * Konvertiert einen String wert in einen Double und validiert ihn entsprechend der gegebenen
	 * parameter.
	 *
	 * @param value der zu validierende wert.
	 * @param field der name des formularfeldes aus dem der wert stammt.
	 * @param allowNull true wenn das feld leer sein darf, sonst false.
	 * @param min der kleinste erlaubte wert
	 * @param max der groesste erlaubte wert
	 * @param errors die fehler Map.
	 */		
	public static Double toDouble(String value, String field, boolean allowNull,
		double min, double max, Map<String,String> errors){
		
		BigDecimal bd = parseNumber(value, field, allowNull, false, 
			BigDecimal.valueOf(min), BigDecimal.valueOf(max), errors);
		
		if (bd != null)
			return bd.doubleValue();
		
		return null;
	}

	/**
	 * Konvertiert einen String wert in einen Float und validiert ihn entsprechend der gegebenen
	 * parameter.
	 *
	 * @param value der zu validierende wert.
	 * @param field der name des formularfeldes aus dem der wert stammt.
	 * @param allowNull true wenn das feld leer sein darf, sonst false.
	 * @param min der kleinste erlaubte wert
	 * @param max der groesste erlaubte wert
	 * @param errors die fehler Map.
	 */	
	public static Float toFloat(String value, String field, boolean allowNull,
		float min, float max, Map<String,String> errors){
		
		BigDecimal bd = parseNumber(value, field, allowNull, false, 
			BigDecimal.valueOf(min), BigDecimal.valueOf(max), errors);
		
		if (bd != null)
			return (float)bd.doubleValue();
		
		return null;
	}

	/**
	 * Konvertiert einen Number wert in einen UI tauglichen String entsprechend der
	 * durch LOCALE gegebenen laenderkonventionen.
	 */
	public static String fromNumber(Number nbr){
		if (nbr == null)
			return null;
		
      NumberFormat nf = NumberFormat.getInstance(LOCALE);
      nf.setMaximumFractionDigits(4);
      return nf.format(nbr);		
	}

	/**
	 * Normalisiert einen string wert, sodass er einen leerzeichen hinten oder vorne
	 * enthaelt und in einen null wert umgewandelt wird, falls der string leer ist.
	 */
	public static String normalize(String s){
		if (s == null)
			return null;
		
		s = s.trim();
		if (s.equals(""))
			s = null;
		
		return s;
	}
	
	/**
	 * Parst eine zahl.
	 */
   private static BigDecimal parseNumber(String s, String field, boolean allowNull,
			boolean isInt, BigDecimal min, BigDecimal max, Map<String,String> errors) {
		
		s = normalize(s);
		
		if (s == null){
			checkNull(s, field, allowNull, errors);	
			return null;
		}
		
		String zahl = isInt ? "Ganzzahl" : "Zahl";
		String msg = field + " enhaelt keine gueltige " + zahl;
      DecimalFormat nf = (DecimalFormat)NumberFormat.getInstance(LOCALE);
      nf.setParseBigDecimal(true);
      ParsePosition pos = new ParsePosition(0);
      BigDecimal bd = (BigDecimal)nf.parse(s, pos);
      
      if (pos.getIndex() != s.length()){
         errors.put(field, msg);
			return null;
		}		
		
		if (isInt && bd.scale() > 0){
			errors.put(field, msg);
			return null;
		}
		
		if (bd.compareTo(min) < 0 || bd.compareTo(max) > 0){
			errors.put(field, field + " muss eine " + zahl + " zwischen " + fromNumber(min) 
				+ " und " + fromNumber(max) + " enthalten");
			return null;
		}

      return bd;
   }
	
	/**
	 * Ueberprueft ob ein feld die null regel verletzt.
	 */
	private static boolean checkNull(String value, String field, boolean allowNull, 
		Map<String,String> errors){

		if (value == null && !allowNull){
			errors.put(field, field + " darf nicht leer sein");
			return false;
		}
		
		return true;
	}
	
}
