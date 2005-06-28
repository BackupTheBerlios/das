package das.dao;

import das.bl.model.Kategorie;
import das.util.ObjName;
import java.sql.Connection;
import java.util.List;
import das.util.Query;
import das.util.ResultType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import das.DasException;



/**
 * Data Access Object zur Verarbeitung der Datenbankzugriffe im Zusammenhang mit Kategorien.
 */
public class KategorieDao {
	
	public static List findKategorien(Query q, Connection con) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StatementBuilder builder = new StatementBuilder();
			builder.add("id", q.getExpression("id"));
			builder.add("name", q.getExpression("name"));
			
			if (q.getResultType() == ResultType.NAMES){				
				stmt = builder.buildQuery("select id, name from kategorie", con);
				return makeNameList(stmt.executeQuery());
			}
			else {
				stmt = builder.buildQuery(
					"select id, name from kategorie ",
					con);
				return makeObjectList(stmt.executeQuery());
			}
		}
		finally {
			DbUtil.close(rs, stmt);
		}		
	}
	
	public static void insertKategorie(Kategorie k, Connection con) throws SQLException {
		String sql = "insert into kategorie(id, name) values(?,?)";
		
		PreparedStatement stmt = null;
		
		try {
			Long id = DbUtil.createId();
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, id);
			setStmtParams(k, stmt, 2);		
			stmt.execute();
			k.setId(id);
		}
		finally {
			DbUtil.close(stmt);
		}
	}

	public static void updateKategorie(Kategorie k, Connection con) throws SQLException {
		String sql = "update kategorie set name = ? where id = ?)";

		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			setStmtParams(k, stmt, 1);
			stmt.setObject(2, k.getId());				
			int updateCount = stmt.executeUpdate();
			if (updateCount < 1){
				throw new DasException("Datensatz mit id " + k.getId() + " nicht gefunden");
			}
		}
		finally {
			DbUtil.close(stmt);
		}
	}
	
	public static void deleteKategorie(Long id, Connection con) throws SQLException {
		String sql = "delete from kategorie where id = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, id);
			stmt.execute();
		}
		finally {
			DbUtil.close(stmt);
		}	
	}	
	
	protected static List makeNameList(ResultSet rs) throws SQLException {
		
		List result = new ArrayList();
		while (rs.next()){
                        result.add(new ObjName(Long.valueOf(rs.getString("id")), rs.getString("name")));
		}
		
		return result;
	}
	
	protected static List makeObjectList(ResultSet rs) throws SQLException {
		List result = new ArrayList();
		
		while (rs.next()){
			result.add(retrieveObject(rs));
		}
		
		return result;
	}
	
	protected static Kategorie retrieveObject(ResultSet rs) throws SQLException {
		Kategorie k = new Kategorie();
		k.setId(rs.getLong("id"));
		k.setName(rs.getString("name"));		
		
		return k;
	}
	
	protected static void setStmtParams(Kategorie k, PreparedStatement stmt, int index)
		throws SQLException {
		
		DbUtil.setString(stmt, index++, k.getName());		
	}
}
