package das.ui.ctrl;

import das.DasException;
import das.util.ObjName;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;


/**
 * Die basisklasse aller user interface controller.
 *
 * @author k
 */
public abstract class ControllerBase {
	
	/**
	 * Der name des command parameters.
	 */
	public static final String COMMAND_PARAM = "cmd";
	
	/**
	 * Konstanten fuer die angabe der richtung einer datentyp konvertierung.
	 * FROM_UI bedeutet konvertierung von der UI repraesentation (also Strings) in
	 * die interne repraesentation. TO_UI bedeutet konvertierung von der internen
	 * repraesentation in HTML taugliche Strings.
	 */
	public static final int FROM_UI = 1;
	public static final int TO_UI = 2;
	
	/*
	 * Der JSP PageContext
	 */
	protected PageContext pageContext;
	
	/*
	 * Die sammlung der fehler die waehrend der verarbeitung eines requests aufgetreten sind.
	 */
	protected Map<String,String> errors = new LinkedHashMap<String,String>();
	
	/*
	 * Die formularfelder und request parameter aus dem HTTP request.
	 */
	protected Map<String,Object> fields;
	
	/*
	 * Wenn dieses flag den wert true hat, ist die der HTTP request erfolgreich verarbeitet
	 * worden. Das heisst es sind keine konvertierungsfehler oder sonstige fehler aufgetreten.
	 */
	protected boolean valid = true;
	
	/**
	 * Setzt den PageContext und initiiert die verarbeitung des HTTP requests. Diese
	 * methode ruft convertAndValidate und danach action auf. action wird nur aufgerufen
	 * wenn die konvertierung und validierung erfolgreich war.
	 */
	public void setPageContext(PageContext ctx) throws Exception {
		
		pageContext = ctx;
		
		ServletRequest req = ctx.getRequest();
		if (req.getCharacterEncoding() == null)
			req.setCharacterEncoding("utf-8");
		
		fields = loadFields(req);
		String command = req.getParameter(COMMAND_PARAM);

		if (command != null)
			command = command.trim().toLowerCase();
		
		if (convertAndValidate(command)){
			action(command);
		}
		else {
			valid = false;
		}
	}
	
	/**
	 * Liefert die sammlung aller aufgetretenen fehler. Der key der Map ist der name
	 * des fehlers, was im normalfall der name des fehlerhaften formular ist.
	 * Bei fehlern die keinen formularfeldern zugeordnet sind, ist der name ein beliebiger
	 * eindeutiger wert.
	 */
	public Map<String,String> getErrors(){
		return errors;
	}
	
	/**
	 * Liefert eine sammlung aller fehler.
	 */
	public Collection<String> getErrorList(){
		return errors.values();
	}
	
	/**
	 * Liefert true if der rquest fehlerfrei abgelaufen ist, sonst false.
	 */
	public boolean isValid(){
		return valid;
	}
	
	/**
	 * Setzt das valid flag.
	 */
	public void setValid(boolean b){
		valid = b;
	}
	
	/**
	 * Liefert die Map mit allen formularfeldern und request paraemtern.
	 */
	public Map<String,Object> getFields(){
		return fields;
	}
	
	/**
	 * Laedt alle request parameter (einschliesslich der formularfelder) in die fields Map.
	 */
	protected Map loadFields(ServletRequest request){
		
		Map<String,String[]> params = (Map<String,String[]>)request.getParameterMap();
		Map<String,Object> result = new TreeMap<String,Object>();
		
		for (Map.Entry<String,String[]> entry : params.entrySet()){
			String[] value = entry.getValue();
			
			if (value.length == 1){
				result.put(entry.getKey(), value[0].trim());
			}
			else {
				List<String> list = new ArrayList<String>(value.length);
				for (String s : value){
					list.add(s.trim());
				}
				result.put(entry.getKey(), list);
			}
		}
		
		return result;
	}
	
