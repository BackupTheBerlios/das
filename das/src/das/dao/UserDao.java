/**
 * author: Mario 
 * date: 17.06.2005
 */ 

package das.dao;

import das.bl.model.User;
import java.sql.Connection;
import java.util.List;
import das.util.Query;
import das.DasException;
import das.util.ObjName;
import das.util.ResultType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data Access Object zur verarbeitung der datenbankzugriffe im zusammenhang mit Usern.
 */
public class UserDao {
		
	public static List findUsers(Query q, Connection con) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StatementBuilder builder = new StatementBuilder();
			builder.add("id", q.getExpression("id"));                        
			builder.add("name", q.getExpression("name"));
			builder.add("gru_id", q.getExpression("gru_id"));
                        
			if (q.getResultType() == ResultType.NAMES){				
				stmt = builder.buildQuery("select id, name from benutzer", con);
				return makeNameList(stmt.executeQuery());
			}
			else {
				stmt = builder.buildQuery(
					"select id, name, login, passwort, email, gru_id from benutzer ",
					con);
				return makeObjectList(stmt.executeQuery());
			}
		}
		finally {
			DbUtil.close(rs, stmt);
		}		
	}
      
               
	public static void insertUser(User u, Connection con) throws SQLException {
		String sql = "insert into benutzer(id, name, login, passwort, email, gru_id) " 
                        + "values(?,?,?,?,?,?)";
		
		PreparedStatement stmt = null;
		
		try {
			Long id = DbUtil.createId();
			stmt = con.prepareStatement(sql);                     
			stmt.setObject(1, id);
			setStmtParams(u, stmt, 2);
			stmt.execute();
			u.setId(id);
		}
		finally {
			DbUtil.close(stmt);
		}
	}

	public static void updateUser(User u, Connection con) throws SQLException{
		String sql = "update benutzer set name = ?, login = ?, passwort = ?, " + 
                        "email = ?, gru_id = ? where id = ?)";

		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			setStmtParams(u, stmt, 1);
			stmt.setObject(6, u.getId());
			int updateCount = stmt.executeUpdate();
			if (updateCount < 1){
				throw new DasException("Datensatz mit id " + u.getId() + " nicht gefunden");
			}
		}
		finally {
			DbUtil.close(stmt);
		}
	}
	
	public static void deleteUser(Long id, Connection con) throws SQLException{
		String sql = "delete from benutzer where id = ?";
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
        
        public static void saveBzr2All(User u, Connection con) throws SQLException {
		PreparedStatement stmt = null;
		
		try {
			String sql = "delete from bzr2all where bzr_id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, u.getId());
			stmt.execute();
			stmt.close();
			
			sql = "insert into bzr2all(bzr_id, all_id) values(?,?)";
			stmt = con.prepareStatement(sql);
			
			for (ObjName n : u.getAllergien()){
				stmt.setLong(1, u.getId());
				stmt.setLong(2, (Long)n.getId());
				stmt.execute();
				stmt.clearParameters();
			}
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
	
	protected static User retrieveObject(ResultSet rs) throws SQLException {
		User u = new User();
		u.setId(rs.getLong("id"));                
		u.setName(rs.getString("name"));
		u.setLogin(rs.getString("login")); 
		u.setPasswort(rs.getString("passwort"));                
		u.setEmail(rs.getString("email"));	
		u.setGruId(DbUtil.getLong(rs, "gru_id"));
                
		return u;
	}
	
	protected static void setStmtParams(User u, PreparedStatement stmt, int index)
		throws SQLException {
		
		DbUtil.setString(stmt, index++, u.getName());
		DbUtil.setString(stmt, index++, u.getLogin());
		DbUtil.setString(stmt, index++, u.getPasswort());                
		DbUtil.setString(stmt, index++, u.getEmail());	
		DbUtil.setLong(stmt, index++, u.getGruId());                
	}
        
}
