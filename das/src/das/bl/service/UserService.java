/**
 * author: Mario 
 * date: 17.06.2005
 */ 

package das.bl.service;

import das.bl.model.User;
import das.dao.UserDao;
import das.util.Query;
import java.util.List;
import das.DasException;
import das.bl.model.Allergie;
import das.bl.model.Gruppe;
import das.dao.DbUtil;
import das.dao.AllergieDao;
import das.dao.GruppeDao;
import das.util.ObjName;
import das.util.QueryExpr;
import das.util.ResultType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.TreeSet;


/**
 * Backend service zur verwaltung von usern.
 */
public class UserService {
	
	private String login;
	
	/**
	 * Erzeugt ein UserService und setzt den aufrufenden user.
	 */	
	public UserService(String login){
		this.login = login;
	}

	/**
	 * Laedt den durch id oder namen identifizierten User aus der datenbank.
	 * Wenn keine User gefunden wurde, wird eine DasException ausgeloest.	 
         */
        public User loadUser(Long id){
		Query q = new Query(ResultType.OBJECTS);
		q.addExpression(new QueryExpr("id", id));
		List<User> users = findUsers(q);

		if (users.isEmpty()){
			throw new DasException("User mit ID " + id + " nicht gefunden");
		}

		return users.get(0);
	}
                
	/**
	 * Laedt den eingeloggten User aus der datenbank. 
         */
        public User loadUser(String login){
		Query q = new Query(ResultType.OBJECTS);
		q.addExpression(new QueryExpr("login", login));
		List<User> users = findUsers(q);

		if (users.isEmpty()){
			throw new DasException("User mit Login " + login + " nicht gefunden");
		}

		return users.get(0);
	}        
        
	/**
	 * Sucht user die den kriterien in q entsprechen. Liefert entweder eine List von 
	 * User objekten oder eine List von ObjName objekten, die user 
	 * repraesentieren. Ob User oder ObjName objekte geliefert werden,
	 * wird durch das resultType attribut von q bestimmt. 
	 */
	public List findUsers(Query q){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			List result = UserDao.findUsers(q, con);
			
			if (q.getResultType() == ResultType.OBJECTS){
				for (User u : (List<User>)result){
					Query qAllergien = new Query(ResultType.NAMES);
					qAllergien.addExpression(new QueryExpr("bzr_id", u.getId()));
					List allergien = AllergieDao.findAllergien(qAllergien, con);
					u.setAllergien(new TreeSet<ObjName>(
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
        
	/**
	 * Speichert den User u in die datenbank.
	 */
	public void saveUser(User u){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			if (u.getId() == null){
				UserDao.insertUser(u, con);
			}
			else {
				UserDao.updateUser(u, con);
			}
			UserDao.saveBzr2All(u, con);
			con.commit();
		}
		catch(Exception ex){
			DbUtil.rollback(con);
			throw new DasException("User konnte nicht gespeichert werden", ex);
		}
		finally {
			DbUtil.close(con);
		}		
	}

	/**
	 * Loescht den durch Id identifizierten User aus der datenbank. 
	 * Wenn der User nicht in der datenbank vorkommt, geschieht nichts.
	 */
	public void deleteUser(Long id){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			UserDao.deleteUser(id, con);
		}
		catch(Exception ex){
			throw new DasException("User " + id + " konnte nicht geloescht werden", ex);
		}
		finally {
			DbUtil.close(con);
		}		
	}
        
        
        /**
	 * Laedt die durch id identifizierte Gruppe aus der datenbank.
	 * Wenn keine Gruppe mit dieser id gefunden wurde, wird eine DasException ausgeloest.
	 */
	public Gruppe loadGruppe(Long id){
		Connection con = null;
		try {
			Query q = new Query(ResultType.OBJECTS);
			q.addExpression(new QueryExpr("id", id));
			con = DbUtil.getConnection();
			List<Gruppe> gruppen = GruppeDao.findGruppen(q, con);
			
			if (gruppen.isEmpty()){
				throw new DasException("Gruppe mit ID " + id + " nicht gefunden");
			}
			
			return gruppen.get(0);
		}
		catch(SQLException ex){
			throw new DasException("Gruppe mit id " + id + " konnte nicht geladen werden", ex);
		}
		finally {
			DbUtil.close(con);
		}
	}
	
	/**
	 * Sucht Gruppen die den kriterien in q entsprechen. Liefert entweder eine List von 
	 * Gruppe objekten oder eine List von ObjName objekten, die Gruppen 
	 * repraesentieren. Ob Gruppe oder ObjName objekte geliefert werden,
	 * wird durch das resultType attribut von q bestimmt. 
	 */	
	public List findGruppen(Query q){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			return GruppeDao.findGruppen(q, con);
		}
		catch(SQLException ex){
			throw new DasException(ex);
		}
		finally {
			DbUtil.close(con);
		}
	}
	
	/**
	 * Speichert die Gruppe g in der datenbank.
	 */
	public void saveGruppe(Gruppe g){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			if (g.getId() == null){
				GruppeDao.insertGruppe(g, con);
			}
			else {
				GruppeDao.updateGruppe(g, con);
			}
		}
		catch(Exception ex){
			throw new DasException("Gruppe konnte nicht gespeichert werden", ex);
		}
		finally {
			DbUtil.close(con);
		}
	}
	
	/**
	 * Loescht die durch id identifizierte Gruppe aus der datenbank. 
	 * Wenn die Gruppe nicht in der datenbank vorkommt, geschieht nichts.
	 */
	public void deleteGruppe(Long id){
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			GruppeDao.deleteGruppe(id, con);
		}
		catch(Exception ex){
			throw new DasException("Gruppe " + id + " konnte nicht geloescht werden", ex);
		}
		finally {
			DbUtil.close(con);
		}		
	}
}
