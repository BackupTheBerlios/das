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


/**
 * Die controller klasse zum suchen und auswaehlen von zutaten.
 */
public class FindZutatUserCtrl extends ControllerBase {
	
	private Collection<ObjName> results;
	private Collection<ObjName> kategorien;
	private Long katId;
	private String nameExpr;
	private String message;
	
	/**
	 * Liefert eine liste von ObjNames fuer alle existierenden kategorien.
	 */
	public Collection<ObjName> getKategorien(){
		Query q = new Query(ResultType.NAMES);
		ZutatenService service = new ZutatenService(getUserName());
		return htmlEscape(service.findKategorien(q));
	}
	
	/**
	 * Liefert die liste der suchergebnisse.
	 */
	public Collection<ObjName> getResults(){
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
			katId = Convert.toLong((String)fields.get("kat"), "kat", true, 
				0, Long.MAX_VALUE, errors);
			nameExpr = Convert.toString((String)fields.get("nameExpr"), "nameExpr", true,
				0, 100, errors);
			
			if (katId == null && nameExpr == null){
				error("W&auml;hlen Sie eine Kategorie"
					+ " oder geben Sie einen Suchbegriff ein");
			}
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
			command = "view";

		ZutatenService service = new ZutatenService(getUserName());

		if (command.equals("find")){
			Query q = new Query(ResultType.NAMES);
			
			if (katId != null){
				q.addExpression(new QueryExpr("kat_id", katId));
			}
			else if (nameExpr != null){
				q.addExpression(new QueryExpr("name", nameExpr));
			}
			
			results = htmlEscape(service.findZutaten(q));
			if (results.size() == 0)
				message = "Kein Datensatz gefunden";
			
			fields.put("nameExpr", htmlEscape(nameExpr));
		}
	}			
}
