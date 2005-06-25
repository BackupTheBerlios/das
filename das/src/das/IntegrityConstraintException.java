package das;

public class IntegrityConstraintException extends DasException {
	public IntegrityConstraintException(){}

	public IntegrityConstraintException(String msg){
		super(msg);
	}

	public IntegrityConstraintException(String msg, Throwable th){
		super(msg, th);
	}
	
	public IntegrityConstraintException(Throwable th){
		super(th);
	}
}
