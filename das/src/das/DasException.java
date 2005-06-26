package das;

/**
 * Basisklasse aller DAS exceptions
 *
 * @author k
 */
public class DasException extends RuntimeException {

	public DasException(){}

	public DasException(String msg){
		super(msg);
	}

	public DasException(String msg, Throwable th){
		super(msg, th);
	}
	
	public DasException(Throwable th){
		super(th);
	}

}
