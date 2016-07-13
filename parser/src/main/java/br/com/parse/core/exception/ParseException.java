package br.com.parse.core.exception;

public class ParseException extends RuntimeException {
	
	private static final long serialVersionUID = -518498464248042868L;

	public ParseException() {
		super();
	}
	
	public ParseException(Exception e) {
		super(e);
	}

	public ParseException(String msg) {
		super(msg);
	}
	
	public ParseException(String msg, Exception e) {
		super(msg,e);
	}

}
