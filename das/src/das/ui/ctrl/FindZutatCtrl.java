package das.ui.ctrl;
import das.bl.service.ZutatenService;
import das.util.ObjName;
import das.util.Query;
import das.util.QueryExpr;
import das.util.ResultType;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;

public class FindZutatCtrl extends ControllerBase {
	
	private List<ObjName> results;
	private List<ObjName> kategorien;
	private Long katId;
	private String nameExpr;
	private String message;
	
	public List<ObjName> getKategorien(){
		Query q = new Query(ResultType.NAMES);
		ZutatenService service = new ZutatenService(getUserName());
		return htmlEscape(service.findKategorien(q));
	}
	
	public List<ObjName> getResults(){
		return results;
	}
	
	public String getMessage(){
		return message;
	}
	
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
		else if (command.equals("delete")){
			int count = 0;
			for (Object value : getFieldAsList("selected")){
				Long id = Long.valueOf((String)value);
				service.deleteZutat(id);
				count++;
			}
			if (count == 1)
				message = "1 Datensatz gel&ouml;scht: ";
			else
				message = count + " Datens&auml;tze gel&ouml;scht";
		}
		else if (command.equals("new")){
			forward("/edit_zutat.jsp?cmd=new");
		}
	}			
}
