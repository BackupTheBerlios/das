package das.ui.ctrl;

import das.bl.model.Rezept;
import das.bl.model.Zutat;
import das.bl.service.RezepteService;
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
 * Die Controllerklasse zum Anzeigen von Rezepten.
 */
public class ShowRezeptCtrl extends ControllerBase {
    
    private Rezept rezept;
    
    /**
     * Konvertiert und validiert die request parameter
     */
    protected boolean convertAndValidate(String command){
        if (command == null)
            return true;
        
        return errors.isEmpty();
    }
    
    /**
     * Laedet den Rezept ein.
     */
    protected void action(String command) throws ServletException, IOException {
        RezepteService service = new RezepteService(getUserName());
        rezept = service.loadRezept(getLongParam("id", true));
        convert(TO_UI);
    }
    
    /**
     * Konvertiert die werte aus der internen Repraesentation ind die Formularfelder als Strings
     */
    protected void convert(int direction){
        if (direction == TO_UI){
            fields.put("id", Convert.fromNumber(rezept.getId()));
            fields.put("name", Convert.fromString(rezept.getName()));
            fields.put("anleitung", Convert.fromString(rezept.getAnleitung()));
        }
    }
}
