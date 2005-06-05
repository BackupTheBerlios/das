package das.dao;

import das.DasException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Utility methoden fuer den datenbankzugriff.
 */
public class DbUtil {
	
	/**
	 * JNDI name der JDBC DataDatasource.
	 */
	public static final String DATASOURCE_JNDI_NAME = "java:comp/env/jdbc/das";
	
	/**
	 * Liefert eine Connection aus dem connection pool. Der aufrufer ist fuer das
	 * schliessen der Connection mittels close verantwortlich.
	 */
	public static Connection getConnection(){
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup(DATASOURCE_JNDI_NAME);
			return ds.getConnection();
		}
		catch(Exception ex){
			throw new DasException("Fehler beim oeffnen der datenquelle " 
				+ DATASOURCE_JNDI_NAME, ex);
		}
	}
	
	/**
	 * Schliesst die uebergebenen JDBC objekte.
	 */
	public static void close(ResultSet rs, Statement stmt, Connection con){
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	/**
	 * Schliesst das uebergebene Connection objekt.
	 */
	public static void close(Connection con){
		close(null, null, con);
	}

	/**
	 * Schliesst das uebergebene Statement objekt.
	 */	
	public static void close(Statement stmt){
		close(null, stmt, null);
	}

	/**
	 * Schliesst die uebergebenen JDBC objekte.
	 */	
	public static void close(ResultSet rs, Statement stmt){
		close(rs, stmt, null);
	}
	
	/**
	* Erzeugt eine neue datenbankweit eindeutige ID.
	*/
	public static Long createId() throws SQLException {
		Connection con = null;
		try {
			con = getConnection();
			CallableStatement stmt = con.prepareCall("{ ? = call nextval('seq') }");
			stmt.registerOutParameter(1, Types.BIGINT);
			stmt.execute();
			return stmt.getLong(1);
		}
		finally {
			close(con);
		}
	}
	
	/**
	 * Bereitet den String s fuer die verwendung als wert in einm SQL statement vor
	 * indem steuerzeichen durch ihre escape sequenz ersetzt werden.
	 */
	public static String sqlEscape(String s){
		if (s == null)
			return null;
		return s.replace("\'", "\'\'");
	}
	
	/**
	 * Liest ein Float objekt aus einem ResultSet. Wenn das feld im ResultSet NULL ist
	 * wird null zurueckgeliefert.
	 */
	public static Float getFloat(ResultSet rs, String field) throws SQLException {
		float nbr = rs.getFloat(field);
		if (rs.wasNull())
			return null;
		return nbr;
	}
	
	/**
	 * Liest ein Long objekt aus einem ResultSet. Wenn das feld im ResultSet NULL ist
	 * wird null zurueckgeliefert.
	 */	
	public static Long getLong(ResultSet rs, String field) throws SQLException {
		long nbr = rs.getLong(field);
		if (rs.wasNull())
			return null;
		return nbr;
	}

	/**
	 * Liest ein Integer objekt aus einem ResultSet. Wenn das feld im ResultSet NULL ist
	 * wird null zurueckgeliefert.
	 */
	public static Integer getInteger(ResultSet rs, String field) throws SQLException {
		int nbr = rs.getInt(field);
		if (rs.wasNull())
			return null;
		return nbr;
	}
	
	/**
	 * Setzt den wert eines statement parameters vom typ String.
	 */
	public static void setString(PreparedStatement stmt, int index, String value)
		throws SQLException {
		
		if (value == null)
			stmt.setNull(index, Types.VARCHAR);
		else
			stmt.setString(index, value);
	}
	
	/**
	 * Setzt den wert eines statement parameters vom typ Long.
	 */
	public static void setLong(PreparedStatement stmt, int index, Long value)
		throws SQLException {
		
		if (value == null)
			stmt.setNull(index, Types.INTEGER);
		else
			stmt.setObject(index, value);
	}

	/**
	 * Setzt den wert eines statement parameters vom typ Float.
	 */
	public static void setFloat(PreparedStatement stmt, int index, Float value)
		throws SQLException {
		
		if (value == null)
			stmt.setNull(index, Types.FLOAT);
		else
			stmt.setObject(index, value);
	}
}
