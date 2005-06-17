package das.ui.ctrl;
/*
 * date: 14.06.2005
 * autor: Kirill
 */
import das.bl.model.Rezept;
import das.bl.service.RezepteService;
import das.bl.service.ZutatenService;
import static das.ui.ctrl.CtrlConstants.*;
import das.util.ObjName;
import das.util.Query;
import das.util.ResultType;
import java.io.IOException;
import java.util.List;
import java.util.Collection;
import javax.servlet.ServletException;

/**
 * Die Controller klasse zur aenderung und neu erzeugung von Rezepten.
 */
public class EditRezeptCtrl extends ControllerBase {
	private List<ObjName> zutaten;
	private List<ObjName> kategorien;
        private Rezept rezept;
        

	public List<ObjName> getZutaten(){
		Query q = new Query(ResultType.NAMES);
		ZutatenService service = new ZutatenService(getUserName());
		return service.findZutaten(q);
	}
                    
	/**
	 * Konvertiert und validiert die request parameter
	 */
	protected boolean convertAndValidate(String command){
		if (command == null)
			return true;
		
		if (command.equals("save")){
			rezept = new Rezept();
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
		
		RezepteService service = new RezepteService(getUserName());
		
		if (command.equals("new")){
			rezept = new Rezept();
			convert(TO_UI);
		}
		else if ((command.equals("edit")) || (command.equals("view"))){
			rezept = service.loadRezept(getLongParam("id", true));
			convert(TO_UI);
		}
		else if (command.equals("save")){
			service.saveRezept(rezept);
			convert(TO_UI);
		}
		else if (command.equals("delete")){
			service.deleteRezept(getLongParam("id", true));
			forward(DELETED_PAGE + "?msg=Rezept");
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
			fields.put("id", Convert.fromNumber(rezept.getId()));
			fields.put("name", Convert.fromString(rezept.getName()));
			fields.put("anleitung", Convert.fromString(rezept.getAnleitung()));
		}
		else if (direction == FROM_UI){
			rezept.setId(Convert.toLong(
				(String)fields.get("id"), "ID", true, 0, Long.MAX_VALUE, errors));
			rezept.setName(Convert.toString(
				(String)fields.get("name"), "Name", false, 1, 200, errors));
			rezept.setAnleitung(Convert.toString(
				(String)fields.get("anleitung"), "Anleitung", false, 1, 2000, errors));
		
		}
	}
}
