package das.bl.service;

import das.bl.model.User;
import das.util.Query;
import java.util.List;

public class UserService {
	
	private String login;
	
	public UserService(String login){
		this.login = login;
	}
	
	public User loadUser(String id){
		throw new RuntimeException("todo");
	}
	
	public List findUsers(Query q){
		throw new RuntimeException("todo");
	}
	
	public void saveUser(User u){
		throw new RuntimeException("todo");
	}
	
	public void deleteUser(String id){
		throw new RuntimeException("todo");
	}	
}
