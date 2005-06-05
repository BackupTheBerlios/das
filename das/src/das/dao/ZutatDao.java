package das.dao;

import das.DasException;
import das.bl.model.Zutat;
import das.util.ObjName;
import java.sql.Connection;
import java.util.List;
import das.util.Query;
import das.util.ResultType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data Access Object zur verarbeitung der datenbankzugriffe im zusammenhang mit zutaten.
 */
public class ZutatDao {
		
	public static List findZutaten(Query q, Connection con) throws SQLException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StatementBuilder builder = new StatementBuilder();
			builder.add("id", q.getExpression("id"));
			builder.add("name", q.getExpression("name"));
			builder.add("kat_id", q.getExpression("kat_id"));
			
			if (q.getResultType() == ResultType.NAMES){				
				stmt = builder.buildQuery("select id, name from zutat", con);
				return makeNameList(stmt.executeQuery());
			}
			else {
				stmt = builder.buildQuery(
					"select id, name, einheit, kalorien, fett, zucker, kat_id from zutat ",
					con);
				return makeObjectList(stmt.executeQuery());
			}
		}
		finally {
			DbUtil.close(rs, stmt);
		}		
	}
	
	public static void insertZutat(Zutat z, Connection con) throws SQLException{
		String sql = "insert into zutat(id, name, einheit, kalorien, fett, zucker, kat_id) "
				+ "values(?,?,?,?,?,?,?)";
		
		PreparedStatement stmt = null;
		
		try {
			Long id = DbUtil.createId();
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, id);
			setStmtParams(z, stmt, 2);
			stmt.execute();
			z.setId(id);
		}
		finally {
			DbUtil.close(stmt);
		}
	}

	public static void updateZutat(Zutat z, Connection con) throws SQLException{
		String sql = "update zutat set name = ?, einheit = ?, kalorien = ?, fett = ?, "
			+ "zucker = ?, kat_id = ? where id = ?)";

		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			setStmtParams(z, stmt, 1);
			stmt.setObject(7, z.getId());
			int updateCount = stmt.executeUpdate();
			if (updateCount < 1){
				throw new DasException("Datensatz mit id " + z.getId() + " nicht gefunden");
			}
		}
		finally {
			DbUtil.close(stmt);
		}
	}
	
	public static void deleteZutat(Long id, Connection con) throws SQLException{
		String sql = "delete from zutat where id = ?";
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
			result.add(new ObjName(rs.getString("id"), rs.getString("name")));
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
	
	protected static Zutat retrieveObject(ResultSet rs) throws SQLException {
		Zutat z = new Zutat();
		z.setId(rs.getLong("id"));
		z.setName(rs.getString("name"));
		z.setEinheit(rs.getString("einheit"));
		z.setKalorien(DbUtil.getFloat(rs, "kalorien"));
		z.setFett(DbUtil.getFloat(rs, "fett"));
		z.setZucker(DbUtil.getFloat(rs, "zucker"));
		z.setKatId(DbUtil.getLong(rs, "kat_id"));		
		
		return z;
	}
	
	protected static void setStmtParams(Zutat z, PreparedStatement stmt, int index)
		throws SQLException {
		
		DbUtil.setString(stmt, index++, z.getName());
		DbUtil.setString(stmt, index++, z.getEinheit());
		DbUtil.setFloat(stmt, index++, z.getKalorien());
		DbUtil.setFloat(stmt, index++, z.getFett());
		DbUtil.setFloat(stmt, index++, z.getZucker());
		DbUtil.setLong(stmt, index++, z.getKatId());		
	}
}
