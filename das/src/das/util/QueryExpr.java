package das.util;

public class QueryExpr {
	
	public static final int AND = 1;
	public static final int OR = 2;
	public static final int NOT = 3;
		
	private String field;
	private int logOp = AND;
	private String compOp = "=";
	private Object value;

	public QueryExpr(int logOp, String field, String compOp, Object value){
		this.logOp = logOp;
		this.field = field;
		this.compOp = compOp;
		this.value = value;
	}		
	
	public QueryExpr(int logOp, String field, String value){
		this(logOp, field, "=", value);
	}	
	
	public QueryExpr(String field, Object value){
		this(AND, field, "=", value);
	}
	
	public QueryExpr(String field){
		this(AND, field, "=", null);
	}

	public String getField() {
		return field;
	}

	public int getLogOp() {
		return logOp;
	}

	public String getCompOp() {
		return compOp;
	}

	public Object getValue() {
		return value;
	}	
}
