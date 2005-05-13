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
 * @author admbaby
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBConnector {
	static Connection conn;
	static DataSource ds;
	public void init() throws Exception {
		Context ctx = new InitialContext();
		if (ctx == null) {
			throw new Exception("Error in das.db.DBConnector - NO Context found");
		}
		try {
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/das");
		} catch (NamingException e) {
			// TODO: handle exception
		}
	}
	public static Connection getConnection() {
		
		return null;
		
	}
}
