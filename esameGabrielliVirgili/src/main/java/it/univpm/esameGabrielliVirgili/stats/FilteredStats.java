/**
 * 
 */
package it.univpm.esameGabrielliVirgili.stats;

import it.univpm.esameGabrielliVirgili.exception.NoAutorizationException;

/**
 * interfaccia che contiene i vari tipi di filtri per le statistiche relative ad una sola competizione
 * 
 * @author lorenzo gabrielli e claudio virgili
 *
 */
public interface FilteredStats {
	
	/**
	 * Restituisce le statistiche per competizione filtrate per stagioni
	 * @param seasons
	 * @return int[]
	 * @throws NoAutorizationException, stagione non ammessa se antecedente al 2018-19
	 */
	
	public abstract int[] filterSeasons(String seasons) throws NoAutorizationException;

	/**
	 * Restituisce le statistiche per competizione filtrate per squadre
	 * @param nameTeams
	 * @return int[]
	 */
	
	public abstract int[] filterTeams(String nameTeams);

	/**
	 * Restituisce le statistiche per competizione filtrate per stagioni e squadre
	 * @param seasons
	 * @param nameTeams
	 * @return int[]
	 * @throws NoAutorizationException, stagione non ammessa se antecedente al 2018-19
	 */
	
	public abstract int[] filterSeasonsTeams(String seasons, String nameTeams) throws NoAutorizationException;

	/**
	 * Restituisce le statistiche per competizione filtrate per stagione
	 * @param startDate
	 * @param endDate
	 * @return int[]
	 */
	
	public abstract int[] filterDate(String startDate, String endDate);

	/**
	 * Restituisce le statistiche per competizione filtrate per stagione
	 * @param startDate
	 * @param endDate
	 * @param nameTeams
	 * @return int[]
	 */
	
	public abstract int[] filterDateTeams(String startDate, String endDate, String nameTeams);
}
