/*
 * Created on 12.05.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package das.db;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author Arash
 *
 * DB Connection Pool
 */
public class DBConnector {
	static Connection conn;
	static DataSource ds;
	public static void init() throws Exception {
		Context ctx = new InitialContext();
		if (ctx == null) {
			throw new Exception("Error in das.db.DBConnector - NO Context found");
		}
		try {
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/das");
		} catch (NamingException e) {
			throw new Exception("Error in das.db.DBConnector - lookup failed");
		}
	}
	public static Connection getConnection() throws Exception {
		if (ds==null) 
			init();
		try {
			conn=ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			
			// TODO: handle exception
		}
		return conn;
		
	}
}
