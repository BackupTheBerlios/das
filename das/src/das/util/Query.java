package das.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Eine DBMS- und schema-unabhaengige repraesentation von abfragekriterien. 
 * Query objekte werden von benutzern der backend services erzeugt 
 * und von DAOs in SQL statements umgewandelt.
 *
 * @author k
 */
public class Query {
	
	protected ResultType resultType;
	protected Map<String,Object> expressions = new TreeMap<String,Object>();
	
	/**
	 * Erzeugt ein Query objekt mit dem gegebenen ResultType. Der ResultType
	 * bestimmt ob das ergebnis der abfrage eine liste von vollstaendig geladenen
	 * domain objekten ist oder eine liste von ObjName objekten.
	 */
	public Query (ResultType rt){
		resultType = rt;
	}
	
	/**
	 * Liefert den ResultType des Query objektes.
	 */
	public ResultType getResultType(){
		return resultType;
	}
	
	/**
	 * Fuegt einen abfrageausdruck hinzu.
	 */
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
	
	/**
	 * Liefert eine Collection aller abfrageausdruecke.
	 */
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
	
	/**
	 * Liefert true wenn die Query keine abfrageausdruecke enthaelt.
	 */
	public boolean isEmpty() {
		return expressions.isEmpty();
	}
	
	/**
	 * Liefert die abfrageausdruecke mit dem gegebenen namen. Wenn kein ausdruck mit 
	 * diesem namen existiert, wird eine leere Collection geliefert.
	 */
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
	
	/**
	 * Liefert den ersten abfrageausdruck mit dem gegebenen namen oder null wenn kein
	 * ausdruck mit diesem namen existiert.
	 */
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
