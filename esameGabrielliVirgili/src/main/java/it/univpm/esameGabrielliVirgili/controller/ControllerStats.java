/**
 * 
 */
package it.univpm.esameGabrielliVirgili.controller;

import org.json.simple.JSONArray;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.esameGabrielliVirgili.exception.NoAutorizationException;
import it.univpm.esameGabrielliVirgili.stats.*;

/**
 * Classe che serve a gestire le richieste di filtraggio dati delle statistiche relative ad una competizione
 * 
 * @author lorenzo gabrielli e claudio virgili
 *
 */
@RestController
public class ControllerStats {
	
	/**
	 * Questo metodo consente di ricevere le statistiche relative ad una competizione riguardanti il numero di partite suddivise per esito primo tempo e finale
	 * senza nessun ulteriore filtraggio
	 * @param nameCompetition nome della competizione di cui si vogliono ricevere le statistiche
	 * @return JSONArray contenente il numero delle competizioni con:
	 * 1. esito finale uguale al parziale;
	 * 2. esito finale ribaltato rispetto al parziale;
	 * 3. esito parziale pareggio e esito finale non pareggio;
	 * 4. esito parziale non pareggio e esito finale pareggio. 
	 */
	
	@RequestMapping(value = "/stats/{nameCompetition}/stats_matches", method = RequestMethod.GET)
	public JSONArray getMatchesNoFilters(@PathVariable ("nameCompetition") String nameCompetition) throws NoAutorizationException {
		FilteredStatsImpl stats = new FilteredStatsImpl(nameCompetition);
		JSONArray ja = (JSONArray) stats.StatsSeasons(null);
		return ja;
	}
	
	/**
	 * Questo metodo consente di ricevere le statistiche relative ad una competizione riguardanti il numero di partite suddivise per esito primo tempo e finale
	 * con filtraggio per stagioni
	 * @param nameCompetition nome della competizione di cui si vogliono ricevere le statistiche
	 * @return JSONArray contenente il numero delle competizioni con:
	 * 1. esito finale uguale al parziale;
	 * 2. esito finale ribaltato rispetto al parziale;
	 * 3. esito parziale pareggio e esito finale non pareggio;
	 * 4. esito parziale non pareggio e esito finale pareggio. 
	 * @throws NoAutorizationException se vengono richieste delle stagioni prima della 2018-19
	 */
	@RequestMapping(value = "/stats/{nameCompetition}/stats_matches/seasons/{seasons}", method = RequestMethod.GET)
	public JSONArray getMatchesSeasons(@PathVariable ("nameCompetition") String nameCompetition, @PathVariable ("seasons") String seasons) throws NoAutorizationException {
		FilteredStatsImpl stats = new FilteredStatsImpl(nameCompetition);
		JSONArray ja = (JSONArray) stats.StatsSeasons(seasons);
		return ja;
	}
	
	/**
	 * Questo metodo consente di ricevere le statistiche relative ad una competizione riguardanti il numero di partite suddivise per esito primo tempo e finale
	 * con filtraggio per una lista di squadre
	 * @param nameCompetition
	 * @param teams
	 * @return JSONArray contenente il numero delle competizioni con:
	 * 1. esito finale uguale al parziale;
	 * 2. esito finale ribaltato rispetto al parziale;
	 * 3. esito parziale pareggio e esito finale non pareggio;
	 * 4. esito parziale non pareggio e esito finale pareggio. 
	 */
	@RequestMapping(value = "/stats/{nameCompetition}/stats_matches/teams/{teams}", method = RequestMethod.GET)
	public JSONArray getMatchesTeams(@PathVariable ("nameCompetition") String nameCompetition, @PathVariable ("teams") String teams) {
		FilteredStatsImpl stats = new FilteredStatsImpl(nameCompetition);
		JSONArray ja = (JSONArray) stats.StatsTeams(teams);
		return ja;
	}
	
