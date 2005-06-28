package das.ui.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * Controller klasse zur menuesteuerung. Das ist der einzige controller der nicht
 * von ControllerBase erbt, weil hier die meiste typische controller funktionalitaet
 * nicht noetig ist und bei jedem seitenzugriff eine menge unnoetiger dinge tun wuerde
 * (parameter aus dem request lesen).
 *
 * @author k
 */
public class MenuCtrl {
	private PageContext pageContext;
	
	public void setPageContext(PageContext pageContext){
		this.pageContext = pageContext;
	}
	
	/*
	public boolean isEditor(){				
		HttpServletRequest httpRequest = (HttpServletRequest)pageContext.getRequest();
		return httpRequest.isUserInRole("editors") || httpRequest.isUserInRole("admins");
	}
	 */
	
	public boolean isShowVerwaltungMenu(){
		HttpServletRequest httpRequest = (HttpServletRequest)pageContext.getRequest();
		return httpRequest.isUserInRole("editors") || httpRequest.isUserInRole("admins");		
	}
	
	public boolean isShowUserBearbeiten(){
		HttpServletRequest httpRequest = (HttpServletRequest)pageContext.getRequest();
		return httpRequest.isUserInRole("admins");		
	}

}
