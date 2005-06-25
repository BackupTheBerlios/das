package das.bl.service;

import das.DasException;
import das.IntegrityConstraintException;
import das.bl.model.Allergie;
import das.bl.model.Kategorie;
import das.bl.model.Zutat;
import das.dao.DbUtil;
import das.dao.AllergieDao;
import das.dao.KategorieDao;
import das.dao.ZutatDao;
import das.util.ObjName;
import das.util.Query;
import das.util.QueryExpr;
import das.util.ResultType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeSet;

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
	 * Laedt die durch id identifizierte Zutat aus der datenbank.
	 * Wenn keine Zutat mit dieser id gefunden wurde, wird eine DasException ausgeloest.
	 */
	public Zutat loadZutat(Long id){
		Query q = new Query(ResultType.OBJECTS);
		q.addExpression(new QueryExpr("id", id));
		List<Zutat> zutaten = findZutaten(q);

		if (zutaten.isEmpty()){
			throw new DasException("Zutat mit ID " + id + " nicht gefunden");
		}

		return zutaten.get(0);
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
			List result = ZutatDao.findZutaten(q, con);
			
			if (q.getResultType() == ResultType.OBJECTS){
				for (Zutat z : (List<Zutat>)result){
					Query qAllergien = new Query(ResultType.NAMES);
					qAllergien.addExpression(new QueryExpr("zut_id", z.getId()));
					List allergien = AllergieDao.findAllergien(qAllergien, con);
					z.setAllergien(new TreeSet<ObjName>(
						AllergieDao.findAllergien(qAllergien, con)));
				}
			}
			
			return result;
		}
		catch(Exception ex){
			throw new DasException(ex);
		}
		finally {
			DbUtil.close(con);
		}
	}
	
	public boolean zutatExists(Zutat zutat){
		Query q = new Query(ResultType.NAMES);
		q.addExpression(new QueryExpr("name", zutat.getName()));
		List found = findZutaten(q);
		
		if (!found.isEmpty()){
			Long foundId = (Long)((ObjName)found.get(0)).getId();
			if (!foundId.equals(zutat.getId())){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Speichert die Zutat z in die datenbank.
	 */
	public void saveZutat(Zutat z){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			if (z.getId() == null){
				ZutatDao.insertZutat(z, con);
			}
			else {
				ZutatDao.updateZutat(z, con);
			}
			ZutatDao.saveZut2All(z, con);
			con.commit();
		}
		catch(Exception ex){
			DbUtil.rollback(con);
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
		catch(SQLException ex){
			String sqlState = ex.getSQLState();
			if (sqlState != null && sqlState.startsWith("23"))
				throw new IntegrityConstraintException("Zutat " + id 
					+ " kann nicht geloescht werden");
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
		Connection con = null;
		try {
			Query q = new Query(ResultType.OBJECTS);
			q.addExpression(new QueryExpr("id", id));
			con = DbUtil.getConnection();
			List<Kategorie> kategorien = KategorieDao.findKategorien(q, con);
			
			if (kategorien.isEmpty()){
				throw new DasException("Kategorie mit ID " + id + " nicht gefunden");
			}
			
			return kategorien.get(0);
		}
		catch(SQLException ex){
			throw new DasException("Kategorie mit id " + id + " konnte nicht geladen werden", ex);
		}
		finally {
			DbUtil.close(con);
		}
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
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			if (k.getId() == null){
				KategorieDao.insertKategorie(k, con);
			}
			else {
				KategorieDao.updateKategorie(k, con);
			}
		}
		catch(Exception ex){
			throw new DasException("Kategorie konnte nicht gespeichert werden", ex);
		}
		finally {
			DbUtil.close(con);
		}
	}
	
	/**
	 * Loescht die durch id identifizierte Kategorie aus der datenbank. 
	 * Wenn die Kategorie nicht in der datenbank vorkommt, geschieht nichts.
	 */
	public void deleteKategorie(Long id){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			KategorieDao.deleteKategorie(id, con);
		}
		catch(Exception ex){
			throw new DasException("Kategorie " + id + " konnte nicht geloescht werden", ex);
		}
		finally {
			DbUtil.close(con);
		}		
	}
	
	/**
	 * Laedt die durch id identifizierte Allergie aus der datenbank.
	 * Wenn keine Allergie mit dieser id gefunden wurde, wird eine DasException ausgeloest.
	 */	
	public Allergie loadAllergie(Long id){
		Connection con = null;
		try {
			Query q = new Query(ResultType.OBJECTS);
			q.addExpression(new QueryExpr("id", id));
			con = DbUtil.getConnection();
			List<Allergie> allergien = AllergieDao.findAllergien(q, con);
			
			if (allergien.isEmpty()){
				throw new DasException("Allergie mit ID " + id + " nicht gefunden");
			}
			
			return allergien.get(0);
		}
		catch(SQLException ex){
			throw new DasException("Allergie mit id " + id + " konnte nicht geladen werden", ex);
		}
		finally {
			DbUtil.close(con);
		}		
	}
	
	/**
	 * Sucht allergien die den kriterien in q entsprechen. Liefert entweder eine List von 
	 * Allergie objekten oder eine List von ObjName objekten, die allergien 
	 * repraesentieren. Ob Allergie oder ObjName objekte geliefert werden,
	 * wird durch das resultType attribut von q bestimmt. 
	 */		
	public List findAllergien(Query q){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			return AllergieDao.findAllergien(q, con);
		}
		catch(SQLException ex){
			throw new DasException(ex);
		}
		finally {
			DbUtil.close(con);
		}
	}
	
	/**
	 * Speichert die Allergie a in die datenbank.
	 */
	public void saveAllergie(Allergie a){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			if (a.getId() == null){
				AllergieDao.insertAllergie(a, con);
			}
			else {
				AllergieDao.updateAllergie(a, con);
			}
		}
		catch(Exception ex){
			throw new DasException("Allergie konnte nicht gespeichert werden", ex);
		}
		finally {
			DbUtil.close(con);
		}
	}
	
	/**
	 * Loescht die durch id identifiziert Allergie aus der datenbank. Wenn keine
	 * Allergie mit dieser id gefunden wird, geschieht nichts.
	 */
	public void deleteAllergie(Long id){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			AllergieDao.deleteAllergie(id, con);
		}
		catch(Exception ex){
			throw new DasException("Allergie " + id + " konnte nicht geloescht werden", ex);
		}
		finally {
			DbUtil.close(con);
		}
	}
}
