/**
 * 
 */
package it.univpm.esameGabrielliVirgili.stats;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.esameGabrielliVirgili.exception.NoAutorizationException;

/**classe che estende l'interfaccia filteredStats per le statistiche per arbitri
 * 
 * @author lorenzo gabrielli e claudio virgili
 *
 */
public class RefereesFilteredStats implements FilteredStats {

	String nameCompetition;
	int values[] = new int[1];//in questo caso si richiede un solo elemento in uscita dai vari metodi
	
	/**
	 * costruttore
	 * @param nameCompetition
	 */
	public RefereesFilteredStats(String nameCompetition) {
		this.nameCompetition=nameCompetition;
	}
	
	
	@Override
	public int[] filterSeasons(String seasons) throws NoAutorizationException {
		// TODO Auto-generated method stub
		StatsImpl stats = new StatsImpl(nameCompetition);
		String s[] = seasons.split(", ");
		for(String a : s) {
			if(Integer.parseInt(a)<2018||Integer.parseInt(a)>2020|| a!=null) throw new NoAutorizationException("Si possono ricevere i dati solo per le stagioni comprese tra la 2018-19 e la 2020-21");
			values[0]+= stats.numberReferees(seasons, null, null, null);		
		}
		return values;
	}

	@Override
	public int[] filterTeams(String nameTeams) {
		// TODO Auto-generated method stub
		StatsImpl stats = new StatsImpl(nameCompetition);
		String t[] = nameTeams.split(", ");
		for(String s : t) {
			values[0]+= stats.numberReferees("2020", null, null, s);		
		}
		return values;
	}

	@Override
	public int[] filterSeasonsTeams(String seasons, String nameTeams) throws NoAutorizationException {
		// TODO Auto-generated method stub
		StatsImpl stats = new StatsImpl(nameCompetition);
		String s[] = seasons.split(", ");
		String t[] = nameTeams.split(", ");
		for(String b : t) {
			for(String a : s) {
				if(Integer.parseInt(a)<2018||Integer.parseInt(a)>2020||a!=null) throw new NoAutorizationException("Si possono ricevere i dati solo per le stagioni comprese tra la 2018-19 e la 2020-21");
				values[0]+=stats.numberReferees(a, null, null, b);
			}
		}
		return values;
	}

	@Override
	public int[] filterDate(String startDate, String endDate) {
		// TODO Auto-generated method stub
		StatsImpl stats = new StatsImpl(nameCompetition);
		String season = startDate.substring(0, 3);
		values[0]+=stats.numberReferees(season, startDate, endDate, null);
		return null;
	}

	@Override
	public int[] filterDateTeams(String startDate, String endDate, String nameTeams) {
		// TODO Auto-generated method stub
		StatsImpl stats = new StatsImpl(nameCompetition);
		String season = startDate.substring(0, 3);
		String t[] = nameTeams.split(", ");
		for(String s : t) values[0]+=stats.numberReferees(season, startDate, endDate, s);
		return values;
	}
	
	/**
	 * Numero di arbitri filtrato per stagione
	 * @param yearsSeasons
	 * @return JSONArray
	 * @throws NoAutorizationException
	 */
	@SuppressWarnings("unchecked")
	public JSONArray StatsSeasons(String yearsSeasons) throws NoAutorizationException{
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
			jo.put("Competizione", nameCompetition);
			if(yearsSeasons == null) yearsSeasons = "2018, 2019, 2020";
			int[] values = filterSeasons(yearsSeasons);
			jo.put("Numero totale di arbitri", values[0]);
			ja.add(jo);

		return ja;
	}
	
	/**
	 * Numero di arbitri filtrato per squadre
	 * @param nameTeams
	 * @return JSONArray
	 */
	@SuppressWarnings("unchecked")
	public JSONArray StatsTeams(String nameTeams) {
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		jo.put("Competizione", nameCompetition);
		int[] values = filterTeams(nameTeams);
		jo.put("Numero totale di arbitri", values[0]);
		ja.add(jo);
		return ja;
	}
	
	/**
	 * Numero di arbitri filtrato per stagioni e squadre
	 * @param yearsSeasons
	 * @param nameTeams
	 * @return JSONArray
	 * @throws NoAutorizationException
	 */
	@SuppressWarnings("unchecked")
	public JSONArray StatsSeasonsTeams(String yearsSeasons, String nameTeams) throws NoAutorizationException{
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		jo.put("Competizione", nameCompetition);
		int[] values = filterSeasonsTeams(yearsSeasons, nameTeams);
		jo.put("Numero totale di arbitri", values[0]);
		ja.add(jo);
	return ja;
	}
	
	/**
	 * Numero di arbitri filtrato per data
	 * @param startDate
	 * @param endDate
	 * @return JSONArray
	 */
	
	@SuppressWarnings("unchecked")
	public JSONArray StatsDate(String startDate, String endDate){
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		jo.put("Competizione", nameCompetition);
		int[] values = filterDate(startDate, endDate);
		jo.put("Numero totale di arbitri", values[0]);
		ja.add(jo);
		return ja;
	}
	
	/**
	 * Numero di arbitri filtrato per data e squadre
	 * @param startDate
	 * @param endDate
	 * @param nameTeams
	 * @return JSONArray
	 */
	@SuppressWarnings("unchecked")
	public JSONArray StatsDateTeams(String startDate, String endDate, String nameTeams){
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		jo.put("Competizione", nameCompetition);
		int[] values = filterDateTeams(startDate, endDate, nameTeams);
		jo.put("Numero totale di arbitri", values[0]);
		ja.add(jo);

		return ja;
	}
}
