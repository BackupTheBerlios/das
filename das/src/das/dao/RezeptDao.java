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
import java.util.*;
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
    
    
    public static void insertRezept(Rezept r, Connection con) throws SQLException{
        String sql = "insert into rezept(id, name, anleitung, bzr_login) "
                + "values(?,?,?,?)";
        
        PreparedStatement stmt = null;
        
        try {
            Long id = DbUtil.createId();
            stmt = con.prepareStatement(sql);
            stmt.setObject(1, id);
            setStmtParams(r, stmt, 2);
            stmt.setObject(4, r.getBenutzer());
            stmt.execute();
            r.setId(id);
            
        } finally {
            DbUtil.close(stmt);
        }
    }
    
    public static void updateRezept(Rezept r, Connection con) throws SQLException{
        String sql = "update rezept set name = ?, anleitung = ?"
                + " where id = ?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            setStmtParams(r, stmt, 1);
            stmt.setObject(3, r.getId());
            
            int updateCount = stmt.executeUpdate();
            if (updateCount < 1){
                throw new DasException("Rezept mit id " + r.getId() + " nicht gefunden");
            }
        } finally {
            DbUtil.close(stmt);
        }
    }
    
    public static void deleteRezept(Long id, Connection con) throws SQLException{
        String sql = "delete from rezept where id = ?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setObject(1, id);
            stmt.execute();
        } finally {
            DbUtil.close(stmt);
        }
    }
    
    public static void saveZutaten(Rezept r, Connection con) throws SQLException{
        Set set = r.zutaten.keySet();
        Iterator iter = set.iterator();
        
        PreparedStatement stmt = null;
        
        try {
            String sql = "delete from zut2rez where rez_id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, r.getId());
            stmt.execute();
            stmt.close();
            
            sql = "insert into zut2rez(zut_id, rez_id, menge) values(?,?,?)";
            stmt = con.prepareStatement(sql);
            
            while(iter.hasNext()){
                Long id = (Long)iter.next();
                Long wert = r.zutaten.get(id);
                stmt.setLong(1, id);
                stmt.setLong(2, r.getId());
                stmt.setLong(3, wert);
                stmt.execute();
                stmt.clearParameters();
            }
        } finally {
            DbUtil.close(stmt);
        }
    }
    
    public static Map<Long,Long> loadZutaten(Long id, Connection con) throws SQLException{
        Map<Long,Long> zutaten = new HashMap<Long,Long>();
        
        String sql = "select zut_id, menge from zut2rez where rez_id = ?";
        PreparedStatement stmt = null;
        StatementBuilder builder = new StatementBuilder();
        stmt = builder.buildQuery(sql, con);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()){
            zutaten.put(rs.getLong("zut_id"), rs.getLong("menge"));
        }
        
        return zutaten;
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
        r.setBenutzer(rs.getString("bzr_login"));
        return r;
    }
    
    protected static void setStmtParams(Rezept r, PreparedStatement stmt, int index)
    throws SQLException {
        DbUtil.setString(stmt, index++, r.getName());
        DbUtil.setString(stmt, index++, r.getAnleitung());
    }
}
