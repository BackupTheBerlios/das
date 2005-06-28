package das.ui.ctrl;
import das.bl.service.ZutatenService;
import das.util.ObjName;
import das.util.Query;
import das.util.QueryExpr;
import das.util.ResultType;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import das.IntegrityConstraintException;


/**
 * Die Controllerklasse zum Suchen und Auswaehlen von Allergien.
 */
public class FindAllergieCtrl extends ControllerBase {
	
	private Collection<ObjName> results;
	private Collection<ObjName> allergien;
	private Long alId;
	private String nameExpr;
	private String message;
	
	/**
	 * Liefert eine Liste von ObjNames fuer alle existierenden Allergien.
	 */
	public Collection<ObjName> getAllergien(){
		Query q = new Query(ResultType.NAMES);
		ZutatenService service = new ZutatenService(getUserName());
		return htmlEscape(service.findAllergien(q));
	}
	
	/**
	 * Liefert die Liste der Suchergebnisse.
	 */
	public Collection<ObjName> getResults(){
		return results;
	}

	/**
	 * Liefert eine Meldung die dem User angezeigt werden kann 
	 * (z.b. fuer den Fall dass keine Datensaetze gefunden wurden).
	 */
	public String getMessage(){
		return message;
	}
	
	/**
	 * Konvertiert und validiert die request Parameter
	 */
	protected boolean convertAndValidate(String command){
		if (command == null)
			return true;
		
		if (command.equals("find")){
			alId = Convert.toLong((String)fields.get("al"), "al", true, 
				0, Long.MAX_VALUE, errors);
			nameExpr = Convert.toString((String)fields.get("nameExpr"), "nameExpr", true,
				0, 100, errors);
			
			if (alId == null && nameExpr == null){
				error("W&auml;hlen Sie eine Allergie"
					+ " oder geben Sie einen Suchbegriff ein");
			}
		}

		return errors.isEmpty();
	}
	
	/**
	 * Fuehrt das gegebene Kommando aus. Wenn das Kommando "find" ist, wird eine
	 * Abfrage ausgefuehrt und die Ergebnisliste geladen. Wenn das Kommando "delete" ist,
	 * werden die ausgewaehlten Datensaetze geloescht.
	 */
	protected void action(String command) throws ServletException, IOException {
		
		System.out.println("command: " + command);
		
		if (command == null)
			command = "view";

		ZutatenService service = new ZutatenService(getUserName());

		if (command.equals("find")){
			Query q = new Query(ResultType.NAMES);
			
			if (alId != null){
				q.addExpression(new QueryExpr("al_id", alId)); 
			}
			else if (nameExpr != null){
				q.addExpression(new QueryExpr("name", nameExpr));
			}
			
			results = htmlEscape(service.findAllergien(q));
			if (results.size() == 0)
				message = "Kein Datensatz gefunden";
			
			fields.put("nameExpr", htmlEscape(nameExpr));
		}
		else if (command.equals("delete")){
                        boolean constraintViolation = false;
			int count = 0;
			for (Object value : getFieldAsList("selected")){
                                try {
                                        Long id = Long.valueOf((String)value);
                                        service.deleteAllergie(id);
                                        count++;
                                }
                                catch(IntegrityConstraintException ex){
					constraintViolation = true;
				}
			}
			if (constraintViolation){
				error("Mindestens eine der ausgew&auml;hlten Allergien konnte nicht " +
						"gel&ouml;escht werden, weil noch User und/oder Nahrungsmittel darauf verweisen.");				
			}                        
			if (count == 1)
				message = "1 Datensatz gel&ouml;scht";
			else
				message = count + " Datens&auml;tze gel&ouml;scht";
		}
		else if (command.equals("new")){
			forward("/edit_allergie.jsp?cmd=new");
		}
	}			
}
