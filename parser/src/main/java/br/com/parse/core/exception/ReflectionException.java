package br.com.parse.core.exception;

public class ReflectionException extends Exception {
	
    private static final long serialVersionUID = 6765416573955357173L;

    /**
     * Construtor.
     * @param cause problema causador do erro
     */
    public ReflectionException(Throwable cause) {
        super(cause);
    }

    /**
     * Construtor.
     * @param message mensagem de erro
     * @param cause problema causador do erro
     */
    public ReflectionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Construtor.
     * @param message mensagem de erro
     */
    public ReflectionException(String message) {
        super(message);
    }

    /**
     * Construtor padr�o.
     */
    public ReflectionException() {
        super();
    }

}
