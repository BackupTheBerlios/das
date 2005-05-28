package das.dao;

import das.bl.model.User;
import java.sql.Connection;
import java.util.List;
import das.util.Query;

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
