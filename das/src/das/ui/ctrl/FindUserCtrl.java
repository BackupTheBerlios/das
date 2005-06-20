/**
 * author: Mario 
 * date: 17.06.2005
 */ 

package das.ui.ctrl;
import das.bl.service.UserService;
import das.util.ObjName;
import das.util.Query;
import das.util.QueryExpr;
import das.util.ResultType;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;



/**
 * Die controller klasse zum suchen und auswaehlen von User.
 */
public class FindUserCtrl extends ControllerBase {
	
	private Collection<ObjName> results;
	private Collection<ObjName> gruppen;
	private Long gruId;
	private String nameExpr;
	private String message;
	
	/**
	 * Liefert eine liste von ObjNames fuer alle existierenden Gruppen.
	 */
	public Collection<ObjName> getGruppen(){
		Query q = new Query(ResultType.NAMES);
		UserService service = new UserService(getUserName());
		return htmlEscape(service.findGruppen(q));
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
			gruId = Convert.toLong((String)fields.get("gru"), "gru", true, 
				0, 50, errors);
			nameExpr = Convert.toString((String)fields.get("nameExpr"), "nameExpr", true,
				0, 100, errors);
			
			if (gruId == null && nameExpr == null){
				error("W&auml;hlen Sie eine Gruppe"
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

		UserService service = new UserService(getUserName());

		if (command.equals("find")){
			Query q = new Query(ResultType.NAMES);
			
			if (gruId != null){
				q.addExpression(new QueryExpr("gru_id", gruId));
			}
			else if (nameExpr != null){
				q.addExpression(new QueryExpr("name", nameExpr));
			}
			
			results = htmlEscape(service.findUsers(q));
			if (results.size() == 0)
				message = "Kein Datensatz gefunden";
			
			fields.put("nameExpr", htmlEscape(nameExpr));
		}
		else if (command.equals("delete")){
			int count = 0;
			for (Object value : getFieldAsList("selected")){
				Long id = Long.valueOf((String)value);
				service.deleteUser(id);
				count++;
			}
			if (count == 1)
				message = "1 Datensatz gel&ouml;scht: ";
			else
				message = count + " Datens&auml;tze gel&ouml;scht";
		}
		else if (command.equals("new")){
			forward("/edit_user.jsp?cmd=new");
		}
	}			
}
