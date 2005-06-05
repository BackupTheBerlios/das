package das.dao;

import das.bl.model.Allergie;
import java.sql.Connection;
import java.util.List;
import das.util.Query;

/**
 * Data Access Object zur verarbeitung der datenbankzugriffe im zusammenhang mit Allergien.
 */
public class AllergieDao {

	public static List findAllergien(Query q, Connection con){
		throw new RuntimeException("todo");
	}
	
	public static void insertAllergie(Allergie a, Connection con){
		throw new RuntimeException("todo");
	}

	public static void updateAllergie(Allergie a, Connection con){
		throw new RuntimeException("todo");
	}
	
	public static void deleteAllergie(Long id, Connection con){
		throw new RuntimeException("todo");
	}	
	
}
