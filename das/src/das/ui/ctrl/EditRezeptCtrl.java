package das.ui.ctrl;
/*
 * date: 14.06.2005
 * autor: Kirill
 */
import com.sun.java_cup.internal.runtime.Symbol;
import das.DasException;
import das.bl.model.*;
import das.bl.service.*;
import static das.ui.ctrl.CtrlConstants.*;
import das.util.ObjName;
import das.util.Query;
import das.util.ResultType;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

/**
 * Die Controller klasse zur aenderung und neu erzeugung von Rezepten.
 */
public class EditRezeptCtrl extends ControllerBase {
    private Rezept rezept;
    
    public List<Zutat> getZutaten(){
        Query q = new Query(ResultType.OBJECTS);
        ZutatenService service = new ZutatenService(getUserName());
        return service.findZutaten(q);
    }
    
    public Map<Long,Long> getRezZutaten(){
        return rezept.zutaten;
    }
    
    /**
     * Liefert eine liste von ObjNames aller kategorien.
     */
    public List<Kategorie> getKategorien(){
        Query q = new Query(ResultType.OBJECTS);
        ZutatenService service = new ZutatenService(getUserName());
        return service.findKategorien(q);
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
            //pruefe das name eindeutig ist
            validateName();
        } else if(command.equals("edit")){
            convert(FROM_UI);
        }
        
        
        return errors.isEmpty();
    }
    
    /**
     * Fuehrt das gegebene kommando aus.
     */
    protected void action(String command) throws ServletException, IOException {
        if (command == null)
            command = "edit";
        
        RezepteService service = new RezepteService(getUserName());
        
        if (command.equals("new")){
            rezept = new Rezept();
            rezept.setBenutzer(getUserName());
            convert(TO_UI);
        } else if ((command.equals("edit")) || (command.equals("view"))){
            rezept = service.loadRezept(getLongParam("id", true));
            convert(TO_UI);
        } else if (command.equals("save")){
            rezept.setBenutzer(getUserName());
            try{
            service.saveRezept(rezept);
            }catch(DasException de){
                errors.put("rezeptExists", "Rezept mit Name '"+rezept.getName()+"' existiert bereits");
                valid = false;
            }
            convert(TO_UI);
        } else if (command.equals("delete")){
            service.deleteRezept(getLongParam("id", true));
            forward("find_rezept.jsp?cmd=find");
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
        } else if (direction == FROM_UI){
            rezept.setId(Convert.toLong(
                    (String)fields.get("id"), "ID", true, 0, Long.MAX_VALUE, errors));
            rezept.setName(Convert.toString(
                    (String)fields.get("name"), "Name", false, 1, 200, errors));
            rezept.setAnleitung(Convert.toString(
                    (String)fields.get("anleitung"), "Anleitung", false, 1, 5000, errors));
            convertZutaten();
            
            
        }
    }
    
    protected void validateName(){
        RezepteService service = new RezepteService(getUserName());
        if(service.nameExists(rezept)){
            errors.put("nameExists", "Rezept mit diesen Namen existiert bereit");
        }
    }
    
    /* sucht und valiediert die zutaten. */
    protected void convertZutaten(){
        List<Zutat> zlist = getZutaten();
        for(Zutat z : zlist){
            Long ID = z.getId();
            String sID = String.valueOf(ID);
            if(fields.containsKey(sID)){
                Long wert = Convert.toLong((String)fields.get(sID), "Anzahl von "+z.getName(), true, 0, Long.MAX_VALUE, errors);
                if(wert != null)
                    if(!wert.equals(Long.valueOf("0"))) rezept.zutaten.put(ID, wert);
            }
        }
        
        
    }
    
}
