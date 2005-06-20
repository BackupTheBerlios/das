/**
 * author: Mario 
 * date: 18.06.2005
 */ 

package das.dao;

import das.bl.model.Gruppe;
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
 * Data Access Object zur Verarbeitung der Datenbankzugriffe im Zusammenhang mit Gruppen.
 */
public class GruppeDao {
	
	public static List findGruppen(Query q, Connection con) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StatementBuilder builder = new StatementBuilder();
			builder.add("id", q.getExpression("id"));
			builder.add("name", q.getExpression("name"));
			
			if (q.getResultType() == ResultType.NAMES){				
				stmt = builder.buildQuery("select id, name from gruppe", con);
				return makeNameList(stmt.executeQuery());
			}
			else {
				stmt = builder.buildQuery(
					"select id, name from gruppe ",
					con);
				return makeObjectList(stmt.executeQuery());
			}
		}
		finally {
			DbUtil.close(rs, stmt);
		}		
	}
	
	public static void insertGruppe(Gruppe g, Connection con) throws SQLException {
		String sql = "insert into gruppe(id, name) values(?,?)";
		
		PreparedStatement stmt = null;
		
		try {
			Long id = DbUtil.createId();
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, id);
			setStmtParams(g, stmt, 2);		
			stmt.execute();
			g.setId(id);
		}
		finally {
			DbUtil.close(stmt);
		}
	}

	public static void updateGruppe(Gruppe g, Connection con) throws SQLException {
		String sql = "update gruppe set name = ? where id = ?)";

		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			setStmtParams(g, stmt, 1);
			stmt.setObject(2, g.getId());				
			int updateCount = stmt.executeUpdate();
			if (updateCount < 1){
				throw new DasException("Datensatz mit id " + g.getId() + " nicht gefunden");
			}
		}
		finally {
			DbUtil.close(stmt);
		}
	}
	
	public static void deleteGruppe(Long id, Connection con) throws SQLException {
		String sql = "delete from gruppe where id = ?";
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
	
	protected static Gruppe retrieveObject(ResultSet rs) throws SQLException {
		Gruppe g = new Gruppe();
		g.setId(rs.getLong("id"));
		g.setName(rs.getString("name"));		
		
		return g;
	}
	
	protected static void setStmtParams(Gruppe g, PreparedStatement stmt, int index)
		throws SQLException {
		
		DbUtil.setString(stmt, index++, g.getName());		
	}
}
