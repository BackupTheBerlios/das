package das.ui.ctrl;

import das.bl.model.Zutat;
import das.bl.service.ZutatenService;
import static das.ui.ctrl.CtrlConstants.*;
import das.util.ObjName;
import das.util.Query;
import das.util.ResultType;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import javax.servlet.ServletException;

/**
 * Die Controllerklasse zum Anzeigen von Zutaten.
 */
public class ShowZutatUserCtrl extends ControllerBase {
	
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
		if (zutat == null)
			return Collections.emptySet();
		
		return htmlEscape(zutat.getAllergien());
	}	
                    
	/**
	 * Konvertiert und validiert die request parameter
	 */
	protected boolean convertAndValidate(String command){
		if (command == null)
			return true;
		
		return errors.isEmpty();
	}
	
	/**
	 * Fuehrt das gegebene kommando aus. 
	 */
	protected void action(String command) throws ServletException, IOException {
		if (command == null)
			return;
		
		ZutatenService service = new ZutatenService(getUserName());
		
		if (command.equals("show")){
			zutat = service.loadZutat(getLongParam("id", true));
			convert(TO_UI);
		}
	}	
	
	/**
	 * Konvertiert die werte aus der internen Repraesentation ind die Formularfelder als Strings 
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
	}
}
