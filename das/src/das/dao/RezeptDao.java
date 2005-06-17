package das.dao;

import das.DasException;
import das.bl.model.Rezept;
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
 * Data Access Object zur verarbeitung der datenbankzugriffe im zusammenhang mit Rezepten.
 */
public class RezeptDao {
    
    public static List findRezepte(Query q, Connection con) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            StatementBuilder builder = new StatementBuilder();
            builder.add("id", q.getExpression("id"));
            builder.add("name", q.getExpression("name"));
            builder.add("anleitung", q.getExpression("anleitung"));
            
            if (q.getResultType() == ResultType.NAMES){
                stmt = builder.buildQuery("select id, name from rezept", con);
                return makeNameList(stmt.executeQuery());
            } else {
                stmt = builder.buildQuery(
                        "select id, name, anleitung, bzr_login from rezept ",
                        con);
                return makeObjectList(stmt.executeQuery());
            }
        } finally {
            DbUtil.close(rs, stmt);
        }
    }
    
    
    public static void insertRezept(Rezept r, Connection con){
        throw new RuntimeException("todo");
    }
    
    public static void updateRezept(Rezept r, Connection con){
        throw new RuntimeException("todo");
    }
    
    public static void deleteRezept(Long id, Connection con){
        throw new RuntimeException("todo");
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
    
    protected static Rezept retrieveObject(ResultSet rs) throws SQLException {
        Rezept r = new Rezept();
        r.setId(rs.getLong("id"));
        r.setName(rs.getString("name"));
        r.setAnleitung(rs.getString("anleitung"));
        //bzr_login
        
        return r;
    }
}
