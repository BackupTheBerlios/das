package das.dao;

import das.bl.model.User;
import java.sql.Connection;
import java.util.List;
import das.util.Query;

/**
 * Data Access Object zur verarbeitung der datenbankzugriffe im zusammenhang mit Usern.
 */
public class UserDao {
		
	public static List findUsers(Query q, Connection con){
		throw new RuntimeException("todo");
	}
	
	public static void insertUser(User u, Connection con){
		throw new RuntimeException("todo");
	}

	public static void updateUser(User u, Connection con){
		throw new RuntimeException("todo");
	}
	
	public static void deleteUser(String id, Connection con){
		throw new RuntimeException("todo");
	}	
}