	/**
	 * Questo metodo consente di ricevere le statistiche relative ad una competizione riguardanti il numero di partite suddivise per esito primo tempo e finale
	 * con filtraggio per una lista di squadre e di stagioni
	 * @param nameCompetition
	 * @param seasons elenco di stagioni separate da ", "
	 * @param teams elenco di squadre separate da ", "
	 * @return JSONArray contenente il numero delle competizioni con:
	 * 1. esito finale uguale al parziale;
	 * 2. esito finale ribaltato rispetto al parziale;
	 * 3. esito parziale pareggio e esito finale non pareggio;
	 * 4. esito parziale non pareggio e esito finale pareggio. 
	 * @throws NoAutorizationException se vengono richieste delle stagioni prima della 2018-19
	 */
	
	@RequestMapping(value = "/stats/{nameCompetition}/stats_matches/sessons/{seasons}/teams/{teams}", method = RequestMethod.GET)
	public JSONArray getMatchesSeasonsTeams(@PathVariable ("nameCompetition") String nameCompetition, @PathVariable ("seasons") String seasons, @PathVariable("teams") String teams) throws NoAutorizationException {
		FilteredStatsImpl stats = new FilteredStatsImpl(nameCompetition);
		JSONArray ja = (JSONArray) stats.StatsSeasonsTeams(seasons, teams);
		return ja;
	}
	
	/**
	 * Questo metodo consente di ricevere le statistiche relative ad una competizione riguardanti il numero di partite suddivise per esito primo tempo e finale
	 * con filtraggio per data
	 * @param nameCompetition
	 * @param startDate 
	 * @param endDate
	 * @return JSONArray contenente il numero delle competizioni con:
	 * 1. esito finale uguale al parziale;
	 * 2. esito finale ribaltato rispetto al parziale;
	 * 3. esito parziale pareggio e esito finale non pareggio;
	 * 4. esito parziale non pareggio e esito finale pareggio.
	 */

	@RequestMapping(value = "/stats/{nameCompetitions}/stats_matches/date", method = RequestMethod.GET)
	public JSONArray getMatchesDate(@PathVariable ("nameCompetition") String nameCompetition, @RequestParam ("start_date") String startDate, @RequestParam ("end_date") String endDate) {
		FilteredStatsImpl stats = new FilteredStatsImpl(nameCompetition);
		JSONArray ja = (JSONArray) stats.StatsDate(startDate, endDate);
		return ja;
	}
	
	/**
	 * Questo metodo consente di ricevere le statistiche relative ad una competizione riguardanti il numero di partite suddivise per esito primo tempo e finale
	 * con filtraggio per una lista di squadre e una data
	 * @param nameCompetition
	 * @param seasons elenco di stagioni separate da ", "
	 * @param teams elenco di squadre separate da ", "
	 * @return JSONArray contenente il numero delle competizioni con:
	 * 1. esito finale uguale al parziale;
	 * 2. esito finale ribaltato rispetto al parziale;
	 * 3. esito parziale pareggio e esito finale non pareggio;
	 * 4. esito parziale non pareggio e esito finale pareggio. 
	 */
	
	@RequestMapping(value = "/stats/{nameCompetitions}/stats_matches/date/teams/{teams}", method = RequestMethod.GET)
	public JSONArray getMatchesDateTeams(@PathVariable ("nameCompetition") String nameCompetition, @PathVariable("teams") String teams, @RequestParam ("start_date") String startDate, @RequestParam ("end_date") String endDate) {
		FilteredStatsImpl stats = new FilteredStatsImpl(nameCompetition);
		JSONArray ja = (JSONArray) stats.StatsDateTeams(startDate, endDate, teams);
		return ja;
	}
	
	/**
	 * Questo metodo consente di ricevere le statistiche relative ad una competizione riguardanti il numero totale di arbitri
	 * senza nessun filtraggio
	 * @param nameCompetition
	 * @return JSONArray contenente il numero di arbitri
	 * @throws NoAutorizationException 
	 */
	
	@RequestMapping(value = "/stats/{nameCompetitions}/stats_referees", method = RequestMethod.GET)
	public JSONArray getRefereesNoFilter(@PathVariable ("nameCompetition") String nameCompetition) throws NoAutorizationException {
		RefereesFilteredStats stats = new RefereesFilteredStats(nameCompetition);
		JSONArray ja = (JSONArray) stats.StatsSeasons(null);
		return ja;
	}

