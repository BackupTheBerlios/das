package das.dao;

import das.bl.model.Allergie;
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
 * Data Access Object zur Verarbeitung der Datenbankzugriffe im Zusammenhang mit Allergien.
 */
public class AllergieDao {

	public static List findAllergien(Query q, Connection con) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = null;

			if (q.getExpression("zut_id") != null){
				sql = "select id, name from allergie join zut2all "
					+ " on allergie.id = zut2all.all_id where zut_id = ?";
				stmt = con.prepareStatement(sql);
				stmt.setLong(1, (Long)q.getExpression("zut_id").getValue());
			}
			else {
				sql = "select id, name from allergie";
				StatementBuilder builder = new StatementBuilder();
				builder.add("id", q.getExpression("id"));
				builder.add("name", q.getExpression("name"));
				stmt = builder.buildQuery(sql, con);
			}
			
			if (q.getResultType() == ResultType.NAMES){
				return makeNameList(stmt.executeQuery());
			}
			else {
				return makeObjectList(stmt.executeQuery());
			}
		}
		finally {
			DbUtil.close(rs, stmt);
		}	
	}
	
	public static void insertAllergie(Allergie a, Connection con) throws SQLException {
		String sql = "insert into allergie(id, name) values(?,?)";
		
		PreparedStatement stmt = null;
		
		try {
			Long id = DbUtil.createId();
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, id);
			setStmtParams(a, stmt, 2);
			stmt.execute();
			a.setId(id);
		}
		finally {
			DbUtil.close(stmt);
		}
	}

	public static void updateAllergie(Allergie a, Connection con) throws SQLException {
		String sql = "update allergie set name = ? where id = ?)";

		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			setStmtParams(a, stmt, 1);
			stmt.setObject(2, a.getId());				
			int updateCount = stmt.executeUpdate();
			if (updateCount < 1){
				throw new DasException("Datensatz mit id " + a.getId() + " nicht gefunden");
			}
		}
		finally {
			DbUtil.close(stmt);
		}
	}
	
	public static void deleteAllergie(Long id, Connection con) throws SQLException {
		String sql = "delete from allergie where id = ?";
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
			result.add(new ObjName(Long.valueOf(rs.getLong("id")), rs.getString("name")));
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
	
	protected static Allergie retrieveObject(ResultSet rs) throws SQLException {
		Allergie a = new Allergie();
		a.setId(rs.getLong("id"));
		a.setName(rs.getString("name"));		
		
		return a;
	}
	
	protected static void setStmtParams(Allergie a, PreparedStatement stmt, int index)
		throws SQLException {
		
		DbUtil.setString(stmt, index++, a.getName());		
	}	
}
