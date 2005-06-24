/**
 * author: Mario
 * date: 17.06.2005
 */


package das.ui.ctrl;
import das.bl.model.User;
import das.bl.service.UserService;
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

import das.DasException;
import javax.servlet.ServletException;


/**
 * Die Controller klasse zur aenderung des Profils.
 */
public class EditUserCtrl extends ControllerBase {
    
    private User user;
    private String message;
    
    public String getMessage(){
        return message;
    }
    
    /**
     * Liefert eine liste von ObjNames aller Gruppen.
     */
    public Collection<ObjName> getGruppen(){
        Query q = new Query(ResultType.NAMES);
        UserService service = new UserService(getUserName());
        return htmlEscape(service.findGruppen(q));
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
        
        if (user != null){
            for (ObjName n : user.getAllergien()){
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
            user = new User();
            convert(FROM_UI);
            validateName();
        }
        
        return errors.isEmpty();
    }
    
    /**
     * Fuehrt das gegebene kommando aus.
     */
    protected void action(String command) throws ServletException, IOException {
        if (command == null)
            return;
        
        UserService service = new UserService(getUserName());
        
        if (command.equals("new")){
            user = new User();
            convert(TO_UI);
        } else if (command.equals("edit")){
            user = service.loadUser(getLongParam("id", true));
            convert(TO_UI);
        } else if (command.equals("profil")){
            String login = getUserName();
            user = service.loadUser(login);
            convert(TO_UI);
        } else if (command.equals("save")){
            try{
                service.saveUser(user);
                //forward("/login.jsp?msg=User wurde erfolgreich gespeichert");
            }catch(DasException de){
                errors.put("userExists", "User mit Login '"+user.getLogin()+"' existiert bereits");
                valid = false;
            }
            convert(TO_UI);
        } else if (command.equals("delete")){
            service.deleteUser(getLongParam("id", true));
            forward(DELETED_PAGE + "?msg=User");
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
            fields.put("id", Convert.fromNumber(user.getId()));
            fields.put("name", Convert.fromString(user.getName()));
            fields.put("login", Convert.fromString(user.getLogin()));
            fields.put("passwort", Convert.fromString(user.getPasswort()));
            fields.put("email", Convert.fromString(user.getEmail()));
            fields.put("gruId", Convert.fromNumber(user.getGruId()));
        } else if (direction == FROM_UI){
            user.setId(Convert.toLong(
                    (String)fields.get("id"), "ID", true, 0, Long.MAX_VALUE, errors));
            user.setName(Convert.toString(
                    (String)fields.get("name"), "Name", false, 0, 100, errors));
            user.setLogin(Convert.toString(
                    (String)fields.get("login"), "Login", false, 0, 50, errors));
            user.setPasswort(Convert.toString(
                    (String)fields.get("passwort"), "Passwort", false, 0, 50, errors));
            user.setEmail(Convert.toString(
                    (String)fields.get("email"), "Email", true, 1, 50, errors));
            user.setGruId(Convert.toLong(
                    (String)fields.get("gruId"), "Gruppe", true, 0, Long.MAX_VALUE, errors));
            allergienFromUi();
        }
    }
    
    protected void validateName(){
        UserService service = new UserService(getUserName());
        if(service.userExists(user)){
            errors.put("userExists", "User mit Login'"+user.getLogin()+"' existiert bereits");
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
        
        user.setAllergien(allergien);
    }
}

