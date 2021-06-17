/**
 * 
 */
package it.univpm.esameGabrielliVirgili.stats;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import it.univpm.esameGabrielliVirgili.exception.NoAutorizationException;

/**
 * classe che implementa l'interfaccia FilteredStats per le statistiche per esito partite
 * 
 * @author lorenzo gabrielli e claudio virgili
 * 
 * 
 */
public class FilteredStatsImpl extends StatsImpl implements FilteredStats {
	
	int[] values;
	String nameCompetition;
	
	/**
	 * costruttore
	 * 
	 * @param nameCompetition
	 */
	public FilteredStatsImpl(String nameCompetition) {
		this.nameCompetition=nameCompetition;
	}
	
	@Override
	public int[] filterSeasons(String seasons) throws NoAutorizationException{
		// TODO Auto-generated method stub
		StatsImpl stats = new StatsImpl(nameCompetition);
		String s[] = seasons.split(", ");
		for(String a : s) {
			if((Integer.parseInt(a)<2018||Integer.parseInt(a)>2020) || a!=null) throw new NoAutorizationException("Si possono ricevere i dati solo per le stagioni comprese tra la 2018-19 e la 2020-21");
			values[0]+=stats.firstEqualsFinal(a, null, null, null);
			values[1]+=stats.firstWinFinalLose(a, null, null, null);
			values[2]+=stats.firstDrawFinalWin(a, null, null, null);
			values[3]+=stats.firstWinFinalDraw(a, null, null, null);
		
		}
		return values;
	}

	@Override
	public int[] filterTeams(String nameTeams) {
		// TODO Auto-generated method stub
		StatsImpl stats = new StatsImpl(nameCompetition);
		String t[] = nameTeams.split(", ");
		for(String s : t) {
			values[0]+=stats.firstEqualsFinal("2020", null, null, s);
			values[1]+=stats.firstWinFinalLose("2020", null, null, s);
			values[2]+=stats.firstDrawFinalWin("2020", null, null, s);
			values[3]+=stats.firstWinFinalDraw("2020", null, null, s);
		}
		return values;
	}

	@Override
	public int[] filterSeasonsTeams(String seasons, String nameTeams) throws NoAutorizationException{
		// TODO Auto-generated method stub
		StatsImpl stats = new StatsImpl(nameCompetition);
		String s[] = seasons.split(", ");
		String t[] = nameTeams.split(", ");
		for(String b : t) {
			for(String a : s) {
				if(Integer.parseInt(a)<2018||Integer.parseInt(a)>2020 || a !=null) throw new NoAutorizationException("Si possono ricevere i dati solo per le stagioni comprese tra la 2018-19 e la 2020-21");
				values[0]+=stats.firstEqualsFinal(a, null, null, b);
				values[1]+=stats.firstWinFinalLose(a, null, null, b);
				values[2]+=stats.firstDrawFinalWin(a, null, null, b);
				values[3]+=stats.firstWinFinalDraw(a, null, null, b);
			}
		}
		return values;
	}

	@Override
	public int[] filterDate(String startDate, String endDate) {
		// TODO Auto-generated method stub
		StatsImpl stats = new StatsImpl(nameCompetition);
		String season = startDate.substring(0, 3);
			values[0]+=stats.firstEqualsFinal(season, startDate, endDate, null);
			values[1]+=stats.firstWinFinalLose(season, startDate, endDate, null);
			values[2]+=stats.firstDrawFinalWin(season, startDate, endDate, null);
			values[3]+=stats.firstWinFinalDraw(season, startDate, endDate, null);
		
		return values;
	}

	@Override
	public int[] filterDateTeams(String startDate, String endDate, String nameTeams) {
		// TODO Auto-generated method stub
		StatsImpl stats = new StatsImpl(nameCompetition);
		String t[] = nameTeams.split(", ");
		String season = startDate.substring(0,3);
		for(String s : t) {
			values[0]+=stats.firstEqualsFinal(season, startDate, endDate, s);
			values[1]+=stats.firstWinFinalLose(season, startDate, endDate, s);
			values[2]+=stats.firstDrawFinalWin(season, startDate, endDate, s);
			values[3]+=stats.firstWinFinalDraw(season, startDate, endDate, s);
		}
		return values;
	}

	/**
	 * Metodo che restituisce un JSONArray con il numero di partite separate per esito filtrato per stagioni
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
			jo.put("Numero partite con esito primo tempo uguale al finale", values[0]);
			jo.put("Numero partite con esito finale ribaltato rispetto al primo tempo", values[1]);
			jo.put("Numero partite con pareggio al primo tempo e non pareggio alla fine", values[2]);
			jo.put("Numero partite con esito primo tempo non pari e pareggio alla fine", values[3]);
			ja.add(jo);

		return ja;
	}

	/**
	 * Metodo che restituisce un JSONArray con il numero di partite separate per esito filtrato per squadre
	 * @param nameTeams
	 * @return JSONArray
	 */
	
	@SuppressWarnings("unchecked")
	public JSONArray StatsTeams(String nameTeams){
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		jo.put("Competizione", nameCompetition);
		int[] values = filterTeams(nameTeams);
		jo.put("Numero partite con esito primo tempo uguale al finale", values[0]);
		jo.put("Numero partite con esito finale ribaltato rispetto al primo tempo", values[1]);
		jo.put("Numero partite con pareggio al primo tempo e non pareggio alla fine", values[2]);
		jo.put("Numero partite con esito primo tempo non pari e pareggio alla fine", values[3]);
		ja.add(jo);

		return ja;
	}

	/**
	 * Metodo che restituisce un JSONArray con il numero di partite separate per esito, filtrato per stagioni e squadre 
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
		jo.put("Numero partite con esito primo tempo uguale al finale", values[0]);
		jo.put("Numero partite con esito finale ribaltato rispetto al primo tempo", values[1]);
		jo.put("Numero partite con pareggio al primo tempo e non pareggio alla fine", values[2]);
		jo.put("Numero partite con esito primo tempo non pari e pareggio alla fine", values[3]);
		ja.add(jo);
		
		return ja;
	}

	/**
	 * Metodo che restituisce un JSONArray con il numero di partite separate per esito, filtrato per data
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
		jo.put("Numero partite con esito primo tempo uguale al finale", values[0]);
		jo.put("Numero partite con esito finale ribaltato rispetto al primo tempo", values[1]);
		jo.put("Numero partite con pareggio al primo tempo e non pareggio alla fine", values[2]);
		jo.put("Numero partite con esito primo tempo non pari e pareggio alla fine", values[3]);
		ja.add(jo);

		return ja;
	}

	/**
	 * Metodo che restituisce un JSONArray con il numero di partite separate per esito filtrato per data e squadre
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
		jo.put("Numero partite con esito primo tempo uguale al finale", values[0]);
		jo.put("Numero partite con esito finale ribaltato rispetto al primo tempo", values[1]);
		jo.put("Numero partite con pareggio al primo tempo e non pareggio alla fine", values[2]);
		jo.put("Numero partite con esito primo tempo non pari e pareggio alla fine", values[3]);
		ja.add(jo);

		return ja;
	}

	

}