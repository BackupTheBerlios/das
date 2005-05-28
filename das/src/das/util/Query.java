package das.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Query {
	
	protected ResultType resultType;
	protected Map<String,Object> expressions = new TreeMap<String,Object>();
	
	public Query (ResultType rt){
		resultType = rt;
	}
	
	public ResultType getResultType(){
		return resultType;
	}
	
	public void addExpression(QueryExpr expr){
		String name = expr.getField();
		Object obj = expressions.get(name);
		
		if (obj == null){
			expressions.put(name, expr);
		}
		else {
			if (obj instanceof List){
				((List)obj).add(expr);
			}
			else {
				List<QueryExpr> lst = new ArrayList<QueryExpr>(3);
				lst.add((QueryExpr)obj);
				lst.add(expr);
			}
		}
	}
	
	public Collection<QueryExpr> getExpressions(){
		List<QueryExpr> result = new ArrayList<QueryExpr>();
		
		for (Object item : expressions.values()){
			if (item instanceof List){
				for (QueryExpr expr : (List<QueryExpr>)item){
					result.add(expr);
				}
			}
			else {
				result.add((QueryExpr)item);
			}
		}
		
		return result;
	}
	
	public boolean isEmpty() {
		return expressions.isEmpty();
	}
	
	public Collection<QueryExpr> getExpressions(String name){
		Object obj = expressions.get(name);
		
		if (obj == null){
			return Collections.<QueryExpr>emptyList();
		}
		else if (obj instanceof List){
			return (List)obj;
		}
		else {
			List<QueryExpr> result = new ArrayList<QueryExpr>(1);
			result.add((QueryExpr)obj);
			return result;
		}
	}
	
	public QueryExpr getExpression(String name){
		Object obj = expressions.get(name);
		
		if (obj == null){
			return null;
		}
		else if (obj instanceof List){
			return ((List<QueryExpr>)obj).get(0);
		}
		else {
			return (QueryExpr)obj;
		}		
	}
}
