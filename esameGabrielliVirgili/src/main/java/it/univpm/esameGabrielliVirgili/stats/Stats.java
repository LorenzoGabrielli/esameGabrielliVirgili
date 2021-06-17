/**
 * 
 */
package it.univpm.esameGabrielliVirgili.stats;

import java.util.Vector;
import it.univpm.esameGabrielliVirgili.model.Competition;

/**
 * Interfaccia contenente i metodi da implementare nelle classi del package stats
 * 
 * @author lorenzo gabrielli e claudio virgili
 *
 */
public interface Stats {
	
	/**
	 * Restituisce il minimo di squadre di una competizione
	 * @param elenco vettore di competizioni da cui ricavare i dati
	 * @return int numero minimo di squadre
	 */

	public abstract int minTeams(Vector<Competition> elenco);
	
	/**
	 * Restituisce il massimo di squadre di una competizione
	 * @param elenco vettore di competizioni da cui ricavare i dati
	 * @return int numero massimo di squadre
	 */
	
	public abstract int maxTeams(Vector<Competition> elenco);
	
	/**
	 * Restituisce il valore medio del numero di squadre delle competizioni
	 * @param elenco vettore di competizioni da cui ricavare i dati 
	 * @return float numero medio di squadre
	 */
	
	public abstract float avTeams(Vector<Competition> elenco);
	
	/**
	 * Restituisce la durata media delle competizioni in giorni/mesi
	 * @param elenco vettore di competizioni da cui ricavare i dati
	 * @return int[] durata media in giorni, mesi
	 */
	
	public abstract int[] avDuration(Vector<Competition> elenco);
	
	/**
	 * Restituisce il numero medio delle stagioni salvate per ogni competizione
	 * @param elenco vettore di competizioni da cui ricavare i dati
	 * @return float numero medio di stagioni disponibili
	 */
	
	public abstract float avAvailableSeason(Vector<Competition> elenco);
	
	/**
	 * Restituisce il numero di paritite con l'esito del primo tempo uguale all'esito finale
	 * 
	 * @param yearSeason anno di inizio delle stagioni, separate da ", "
	 * @param startDate data d'inizio per filtraggio data
	 * @param endDate data di fine per filtraggio data
	 * @param nameTeam nome delle squadre per filtraggio per squadre, separate da ", "
	 * @return int numero di partite
	 */
	
	public abstract int firstEqualsFinal(String yearSeason, String startDate, String endDate, String nameTeam);
	
	/**
	 * Restituisce il numero di paritite con l'esito del primo tempo ribaltato rispetto all'esito finale
	 * 
	 * @param yearSeason anno di inizio delle stagioni, separate da ", "
	 * @param startDate data d'inizio per filtraggio data
	 * @param endDate data di fine per filtraggio data
	 * @param nameTeam nome delle squadre per filtraggio per squadre, separate da ", "
	 * @return int numero di partite
	 */
	
	public abstract int firstWinFinalLose(String yearSeason, String startDate, String endDate, String nameTeam);
	
	/**
	 * Restituisce il numero di paritite con l'esito del primo tempo pareggio e l'esito finale non pari
	 * 
	 * @param yearSeason anno di inizio delle stagioni, separate da ", "
	 * @param startDate data d'inizio per filtraggio data
	 * @param endDate data di fine per filtraggio data
	 * @param nameTeam nome delle squadre per filtraggio per squadre, separate da ", "
	 * @return int numero di partite
	 */
	
	public abstract int firstDrawFinalWin(String yearSeason, String startDate, String endDate, String nameTeam);
	
	/**
	 * Restituisce il numero di paritite con l'esito del primo tempo non pari e l'esito finale pari
	 * 
	 * @param yearSeason anno di inizio delle stagioni, separate da ", "
	 * @param startDate data d'inizio per filtraggio data
	 * @param endDate data di fine per filtraggio data
	 * @param nameTeam nome delle squadre per filtraggio per squadre, separate da ", "
	 * @return int numero di partite
	 */
	
	public abstract int firstWinFinalDraw(String yearSeason, String startDate, String endDate, String nameTeam);
	
	/**
	 * Restituisce il numero di arbitri per stagione
	 * 
	 * @param yearSeason anno di inizio delle stagioni, separate da ", "
	 * @param startDate data d'inizio per filtraggio data
	 * @param endDate data di fine per filtraggio data
	 * @param nameTeam nome delle squadre per filtraggio per squadre, separate da ", "
	 * @return int numero di partite
	 */
	
	public abstract int numberReferees(String yearSeason, String startDate, String endDate, String nameTeam);
}
