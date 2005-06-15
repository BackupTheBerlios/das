package das.bl.service;

import das.bl.model.Rezept;
import das.DasException;
import das.bl.model.Allergie;
import das.bl.model.Kategorie;
import das.bl.model.Zutat;
import das.dao.DbUtil;
import das.dao.AllergieDao;
import das.dao.KategorieDao;
import das.dao.RezeptDao;
import das.util.Query;
import das.util.QueryExpr;
import das.util.ResultType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * Backend service zur verwaltung von rezepten. 
 */
public class RezepteService {

	private String login;

	/**
	 * Erzeugt ein RezepteService und setzt den aufrufenden user.
	 */	
	public RezepteService(String login){
		this.login = login;
	}
	
	/**
	 * Laedt das durch id identifizierte Rezept aus der datenbank.
	 * Wenn kein Rezept mit dieser id gefunden wurde, wird eine DasException ausgeloest.
	 */	
	public Rezept loadRezept(Long id){
		throw new RuntimeException("todo");
	}

	/**
	 * Sucht rezepte die den kriterien in q entsprechen. Liefert entweder eine List von 
	 * Rezept objekten oder eine List von ObjName objekten, die rezepte 
	 * repraesentieren. Ob Rezept oder ObjName objekte geliefert werden,
	 * wird durch das resultType attribut von q bestimmt. 
	 */
	public List findRezepte(Query q){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			return RezeptDao.findRezepte(q, con);
		}
		catch(Exception ex){
			throw new DasException(ex);
		}
		finally {
			DbUtil.close(con);
		}
	}
	
	/**
	 * Speichert das Rezept r in die datenbank.
	 */
	public void saveRezept(Rezept r){
		throw new RuntimeException("todo");
	}

	/**
	 * Loescht das durch id identifizierte Rezept aus der datenbank. 
	 * Wenn das Rezept nicht in der datenbank vorkommt, geschieht nichts.
	 */
	public void deleteRezept(Long id){
		throw new RuntimeException("todo");
	}	
	
}
