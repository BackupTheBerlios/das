/*
 * Created on 16.05.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package das.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserServlet extends HttpServlet {
	/*
	 * steuerzentrale .. hier wird anhand der requests entschieden
	 * welche functionen ausgeführt werden sollen*/
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	    //test
	    response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Test Form");
	    
	}
	/**
	 * listet alle registrierten User auf
	 * TODO parameter festlegen
	 */
	private void listUsers() {
		
	}
	/**
	 * einen User registrieren
	 * TODO parameter festlegen
	 */
	private void registerUser() {
		
	}
	/**
	 * user einloggen
	 * TODO parameter festlegen
	 */
	private void loginUser() {
				
	}
	/** 
	 * User ausloggen
	 * TODO parameter festlegen
	 */
	private void logoutUser() {
		
	}
	/**
	 * Userprofil bearbeiten: password setzen, emailadresse ändern, etc.
	 * TODO parameter festlegen
	 */
	private void editUserProfile() {
		
	}
	/**
	 * user löschen
	 * TODO parameter festlegen
	 */
	private void deleteUser () {
		
	}
	/**
	 * user einer gruppe zuweisen
	 * TODO parameter festlegen
	 *
	 */
	private void assignUserToGroup() {
		
	}
}
