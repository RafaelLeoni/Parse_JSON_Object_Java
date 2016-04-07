package exception;

public class ParseException extends Exception {
	
	private static final long serialVersionUID = -518498464248042868L;

	public ParseException() {
		super();
	}
	
	public ParseException(String e) {
		super(e);
	}
	
	public ParseException(String e, Throwable t) {
		super(e,t);
	}
	
	public ParseException(Throwable t) {
		super(t);
	}

}
