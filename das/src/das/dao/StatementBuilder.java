package das.dao;

import das.util.QueryExpr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class StatementBuilder {
	
	private LinkedHashMap<String,QueryExpr> fields = new LinkedHashMap<String,QueryExpr>();
	
	public void add(String name, QueryExpr expr){
		if (expr == null)
			return;
		
		fields.put(name, expr);
	}
	
	public PreparedStatement buildQuery(String select, Connection con) 
		throws SQLException {
		
		String where = buildWhere();
		PreparedStatement stmt = con.prepareStatement(select + " " + where);
		setParams(stmt);
		
		return stmt;
	}
	
	protected String buildWhere(){
		if (fields.isEmpty())
			return "";
		
		StringBuilder sb = new StringBuilder("where ");
		int i = 0;
		
		for (Map.Entry<String,QueryExpr> entry : fields.entrySet()){
			QueryExpr expr = entry.getValue();
			
			if (i++ > 0){
				int logOp = expr.getLogOp();
				if (logOp == QueryExpr.AND)
					sb.append(" and ");
				else if (logOp == QueryExpr.OR)
					sb.append(" or ");
				else if (logOp == QueryExpr.NOT)
					sb.append(" and not ");
			}

			sb.append(entry.getKey() + " ");
			
			if (expr.getValue() instanceof String
					&& ((String)expr.getValue()).indexOf('*') != -1){
				sb.append(" ilike ?");
			}
			else {
				sb.append(expr.getCompOp() + " ?");
			}
		}
		
		return sb.toString();
	}
	
	protected void setParams(PreparedStatement stmt) throws SQLException {
		
		int i = 1;
		
		for (QueryExpr expr : fields.values()){
			Object value = expr.getValue();
			if (value instanceof String){
				String s = ((String)value).replaceAll("\\u002A", "%");
				System.out.println("nach regex: " + s);
				stmt.setString(i, s);
			}
			else if (value instanceof Long){
				stmt.setLong(i, (Long)value);
			}
			else if (value instanceof Integer){
				stmt.setInt(i, (Integer)value);
			}
			else if (value instanceof Double){
				stmt.setDouble(i, (Double)value);
			}
			else if (value instanceof Float){
				stmt.setFloat(i, (Float)value);
			}
			else if (value instanceof Date){
				stmt.setDate(i, new java.sql.Date(((Date)value).getTime()));
			}
			
			i++;
		}
	}
}
