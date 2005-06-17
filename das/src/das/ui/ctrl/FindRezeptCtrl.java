package das.ui.ctrl;
import das.bl.service.RezepteService;
import das.util.ObjName;
import das.util.Query;
import das.util.QueryExpr;
import das.util.ResultType;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;


/**
 * autor: Kirill 
 * date: 14.06.2005
 * 
 * Die controller klasse zum suchen und auswaehlen von rezepten.
 */
public class FindRezeptCtrl extends ControllerBase {
	
	private List<ObjName> results;

	//private Long katId;
        private String maxCal;
        private String maxFett;
        private String mindScore;
        private String anleitung;
        
	private String nameExpr;
	private String message;
	
	/**
	 * Liefert die liste der suchergebnisse.
	 */
	public List<ObjName> getResults(){
		return results;
	}

	/**
	 * Liefert eine meldung die dem user angezeigt werden kann 
	 * (z.b. fuer den fall dass keine datensaetze gefunden wurden).
	 */
	public String getMessage(){
		return message;
	}
	
	/**
	 * Konvertiert und validiert die request parameter
	 */
	protected boolean convertAndValidate(String command){
		if (command == null)
			return true;
		
		if (command.equals("find")){
			nameExpr = Convert.toString((String)fields.get("nameExpr"), "nameExpr", true,
				0, 100, errors);
			}
		

		return errors.isEmpty();
	}
	
	/**
	 * Fuehrt das gegebene kommando aus. Wenn das kommando "find" ist, wird eine
	 * abfrage ausgefuehrt und die ergebnisliste geladen. Wenn das kommando "delete" ist
	 * werden die ausgewaehlten datensaetze geloescht.
	 */
	protected void action(String command) throws ServletException, IOException {
		
		System.out.println("command: " + command);
		
		if (command == null)
			command = "find";

		RezepteService service = new RezepteService(getUserName());

                
		if (command.equals("find")){
			Query q = new Query(ResultType.NAMES);
			

			if (nameExpr != null){
				q.addExpression(new QueryExpr("name", nameExpr));
			}
			
			results = (service.findRezepte(q));
			if (results.size() == 0)
				message = "Kein Datensatz gefunden";
			
			fields.put("nameExpr", htmlEscape(nameExpr));
                
		}
		else if (command.equals("delete")){
			int count = 0;
			for (Object value : getFieldAsList("selected")){
				Long id = Long.valueOf((String)value);
				service.deleteRezept(id);
				count++;
			}
			if (count == 1)
				message = "1 Datensatz gel&ouml;scht: ";
			else
				message = count + " Datens&auml;tze gel&ouml;scht";
		}
		else if (command.equals("new")){
			forward("/edit_rezept.jsp?cmd=new");
		}
	}			
}
