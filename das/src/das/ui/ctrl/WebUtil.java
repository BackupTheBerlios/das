package das.ui.ctrl;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility methoden fuer web anwendungen.
 *
 * @author k
 */
public class WebUtil {
   
   public static String htmlEscape(String s){
      if (s == null)
         return null;
      
      StringBuilder sb = new StringBuilder(s.length() + 10);
      
      for (int i = 0; i < s.length(); ++i){
         char c = s.charAt(i);
         switch(c){
            case '<':
               sb.append("&lt;"); break;
            case '>':
               sb.append("&gt;"); break;
            case '\"':
               sb.append("&quot;"); break;
            case '\'':
               sb.append("&#039;"); break;
            case '\\':
               sb.append("&#029"); break;
            case '&':
               sb.append("&amp;"); break;
            default:
               sb.append(c);
         }
      }
      
      return sb.toString();
   }
      
   private WebUtil() {}
   
}
