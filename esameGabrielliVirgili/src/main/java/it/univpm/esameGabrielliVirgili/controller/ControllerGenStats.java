/**
 * 
 */
package it.univpm.esameGabrielliVirgili.controller;

import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import it.univpm.esameGabrielliVirgili.stats.StatsImpl;

/**
 * Classe che serve a gestire le richieste di filtraggio dati delle statistiche generali delle competizioni
 * 
 * @author lorenzo gabrielli e claudio virgili
 *
 */
@RestController
public class ControllerGenStats {
	
	/**
	 * Metodo che restituisce il numero minimo, massimo e medio di squadre per una lista di competizioni
	 * @param competitions elenco di competizioni separate da ", "
	 * @return JSONArray contenente il numero min, max, med di squadre per competizione
	 */

	@RequestMapping(value = "/stats/gen/number_teams", method = RequestMethod.GET)
	public JSONArray getNumberTeams(@RequestParam String competitions){
		StatsImpl stats = new StatsImpl(competitions);
		JSONArray ja = (JSONArray) stats.GenStatsTeams();
		return ja;
	}
	
	/**
	 * Metodo che restituisce la durata media delle competizioni
	 * @param competitions elenco di competizioni separate da ", "
	 * @return JSONArray contenente la durata media delle competizioni
	 */
	@RequestMapping(value = "/stats/gen/duration", method = RequestMethod.GET)
	public JSONArray getDuration(@RequestParam String competitions) {
		StatsImpl stats = new StatsImpl(competitions);
		JSONArray ja = (JSONArray) stats.GenAvDuration();
		return ja;
	}
	
	/**
	 * Metodo che restituisce il numero medio di stagioni disponibili per ogni competizione
	 * @param competitions elenco di competizioni separate da ", "
	 * @return JSONArray contenente la media del numero di stagioni disponibili
	 */
	@RequestMapping(value = "/stats/gen/seasons", method = RequestMethod.GET)
	public JSONArray getSeasons(@RequestBody String competitions) {
		StatsImpl stats = new StatsImpl(competitions);
		JSONArray ja = (JSONArray) stats.GenStatsSeasons();
		return ja;
	}
}
