package das.ui.ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class LogoutCtrl extends ControllerBase {
	
	protected String msg;
	
	public String getMessage(){
		return msg;
	}
	
	protected boolean convertAndValidate(String command){
		return true;
	}
	
	protected void action(String command) throws ServletException, IOException {
		HttpSession session = pageContext.getSession();
		if (session != null){
			try {
				session.invalidate();
			}
			catch(IllegalStateException ex){
				ex.printStackTrace();
			}
			finally {
				msg = "Sie wurden erfolgreich ausgeloggt";
			}
		}
		else {
			msg = "Sie sind nicht eingeloggt";
		}
	}
}
