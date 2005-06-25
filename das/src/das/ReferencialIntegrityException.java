package das;

public class ReferencialIntegrityException extends DasException {
	public ReferencialIntegrityException(){}

	public ReferencialIntegrityException(String msg){
		super(msg);
	}

	public ReferencialIntegrityException(String msg, Throwable th){
		super(msg, th);
	}
	
	public ReferencialIntegrityException(Throwable th){
		super(th);
	}
}
