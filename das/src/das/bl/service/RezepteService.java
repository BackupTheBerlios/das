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
        Rezept r;
        Connection con = null;
        try {
            Query q = new Query(ResultType.OBJECTS);
            q.addExpression(new QueryExpr("id", id));
            con = DbUtil.getConnection();
            List<Rezept> rezepten = RezeptDao.findRezepte(q, con);
            
            if (rezepten.isEmpty()){
                throw new DasException("Rezept mit ID " + id + " nicht gefunden");
            }
            
            r = rezepten.get(0);
            r.zutaten = RezeptDao.loadZutaten(r.getId(), con);
            return r;
        } catch(SQLException ex){
            throw new DasException("Rezept mit ID " + id + " konnte nicht geladen werden", ex);
        } finally {
            DbUtil.close(con);
        }
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
        } catch(Exception ex){
            throw new DasException(ex);
        } finally {
            DbUtil.close(con);
        }
    }
    
   /**
    * Bewerten den Rezept r durch User u
    */
    public void bewerteRezept(String login, Rezept r, int rating){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			RezeptDao.bewerteRezept(r, con, login, rating);
		}
		catch(Exception ex){
			throw new DasException("Rezept " + r.getName() + " konnte nicht bewertet werden", ex);
		}
		finally {
			DbUtil.close(con);
		}		
    }
    
    
    /**
     * Speichert das Rezept r in die datenbank.
     */
    public void saveRezept(Rezept r){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			if (r.getId() == null){
				RezeptDao.insertRezept(r, con);
			}
			else {
				RezeptDao.updateRezept(r, con);
			}
                        RezeptDao.saveZutaten(r, con);
			con.commit();
		}
		catch(Exception ex){
			DbUtil.rollback(con);
			throw new DasException("Rezept konnte nicht gespeichert werden", ex);
		}
		finally {
			DbUtil.close(con);
		}	
    }
    
    /**
     * Loescht das durch id identifizierte Rezept aus der datenbank.
     * Wenn das Rezept nicht in der datenbank vorkommt, geschieht nichts.
     */
    public void deleteRezept(Long id){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			RezeptDao.deleteRezept(id, con);
		}
		catch(Exception ex){
			throw new DasException("Rezept " + id + " konnte nicht geloescht werden", ex);
		}
		finally {
			DbUtil.close(con);
		}		
    }
    
}
