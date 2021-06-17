/**
 * 
 */
package it.univpm.esameGabrielliVirgili.exception;

/**
 * Classe che gestisce le eccezioni relative all'inserimento errato della data
 * 
 * @author lorenzo gabrielli e claudio virgili
 *
 */
public class DateArgumentException extends IllegalArgumentException{
	
	private String errorMessage;

	/**
	 * Serial ID dell'eccezione generato in automatico
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Costruttore default
	 */
	
	public DateArgumentException() {
		super();
	}
	
	/**
	 * Costruttore
	 * 
	 * @param errorMessage messaggio di errore
	 */
	
	public DateArgumentException(String errorMessage) {
		super(errorMessage);
		this.errorMessage=errorMessage;
	}
	
	/**
	 * Get messaggio di errore
	 * 
	 * @return String errorMessage
	 */
	public String getMessage() {
		return errorMessage;
	}
	
}
