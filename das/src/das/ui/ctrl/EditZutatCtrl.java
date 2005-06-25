package das.ui.ctrl;
import das.IntegrityConstraintException;
import das.bl.model.Zutat;
import das.bl.service.ZutatenService;
import static das.ui.ctrl.CtrlConstants.*;
import das.util.ObjName;
import das.util.Query;
import das.util.ResultType;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.servlet.ServletException;

/**
 * Die Controller klasse zur aenderung und neu erzeugung von zutaten.
 */
public class EditZutatCtrl extends ControllerBase {
	
	private Zutat zutat;
	
	/**
	 * Liefert eine liste von ObjNames aller kategorien.
	 */
	public Collection<ObjName> getKategorien(){
		Query q = new Query(ResultType.NAMES);
		ZutatenService service = new ZutatenService(getUserName());
		return htmlEscape(service.findKategorien(q));
	}	
        
   /**
	 * Liefert eine Liste von ObjNames aller Allergien.
	*/ 
	public Collection<ObjName> getAllergien(){                 
		Query q = new Query(ResultType.NAMES);
		ZutatenService service = new ZutatenService(getUserName());
		return htmlEscape(service.findAllergien(q));
	}	
	
	public Map getSelectedAllergien(){		
		Map result = new TreeMap();
		
		if (zutat != null){
			for (ObjName n : zutat.getAllergien()){
				result.put(n.getId(), "checked");
			}
		}
		
		return result;
	}
                    
	/**
	 * Konvertiert und validiert die request parameter
	 */
	protected boolean convertAndValidate(String command){
		if (command == null)
			return true;
		
		if (command.equals("save")){
			zutat = new Zutat();
			convert(FROM_UI);			
		}
		
		return errors.isEmpty();
	}
	
	/**
	 * Fuehrt das gegebene kommando aus. 
	 */
	protected void action(String command) throws ServletException, IOException {
		if (command == null)
			return;
		
		ZutatenService service = new ZutatenService(getUserName());
		
		if (command.equals("new")){
			zutat = new Zutat();
			convert(TO_UI);
		}
		else if (command.equals("edit")){
			zutat = service.loadZutat(getLongParam("id", true));
			convert(TO_UI);
		}
		else if (command.equals("save")){
			if (service.zutatExists(zutat)){
				error("Eine Zutat mit diesem Namen existiert bereits");
			}
			else {
				service.saveZutat(zutat);
				convert(TO_UI);
			}
		}
		else if (command.equals("delete")){
			try {
				service.deleteZutat(getLongParam("id", true));
				forward(DELETED_PAGE + "?msg=Zutat");
			}
			catch(IntegrityConstraintException ex){
				error("Die Zutat kann nicht gel&ouml;scht werden, "
						+ "solange sie in einem Rezept verwendet wird");
			}
		}
	}	
	
	/**
	 * Konvertiert die werte aus den formularfeldern von Strings in die interne repraesentation
	 * oder umgekehrt.
	 *
	 * @param direction Entweder FROM_UI oder TO_UI je nachdem ob die werte von der
	 * UI repraesentation in die interne repraesentatoin oder umgekehrt konvertiert
	 * werden sollen.
	 */
	protected void convert(int direction){
		if (direction == TO_UI){
			fields.put("id", Convert.fromNumber(zutat.getId()));
			fields.put("name", Convert.fromString(zutat.getName()));
			fields.put("einheit", Convert.fromString(zutat.getEinheit()));
			fields.put("kalorien", Convert.fromNumber(zutat.getKalorien()));
			fields.put("fett", Convert.fromNumber(zutat.getFett()));
			fields.put("zucker", Convert.fromNumber(zutat.getZucker()));
			fields.put("katId", Convert.fromNumber(zutat.getKatId()));
		}
		else if (direction == FROM_UI){
			zutat.setId(Convert.toLong(
				(String)fields.get("id"), "ID", true, 0, Long.MAX_VALUE, errors));
			zutat.setName(Convert.toString(
				(String)fields.get("name"), "Name", false, 1, 100, errors));
			zutat.setEinheit(Convert.toString(
				(String)fields.get("einheit"), "Einheit", false, 1, 20, errors));
			zutat.setKalorien(Convert.toFloat(
				(String)fields.get("kalorien"), "Kalorien", false, 0, 10000, errors));
			zutat.setFett(Convert.toInteger(
				(String)fields.get("fett"), "Fett", true, 0, 100, errors));
			zutat.setZucker(Convert.toInteger(
				(String)fields.get("zucker"), "Zucker", true, 0, 100, errors));
			zutat.setKatId(Convert.toLong(
				(String)fields.get("katId"), "Kategorie", false, 0, Long.MAX_VALUE, errors));
			allergienFromUi();
		}
	}
	
	protected void allergienFromUi(){
		List<String> allergienList = getFieldAsList("selectedAllergien");
		if (allergienList.isEmpty())
			return;
		
		Set<ObjName> allergien = new TreeSet<ObjName>();
		for (String s : allergienList){
			allergien.add(new ObjName(Long.valueOf(s), s));
		}
		
		zutat.setAllergien(allergien);
	}
}
