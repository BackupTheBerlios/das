package das.ui.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

public class MenuCtrl {
	private PageContext pageContext;
	
	public void setPageContext(PageContext pageContext){
		this.pageContext = pageContext;
	}
	
	public boolean isEditor(){				
		HttpServletRequest httpRequest = (HttpServletRequest)pageContext.getRequest();
		return httpRequest.isUserInRole("editors") || httpRequest.isUserInRole("admins");
	}
}
