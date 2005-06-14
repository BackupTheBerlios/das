package das.ui.ctrl;

import das.bl.model.Kategorie;
import das.bl.service.ZutatenService;
import static das.ui.ctrl.CtrlConstants.*;
import das.util.ObjName;
import das.util.Query;
import das.util.ResultType;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;

/**
 * Die Controllerklasse zur Aenderung und Neu erzeugen von Kategorien.
 */
public class EditKategorieCtrl extends ControllerBase {
	
	private Kategorie kategorie;
         
	/**
	 * Konvertiert und validiert die request Parameter
	 */
	protected boolean convertAndValidate(String command){
		if (command == null)
			return true;
		
		if (command.equals("save")){
			kategorie = new Kategorie();
			convert(FROM_UI);			
		}
		
		return errors.isEmpty();
	}
	
	/**
	 * Fuehrt das gegebene Kommando aus. 
	 */
	protected void action(String command) throws ServletException, IOException {
		if (command == null)
			return;
		
		ZutatenService service = new ZutatenService(getUserName());
		
		if (command.equals("new")){
			kategorie = new Kategorie();
			convert(TO_UI);
		}
		else if (command.equals("edit")){
			kategorie = service.loadKategorie(getLongParam("id", true));
			convert(TO_UI);
		}
		else if (command.equals("save")){
			service.saveKategorie(kategorie);
			convert(TO_UI);
		}
		else if (command.equals("delete")){
			service.deleteKategorie(getLongParam("id", true));
			forward(DELETED_PAGE + "?msg=Kategorie");
		}
	}	
	
	/**
	 * Konvertiert die Werte aus den Formularfeldern von Strings in die interne Repraesentation
	 * oder umgekehrt.
	 *
	 * @param direction Entweder FROM_UI oder TO_UI je nachdem ob die Werte von der
	 * UI Repraesentation in die interne Repraesentatoin oder umgekehrt konvertiert
	 * werden sollen.
	 */
	protected void convert(int direction){
		if (direction == TO_UI){
			fields.put("id", Convert.fromNumber(kategorie.getId()));
			fields.put("name", Convert.fromString(kategorie.getName()));
		}
		else if (direction == FROM_UI){
			kategorie.setId(Convert.toLong(
				(String)fields.get("id"), "ID", true, 0, Long.MAX_VALUE, errors));
			kategorie.setName(Convert.toString(
				(String)fields.get("name"), "Name", false, 1, 100, errors));			
		}
	}
}
