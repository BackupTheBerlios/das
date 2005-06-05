package das.bl.service;

import das.DasException;
import das.bl.model.Allergie;
import das.bl.model.Kategorie;
import das.bl.model.Zutat;
import das.dao.DbUtil;
import das.dao.KategorieDao;
import das.dao.ZutatDao;
import das.util.Query;
import das.util.QueryExpr;
import das.util.ResultType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Backend service zur verwaltung von zutaten, kategorien und allergien. 
 */
public class ZutatenService {
	
	private String login;
	
	/**
	 * Erzeugt ein ZutatenService und setzt den aufrufenden user.
	 */
	public ZutatenService(String login){
		this.login = login;
	}

	/**
	 * Laedt die durch id identifiziert Zutat aus der datenbank.
	 * Wenn keine Zutat mit dieser id gefunden wurde, wird eine DasException ausgeloest.
	 */
	public Zutat loadZutat(Long id){
		Connection con = null;
		try {
			Query q = new Query(ResultType.OBJECTS);
			q.addExpression(new QueryExpr("id", id));
			con = DbUtil.getConnection();
			List<Zutat> zutaten = ZutatDao.findZutaten(q, con);
			
			if (zutaten.isEmpty()){
				throw new DasException("Zutat mit ID " + id + " nicht gefunden");
			}
			
			return zutaten.get(0);
		}
		catch(SQLException ex){
			throw new DasException("Zutat mit id " + id + " konnte nicht geladen werden", ex);
		}
		finally {
			DbUtil.close(con);
		}		
	}
	
	/**
	 * Sucht zutaten die den kriterien in q entsprechen. Liefert entweder eine List von 
	 * Zutat objekten oder eine List von ObjName objekten, die zutaten 
	 * repraesentieren. Ob Zutat oder ObjName objekte geliefert werden,
	 * wird durch das resultType attribut von q bestimmt. 
	 */
	public List findZutaten(Query q){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			return ZutatDao.findZutaten(q, con);
		}
		catch(Exception ex){
			throw new DasException(ex);
		}
		finally {
			DbUtil.close(con);
		}
	}
	
	/**
	 * Speichert die Zutat z in die datenbank.
	 */
	public void saveZutat(Zutat z){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			if (z.getId() == null){
				ZutatDao.insertZutat(z, con);
			}
			else {
				ZutatDao.updateZutat(z, con);
			}
		}
		catch(Exception ex){
			throw new DasException("Zutat konnte nicht gespeichert werden", ex);
		}
		finally {
			DbUtil.close(con);
		}		
	}
	
	/**
	 * Loescht die durch id identifizierte Zutat aus der datenbank. 
	 * Wenn die Zutat nicht in der datenbank vorkommt, geschieht nichts.
	 */
	public void deleteZutat(Long id){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			ZutatDao.deleteZutat(id, con);
		}
		catch(Exception ex){
			throw new DasException("Zutat " + id + " konnte nicht geloescht werden", ex);
		}
		finally {
			DbUtil.close(con);
		}		
	}

	/**
	 * Laedt die durch id identifizierte Kategorie aus der datenbank.
	 * Wenn keine Kategorie mit dieser id gefunden wurde, wird eine DasException ausgeloest.
	 */
	public Kategorie loadKategorie(Long id){
		throw new RuntimeException("todo");
	}
	
	/**
	 * Sucht kategorien die den kriterien in q entsprechen. Liefert entweder eine List von 
	 * Kategorie objekten oder eine List von ObjName objekten, die kategorien 
	 * repraesentieren. Ob Kategorie oder ObjName objekte geliefert werden,
	 * wird durch das resultType attribut von q bestimmt. 
	 */	
	public List findKategorien(Query q){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			return KategorieDao.findKategorien(q, con);
		}
		catch(SQLException ex){
			throw new DasException(ex);
		}
		finally {
			DbUtil.close(con);
		}
	}
	
	/**
	 * Speichert die Kategorie k in der datenbank.
	 */
	public void saveKategorie(Kategorie k){
		throw new RuntimeException("todo");
	}
	
	/**
	 * Loescht die durch id identifizierte Kategorie aus der datenbank. 
	 * Wenn die Kategorie nicht in der datenbank vorkommt, geschieht nichts.
	 */
	public void deleteKategorie(Long id){
		throw new RuntimeException("todo");
	}
	
	/**
	 * Laedt die durch id identifizierte Allergie aus der datenbank.
	 * Wenn keine Allergie mit dieser id gefunden wurde, wird eine DasException ausgeloest.
	 */	
	public Allergie loadAllergie(Long id){
		throw new RuntimeException("todo");
	}
	
	/**
	 * Sucht allergien die den kriterien in q entsprechen. Liefert entweder eine List von 
	 * Allergie objekten oder eine List von ObjName objekten, die allergien 
	 * repraesentieren. Ob Allergie oder ObjName objekte geliefert werden,
	 * wird durch das resultType attribut von q bestimmt. 
	 */		
	public List findAllergien(Query q){
		throw new RuntimeException("todo");
	}
	
	/**
	 * Speichert die Allergie a in die datenbank.
	 */
	public void saveAllergie(Allergie a){
		throw new RuntimeException("todo");
	}
	
	/**
	 * Loescht die durch id identifiziert Allergie aus der datenbank. Wenn keine
	 * Allergie mit dieser id gefunden wird, geschieht nichts.
	 */
	public void deleteAllergie(Long id){
		throw new RuntimeException("todo");
	}
}
