package das.bl.service;

import das.bl.model.User;
import das.util.Query;
import java.util.List;

/**
 * Backend service zur verwaltung von usern.
 */
public class UserService {
	
	private String login;
	
	/**
	 * Erzeugt ein UserService und setzt den aufrufenden user.
	 */	
	public UserService(String login){
		this.login = login;
	}

	/**
	 * Laedt den durch id identifizierten User aus der datenbank.
	 * Wenn keine User mit dieser id gefunden wurde, wird eine DasException ausgeloest.
	 */
	public User loadUser(String id){
		throw new RuntimeException("todo");
	}

	/**
	 * Sucht user die den kriterien in q entsprechen. Liefert entweder eine List von 
	 * User objekten oder eine List von ObjName objekten, die user 
	 * repraesentieren. Ob User oder ObjName objekte geliefert werden,
	 * wird durch das resultType attribut von q bestimmt. 
	 */
	public List findUsers(Query q){
		throw new RuntimeException("todo");
	}

	/**
	 * Speichert den User u in die datenbank.
	 */
	public void saveUser(User u){
		throw new RuntimeException("todo");
	}

	/**
	 * Loescht den durch id identifizierten User aus der datenbank. 
	 * Wenn der User nicht in der datenbank vorkommt, geschieht nichts.
	 */
	public void deleteUser(String id){
		throw new RuntimeException("todo");
	}	
}