	/**
	 * Leitet den HTTP request an die JSP seite mit der gegebenen url weiter. Die url
	 * ist relativ zum context root der web anwendung.
	 */
	protected void forward(String url) throws ServletException, IOException {
				
		RequestDispatcher rd = pageContext.getRequest().getRequestDispatcher(url);
		rd.forward(pageContext.getRequest(), pageContext.getResponse());
	}
	
	/**
	 * Fuegt eine fehlermeldung in die sammlung aller fehler ein.
	 */
	protected void error(String msg){
		error(String.valueOf(errors.size()), msg);
	}
	
	/**
	 * Fuegt eine fehlermeldung unter dem gegebenen key in die sammlung der fehler ein. 
	 */
	protected void error(String key, String msg){
		errors.put(key, msg);
		valid = false;
	}
	
	/**
	 * Liefert den namen des eingeloggten users.
	 */
	protected String getUserName(){
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		return request.getRemoteUser();
	}
	
	/**
	 * Liefert einen request parameter vom typ Long. Wenn der parameter nicht in einen
	 * Long konvertierbar ist, wird eine DasException ausgeloest.
	 *
	 * @param name Der name des request parameters.
	 * @param required Wenn required true ist und der request den gewuenschten
	 * parameter nicht enthaelt, wird eine DasException ausgeloest.
	 */
	protected Long getLongParam(String name, boolean required){
		
		String param = Convert.normalize(pageContext.getRequest().getParameter(name));
		
		if (param == null){
			if (required)
				throw new DasException("Request parameter " + name + " nicht gefunden");
			return null;
		}
		
		Long n = Convert.toLong(param, name, !required, 
			Long.MIN_VALUE, Long.MAX_VALUE, errors);
		if (n == null)
			throw new DasException("Ungueltiger request parameter " + name);
		
		return n;
	}
	
	/**
	 * Liefert einen request parameter vom typ String.
	 *
	 * @param name Der name des request parameters.
	 * @param required Wenn required true ist und der request den gewuenschten
	 * parameter nicht enthaelt, wird eine DasException ausgeloest.
	 */	
	protected String getStringParam(String name, boolean required){
		
		String param = Convert.normalize(pageContext.getRequest().getParameter(name));
		
		if (param == null){
			if (required)
				throw new DasException("Request parameter " + name + " nicht gefunden");
			return null;
		}
		
		return param;
	}
	
	/**
	 * Aendert die name attribute der ObjName in der liste so dass sie in HTML dargestellt
	 * werden koennen. Das heisst die zeichen ", ', <, >, etc werden durch die entsprechenden
	 * entity referenzen ersetzt.
	 */
	protected Collection<ObjName> htmlEscape(Collection<ObjName> names){
		
		for (ObjName name : names){
			name.setName(WebUtil.htmlEscape(name.getName()));
		}
		
		return names;
	}
	
	/**
	 * Liefert die fuer die darstellung in HTML geeignete form von s.
	 * Das heisst die zeichen ", ', <, >, etc werden durch die entsprechenden
	 * entity referenzen ersetzt.
	 */	
	protected String htmlEscape(String s){
		return WebUtil.htmlEscape(s);
	}
	
	/**
	 * Liefert den wert des formularfeldes mit dem gegebenen namen in einer liste.
	 * Die liste kann einen, mehrere oder gar keinen wert enthalten. 
	 */
	protected List getFieldAsList(String name){
		Object value = fields.get(name);
		
		if (value instanceof List){
			return (List)value;
		}
		else {
			List result = new ArrayList();
			if (value != null){
				result.add(value);
			}
			return result;
		}
	}
	
	/**
	 * Diese methode muss von allen konkreten controller klassen implementiert werden.
	 * Sie dient dazu die request parameter zu validierung und in den gewuenschten typ
	 * umzuwandeln. Die methode liefert true wenn die konvertierung und validierung 
	 * erfolgreich waren, ansonsten false.
	 */
	protected abstract boolean convertAndValidate(String command);
	
	/**
	 * action wird aufgerufen um die durch den command parameter angegebene aktion
	 * auszufuehren. Die methode wird nur aufgerufen wenn convertAndValidate erfolgreich war.
	 */
	protected abstract void action(String command) throws ServletException, IOException;
}
