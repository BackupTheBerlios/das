package das.dao;

import das.bl.model.Rezept;
import java.sql.Connection;
import java.util.List;
import das.util.Query;

/**
 * Data Access Object zur verarbeitung der datenbankzugriffe im zusammenhang mit Rezepten.
 */
public class RezeptDao {
	
	public static List findRezepte(Query q, Connection con){
		throw new RuntimeException("todo");
	}
	
	public static void insertRezept(Rezept r, Connection con){
		throw new RuntimeException("todo");
	}

	public static void updateRezept(Rezept r, Connection con){
		throw new RuntimeException("todo");
	}
	
	public static void deleteRezept(Long id, Connection con){
		throw new RuntimeException("todo");
	}	
	
}
