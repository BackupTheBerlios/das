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

public class ZutatenService {
	
	private String login;
	
	public ZutatenService(String login){
		this.login = login;
	}
	
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

	public Kategorie loadKategorie(Long id){
		throw new RuntimeException("todo");
	}
	
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
	
	public void saveKategorie(Kategorie k){
		throw new RuntimeException("todo");
	}
	
	public void deleteKategorie(Long id){
		throw new RuntimeException("todo");
	}
	
	public Allergie loadAllergie(Long id){
		throw new RuntimeException("todo");
	}
	
	public List findAllergien(Query q){
		throw new RuntimeException("todo");
	}
	
	public void saveAllergie(Allergie a){
		throw new RuntimeException("todo");
	}
	
	public void deleteAllergie(Long id){
		throw new RuntimeException("todo");
	}
}
