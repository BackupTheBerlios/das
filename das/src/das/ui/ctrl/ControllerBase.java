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

public abstract class ControllerBase {
	
	public static final String COMMAND_PARAM = "cmd";
	public static final int FROM_UI = 1;
	public static final int TO_UI = 2;
	
	protected PageContext pageContext;
	protected Map<String,String> errors = new LinkedHashMap<String,String>();
	protected Map<String,Object> fields;
	protected boolean valid = true;
	
	public void setPageContext(PageContext ctx) throws Exception {
		
		pageContext = ctx;
		fields = loadFields(ctx.getRequest());
		String command = ctx.getRequest().getParameter(COMMAND_PARAM);

		if (command != null)
			command = command.trim().toLowerCase();
		
		if (convertAndValidate(command)){
			action(command);
		}
		else {
			valid = false;
		}
	}
	
	public Map<String,String> getErrors(){
		return errors;
	}
	
	public Collection<String> getErrorList(){
		return errors.values();
	}
	
	public boolean isValid(){
		return valid;
	}
	
	public void setValid(boolean b){
		valid = b;
	}
	
	public Map<String,Object> getFields(){
		return fields;
	}
	
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
	
	protected void forward(String url) throws ServletException, IOException {
				
		RequestDispatcher rd = pageContext.getRequest().getRequestDispatcher(url);
		rd.forward(pageContext.getRequest(), pageContext.getResponse());
	}
	
	protected void error(String msg){
		error(String.valueOf(errors.size()), msg);
	}
	
	protected void error(String key, String msg){
		errors.put(key, msg);
		valid = false;
	}
	
	protected String getUserName(){
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		return request.getRemoteUser();
	}
	
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
	
	protected String getStringParam(String name, boolean required){
		
		String param = Convert.normalize(pageContext.getRequest().getParameter(name));
		
		if (param == null){
			if (required)
				throw new DasException("Request parameter " + name + " nicht gefunden");
			return null;
		}
		
		return param;
	}
	
	protected List<ObjName> htmlEscape(List<ObjName> names){
		
		for (ObjName name : names){
			name.setName(WebUtil.htmlEscape(name.getName()));
		}
		
		return names;
	}
	
	protected String htmlEscape(String s){
		return WebUtil.htmlEscape(s);
	}
	
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
			
	protected abstract boolean convertAndValidate(String command);
	
	protected abstract void action(String command) throws ServletException, IOException;
}
