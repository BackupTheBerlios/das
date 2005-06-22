package das.ui.ctrl;
import das.bl.service.*;
import das.util.ObjName;
import das.util.Query;
import das.util.QueryExpr;
import das.bl.model.*;
import das.util.ResultType;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * autor: Kirill 
 * date: 14.06.2005
 * 
 * Die controller klasse zum suchen und auswaehlen von rezepten.
 */
public class FindRezeptCtrl extends ControllerBase {
	
	private List<ObjName> results;

        private String maxCal;
        private String maxFett;
        private String mindScore;
        private String anleitung;
        
	private String nameExpr;
	private String message;

        /* liefert TRUE wenn der eingellogter user die editor rechte hat. */
       	public boolean isEditor(){				
		HttpServletRequest httpRequest = (HttpServletRequest)pageContext.getRequest();
		return httpRequest.isUserInRole("editors") || httpRequest.isUserInRole("admins");
	}
        
        /* liefert TRUE wenn der current user tatsachlich den autor des rezepts ist */
        public boolean isAutor(Long rID){
            RezepteService service = new RezepteService(getUserName());
            System.out.println(service.loadRezept(rID).getBenutzer());
            return getUserName().equals(service.loadRezept(rID).getBenutzer());
	}
        
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
			command = "find";
		
		if (command.equals("find")){
			nameExpr = Convert.toString((String)fields.get("nameExpr"), "nameExpr", true,
				0, 200, errors);
			}
		return errors.isEmpty();
	}
	
	/**
	 * Fuehrt das gegebene kommando aus. Wenn das kommando "find" ist, wird eine
	 * abfrage ausgefuehrt und die ergebnisliste geladen. Wenn das kommando "delete" ist
	 * werden die ausgewaehlten datensaetze geloescht.
	 */
	protected void action(String command) throws ServletException, IOException {
		
		if (command == null)
			command = "find";

		RezepteService service = new RezepteService(getUserName());

                
		if (command.equals("find")){
			Query q = new Query(ResultType.OBJECTS);
			

			if (nameExpr != null){
				q.addExpression(new QueryExpr("name", nameExpr));
			}
			
			results = (service.findRezepte(q));
			if (results.size() == 0)
				message = "Kein Datensatz gefunden";
			
			fields.put("nameExpr", htmlEscape(nameExpr));
                
		}
		else if (command.equals("delete")){

				message = "1 Datensatz gel&ouml;scht";

		}
		else if (command.equals("new")){
			forward("/edit_rezept.jsp?cmd=new");
		}
	}			
}
