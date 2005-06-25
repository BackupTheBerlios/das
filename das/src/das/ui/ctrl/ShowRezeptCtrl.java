package das.ui.ctrl;

import das.bl.model.*;
import das.bl.service.*;
import static das.ui.ctrl.CtrlConstants.*;
import das.util.ObjName;
import das.util.Query;
import das.util.ResultType;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.*;
import javax.servlet.ServletException;

/**
 * Die Controllerklasse zum Anzeigen von Rezepten.
 */
public class ShowRezeptCtrl extends ControllerBase {
    
    public Rezept rezept;
    
    /* prueft ob das eingellogter User allergisch zu diesen Rezept(seine Zutaten) ist */
    public boolean isAllergic(){
        Set<ObjName> myAllergies;
        Set<ObjName> rezeptAllergies;
        User u;
        UserService us = new UserService(getUserName());
        u = us.loadUser(getUserName());
        myAllergies = u.getAllergien();
        rezeptAllergies = rezept.getAllergies();
        System.out.println("myALL: "+myAllergies);
        
        System.out.println("rezALL: "+rezeptAllergies);
        
        Iterator i = myAllergies.iterator();
        while(i.hasNext()){
            ObjName a = (ObjName) i.next();
            if(rezeptAllergies.contains(a)) return true;
        }
        
        
        return false;
    }
    
    
    public Map<Long,Long> getZutaten(){
        return rezept.zutaten;
    }
    
    
    public float sumZucker(){
        float sum = 0.0f;
        Set set = rezept.zutaten.keySet();
        Iterator iter = set.iterator();
        ZutatenService zs = new ZutatenService(getUserName());
        Zutat z;
        
        while(iter.hasNext()){
            Long id = (Long)iter.next();
            Long anzahl = (Long)rezept.zutaten.get(id);
            
            z = zs.loadZutat(id);
            Float zucker = 0.0f;
            if(z.getZucker() != null)
                zucker = (float)z.getZucker()/100;
            
            sum += (zucker*anzahl);
        }
        
        return sum;
    }
    
    public float sumFett(){
        float sum = 0.0f;
        Set set = rezept.zutaten.keySet();
        Iterator iter = set.iterator();
        ZutatenService zs = new ZutatenService(getUserName());
        Zutat z;
        
        while(iter.hasNext()){
            Long id = (Long)iter.next();
            Long anzahl = (Long)rezept.zutaten.get(id);
            
            z = zs.loadZutat(id);
            Float fett = 0.0f;
            if(z.getFett() != null) fett = (float)z.getFett()/100;
            sum += (fett*anzahl);
        }
        
        return sum;
    }
    
    public float sumKalorien(){
        float sum = 0.0f;
        Set set = rezept.zutaten.keySet();
        Iterator iter = set.iterator();
        ZutatenService zs = new ZutatenService(getUserName());
        Zutat z;
        
        while(iter.hasNext()){
            Long id = (Long)iter.next();
            Long anzahl = (Long)rezept.zutaten.get(id);
            
            z = zs.loadZutat(id);
            Float kal = z.getKalorien();
            sum += (kal*anzahl);
        }
        
        return sum;
    }
    
    /**
     * Konvertiert und validiert die request parameter
     */
    protected boolean convertAndValidate(String command){
        if (command == null)
            return true;
        
        if (command.equals("bewerten")){
            rezept = new Rezept();
            convert(FROM_UI);
        }
        
        return errors.isEmpty();
    }
    
    /**
     * Laedet den Rezept ein.
     */
    protected void action(String command) throws ServletException, IOException {
        
        RezepteService service = new RezepteService(getUserName());
        rezept = service.loadRezept(getLongParam("id", true));
        
        if (command != null){ //equals bewerten
            int bew = Convert.toInteger( (String)fields.get("bew"), "Bewertung", true, 1, 5, errors);
            service.bewerteRezept(getUserName(), rezept, bew);
        }
        
        
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
        }else if (direction == FROM_UI){
            rezept.setId(Convert.toLong( (String)fields.get("id"), "ID", true, 0, Long.MAX_VALUE, errors));
        }
    }
}
