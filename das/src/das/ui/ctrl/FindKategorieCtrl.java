package das.ui.ctrl;
import das.bl.service.ZutatenService;
import das.util.ObjName;
import das.util.Query;
import das.util.QueryExpr;
import das.util.ResultType;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;


/**
 * Die controllerklasse zum Suchen und Auswaehlen von Kategorien.
 */
public class FindKategorieCtrl extends ControllerBase {
	
	private Collection<ObjName> results;
	private Collection<ObjName> kategorien;
	private Long katId;
	private String nameExpr;
	private String message;
	
	/**
	 * Liefert eine Liste von ObjNames fuer alle existierenden Kategorien.
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
			
			if (katId != null){
				q.addExpression(new QueryExpr("kat_id", katId)); 
			}
			else if (nameExpr != null){
				q.addExpression(new QueryExpr("name", nameExpr));
			}
			
			results = htmlEscape(service.findKategorien(q));
			if (results.size() == 0)
				message = "Kein Datensatz gefunden";
			
			fields.put("nameExpr", htmlEscape(nameExpr));
		}
		else if (command.equals("delete")){
			int count = 0;
			for (Object value : getFieldAsList("selected")){
				Long id = Long.valueOf((String)value);
				service.deleteKategorie(id);
				count++;
			}
			if (count == 1)
				message = "1 Datensatz gel&ouml;scht";
			else
				message = count + " Datens&auml;tze gel&ouml;scht";
		}
		else if (command.equals("new")){
			forward("/edit_kategorie.jsp?cmd=new");
		}
	}			
}
