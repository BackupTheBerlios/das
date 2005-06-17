package das.ui.ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static das.ui.ctrl.CtrlConstants.HOME_PAGE;
import javax.servlet.http.HttpServletRequest;

public class LogoutCtrl extends ControllerBase {
		
	protected boolean convertAndValidate(String command){
		return true;
	}
	
	protected void action(String command) throws ServletException, IOException {
		HttpSession session = pageContext.getSession();
		if (session != null){
			try {
				session.invalidate();
				HttpServletResponse httpResponse = 
					(HttpServletResponse)pageContext.getResponse();
				HttpServletRequest httpRequest = 
					(HttpServletRequest)pageContext.getRequest();
				httpResponse.sendRedirect(httpRequest.getContextPath() + HOME_PAGE);
			}
			catch(IllegalStateException ex){
				ex.printStackTrace();
			}
		}
	}
}
