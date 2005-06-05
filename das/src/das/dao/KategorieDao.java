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

/**
 * Data Access Object zur verarbeitung der datenbankzugriffe im zusammenhang mit Kategorien.
 */
public class KategorieDao {
	
	public static List findKategorien(Query q, Connection con) throws SQLException {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StatementBuilder builder = new StatementBuilder();
			builder.add("id", q.getExpression("id"));
			stmt = builder.buildQuery("select id, name from kategorie", con);
			rs = stmt.executeQuery();
			
			if (q.getResultType() == ResultType.NAMES){
				return makeNameList(rs);
			}
			else {
				return makeObjectList(rs);
			}
		}
		finally {
			DbUtil.close(rs, stmt, null);
		}
	}
	
	public static void insertKategorie(Kategorie k, Connection con){
		throw new RuntimeException("todo");
	}

	public static  void updateKategorie(Kategorie k, Connection con){
		throw new RuntimeException("todo");
	}
	
	public static  void deleteKategorie(Long id, Connection con){
		throw new RuntimeException("todo");
	}	
	
	protected static List makeNameList(ResultSet rs) throws SQLException {
		
		List result = new ArrayList();
		while (rs.next()){
			result.add(new ObjName(rs.getString("id"), rs.getString("name")));
		}
		
		return result;
	}
	
	protected static List makeObjectList(ResultSet rs){
		throw new RuntimeException("todo");
	}
}
