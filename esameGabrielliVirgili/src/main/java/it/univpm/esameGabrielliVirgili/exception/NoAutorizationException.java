/**
 * 
 */
package it.univpm.esameGabrielliVirgili.exception;

/**
 * Classe che gestisce le eccezioni relative all'inserimento di una stagione non permessa
 * 
 * Le stagioni antecedenti al 2018-19 non sono permesse
 * 
 * @author lorenzo gabrielli e claudio virgili
 *
 */
public class NoAutorizationException extends Exception{

	private String errorMessage;
	/**
	 * ID seriale generato automaticamente
	 */
	private static final long serialVersionUID = -6333998481395714935L;

	/**
	 * costruttore di default
	 */
	
	public NoAutorizationException() {
		super();
	}
	
	/**
	 * costruttore
	 * 
	 * @param errorMessage messaggio di errore
	 */
	
	public NoAutorizationException(String errorMessage) {
		this.errorMessage=errorMessage;
	}
	
	/**
	 * get messaggio di errore
	 * 
	 * @return errorMessage
	 */
	
	public String getErrorMessage() {
		return errorMessage;
	}
}