	/**
	 * Questo metodo consente di ricevere le statistiche relative ad una competizione riguardanti il numero totale di arbitri
	 * con filtraggio per una lista di stagioni
	 * @param nameCompetition
	 * @param seasons
	 * @return JSONArray contenente il numero di arbitri
	 * @throws NoAutorizationException le stagioni prima del 2018-19 non sono permesse
	 */
	@RequestMapping(value = "/stats/{nameCompetitions}/stats_referees/seasons/{seasons}", method = RequestMethod.GET)
	public JSONArray getRefereesSeasons(@PathVariable ("nameCompetition") String nameCompetition, @PathVariable ("seasons") String seasons) throws NoAutorizationException {
		RefereesFilteredStats stats = new RefereesFilteredStats(nameCompetition);
		JSONArray ja = (JSONArray) stats.StatsSeasons(seasons);
		return ja;
	}
	/**
	 * Questo metodo consente di ricevere le statistiche relative ad una competizione riguardanti il numero totale di arbitri
	 * con filtraggio per una lista di squadre
	 * @param nameCompetition
	 * @param teams lista delle squadre
	 * @return JSONArray contenente il numero di arbitri
	 */
	
	@RequestMapping(value = "/stats/{nameCompetitions}/stats_referees/teams/{teams}", method = RequestMethod.GET)
	public JSONArray getRefereesTeams(@PathVariable ("nameCompetition") String nameCompetition, @PathVariable ("teams") String teams) throws NoAutorizationException {
		RefereesFilteredStats stats = new RefereesFilteredStats(nameCompetition);
		JSONArray ja = (JSONArray) stats.StatsTeams(teams);
		return ja;
	}
	
	/**
	 * Questo metodo consente di ricevere le statistiche relative ad una competizione riguardanti il numero totale di arbitri
	 * con filtraggio per una lista di squadre e di stagioni
	 * @param nameCompetition
	 * @param seasons lista di stagioni separate da ", "
	 * @param teams lista di squadre separate da ", "
	 * @return JSONArray contenente il numero di arbitri
	 * @throws NoAutorizationException stagioni prima del 2018-19 non ammesse
	 */
	@RequestMapping(value = "/stats/{nameCompetitions}/stats_referees/seasons/{seasons}/teams/{teams}", method = RequestMethod.GET)
	public JSONArray getRefereesSeasonsTeams(@PathVariable ("nameCompetition") String nameCompetition, @PathVariable ("seasons") String seasons, @PathVariable ("teams") String teams) throws NoAutorizationException {
		RefereesFilteredStats stats = new RefereesFilteredStats(nameCompetition);
		JSONArray ja = (JSONArray) stats.StatsSeasonsTeams(seasons, teams);
		return ja;
	}
	
	/**
	 * Questo metodo consente di ricevere le statistiche relative ad una competizione riguardanti il numero totale di arbitri
	 * con filtraggio per data
	 * @param nameCompetition
	 * @param startDate
	 * @param endDate
	 * @return JSONArray contenente il numero di arbitri
	 */
	
	@RequestMapping(value = "/stats/{nameCompetitions}/stats_referees/date", method = RequestMethod.GET)
	public JSONArray getRefereesDate(@PathVariable ("nameCompetition") String nameCompetition, @RequestParam ("start_date") String startDate, @RequestParam ("end_date") String endDate) {
		RefereesFilteredStats stats = new RefereesFilteredStats(nameCompetition);
		JSONArray ja = (JSONArray) stats.StatsDate(startDate, endDate);
		return ja;
	}
	
	/**
	 * Questo metodo consente di ricevere le statistiche relative ad una competizione riguardanti il numero totale di arbitri
	 * con filtraggio per data e per una lista di squadre
	 * @param nameCompetition
	 * @param teams
	 * @param startDate
	 * @param endDate
	 * @return JSONArray contenente il numero di arbitri
	 */
	@RequestMapping(value = "/stats/{nameCompetitions}/stats_referees/date/teams/{teams}", method = RequestMethod.GET)
	public JSONArray getRefereesDateTeams(@PathVariable ("nameCompetition") String nameCompetition, @PathVariable ("teams") String teams ,@RequestParam ("start_date") String startDate, @RequestParam ("end_date") String endDate) {
		FilteredStatsImpl stats = new FilteredStatsImpl(nameCompetition);
		JSONArray ja = (JSONArray) stats.StatsDateTeams(startDate, endDate, teams);
		return ja;
	}
}