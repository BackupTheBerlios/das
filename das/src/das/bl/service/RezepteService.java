package das.bl.service;

import das.bl.model.Rezept;
import das.util.Query;
import java.util.List;

public class RezepteService {

	private String login;
	
	public RezepteService(String login){
		this.login = login;
	}
	
	public Rezept loadRezept(Long id){
		throw new RuntimeException("todo");
	}
	
	public List findRezepte(Query q){
		throw new RuntimeException("todo");
	}
	
	public void saveRezept(Rezept u){
		throw new RuntimeException("todo");
	}
	
	public void deleteRezept(Long id){
		throw new RuntimeException("todo");
	}	
	
}
