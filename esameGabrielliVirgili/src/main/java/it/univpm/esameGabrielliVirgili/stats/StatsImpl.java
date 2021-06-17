/**
 * 
 */
package it.univpm.esameGabrielliVirgili.stats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.esameGabrielliVirgili.model.Competition;
import it.univpm.esameGabrielliVirgili.model.Match;
import it.univpm.esameGabrielliVirgili.model.Team;
import it.univpm.esameGabrielliVirgili.util.*;
import it.univpm.esameGabrielliVirgili.exception.DateArgumentException;

/**
 * Classe che implementa l'interfaccia Stats
 * 
 * @author lorenzo gabrielli e claudio virgili
 *
 */
public class StatsImpl implements Stats {
	
	private String nameCompetition;
	private JSONObject jo = new JSONObject();
	private ChiamataAPI call;
	
	/**
	 * costruttore default
	 */
	
	public StatsImpl() {
		this.nameCompetition=null;
	}
	
	/**
	 * costruttore
	 * @param nameCompetition
	 */
	public StatsImpl(String nameCompetition) {
		this.nameCompetition=nameCompetition;
	}

	@Override
	public int minTeams(Vector<Competition> elenco) {
		// TODO Auto-generated method stub
		//vengono dati in ingresso i nomi di più competizioni separati da ", "
		elenco = CompetitionReader.getCompetitions(nameCompetition);
		int min = 100;
		for(int i=0; i<elenco.size(); i++) {
		int value;
		String name = elenco.get(i).getName();
		call = new ChiamataAPI(name);
		jo=call.getCompetitionTeams();
		value = Integer.parseInt(jo.get("count").toString());
		if(value < min) min = value;
		}
		return min;
	}

	@Override
	public int maxTeams(Vector<Competition> elenco) {
		// TODO Auto-generated method stub
		//vengono dati in ingresso i nomi di più competizioni separati da ", "
		elenco = CompetitionReader.getCompetitions(nameCompetition);
		int max = 0;
		for(int i=0; i<elenco.size(); i++) {
		int value;
		String name = elenco.get(i).getName();
		call = new ChiamataAPI(name);
		jo=call.getCompetitionTeams();
		value = Integer.parseInt(jo.get("count").toString());
		if(value > max) max = value;
		}
		return max;
	}

	@Override
	public float avTeams(Vector <Competition> elenco) {
		// TODO Auto-generated method stub
		//vengono dati in ingresso i nomi di più competizioni separati da ", "
		elenco = CompetitionReader.getCompetitions(nameCompetition);
		float somma = 0;
		int i;
		for(i=1; i<=elenco.size(); i++) {
			int value;
			String name = elenco.get(i).getName();
			call = new ChiamataAPI(name);
			jo=call.getCompetitionTeams();
			value = Integer.parseInt(jo.get("count").toString());
			somma += value;
			}
		return somma/i;
	}

	@Override
	public int[] avDuration(Vector<Competition> elenco) {
		// TODO Auto-generated method stub
		elenco = CompetitionReader.getCompetitions(nameCompetition);
		int[] duration = new int[2]; //duration[0] = mesi, duration[1] = giorni
		int i; //contatore, messo all'esterno perché servirà per fare la media
		Date startDate;
		Date endDate;
		int somma = 0;//somma, messa all'esterno perché servirà per fare la media
		for(i=1; i<=elenco.size(); i++) {
			int days=0;
			String name = elenco.get(i).getName();
			call = new ChiamataAPI(name);
			jo=call.getDataCompetition();
			JSONObject currentSeason = (JSONObject) jo.get("currentSeason");
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentSeason.get("startDate").toString());
				endDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentSeason.get("endDate").toString());
				days = (int) (endDate.getTime() - startDate.getTime())/(1000*3600*24);
			} catch (ParseException e) {
				throw new DateArgumentException("Inserire data nel formato yyyy-MM-dd");
			} if(startDate.compareTo(endDate)>0) {
				throw new DateArgumentException("La data iniziale non può essere successiva alla data finale");
			}
			somma+=days;
			}
		int avDays = somma/i;
		//si considerano i mesi a 30 giorni
		duration[0]=avDays/30;//mesi = giorni totali / 30
		duration[1]=avDays%30;//giorni = resto della divisione intera tra giorni totali e 30
		return duration;
	}

	@Override
	public float avAvailableSeason(Vector<Competition> elenco) {
		// TODO Auto-generated method stub
		//Vector<Competition> elenco = CompetitionTeamReader.caricaComp();
		float somma = 0;
		int i;
		for(i=1; i<=elenco.size(); i++) {
			int value;
			String name = elenco.get(i).getName();
			call = new ChiamataAPI(name);
			jo=call.getDataCompetition();
			JSONArray seasons = (JSONArray) jo.get("season");
			value = seasons.size();
			somma += value;
			}
		return somma/i;
	}

	@Override
	public int firstEqualsFinal(String yearSeason, String startDate, String endDate, String nameTeam) {
		// TODO Auto-generated method stub
		/*if(startDate != null && endDate != null) {
			Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		}*/
		call = new ChiamataAPI(nameCompetition);
		jo=call.getDataMatches(yearSeason);
		JSONArray matches = (JSONArray) jo.get("matches");
		int cont = 0;
		for(int i=0; i<matches.size(); i++) {
			Match match = new Match();
			JSONObject obj = (JSONObject) matches.get(i);
			JSONObject utcDate = (JSONObject) obj.get("utcDate");
			try {
				match.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(utcDate.toString().substring(0, 9)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject score = (JSONObject) obj.get("score");
			match.setWinner(score.get("winner").toString());
			JSONObject ht = (JSONObject) score.get("halftime");
			match.setHtHome(Integer.parseInt(ht.get("homeTeam").toString()));
			match.setHtAway(Integer.parseInt(ht.get("awayTeam").toString()));
			JSONObject homeTeam = (JSONObject) obj.get("homeTeam");
			Team hTeam = new Team();
			hTeam.setName(homeTeam.get("name").toString());
			match.setHomeTeam(hTeam);
			JSONObject awayTeam = (JSONObject) obj.get("awayTeam");
			Team aTeam = new Team();
			aTeam.setName(awayTeam.get("name").toString());
			//nessun filtro e filtro solo per stagione
			if(startDate == null && endDate == null && nameTeam == null) {
			if(match.getWinner().equals("HOME_TEAM")&&(match.getHtHome()>match.getHtAway())||match.getWinner().equals("AWAY_TEAM")&&(match.getHtHome()<match.htAway)||match.getWinner().equals("DRAW")&&(match.getHtHome()==match.getHtAway())) cont++;
			}
			//filtro solo per data
			if(startDate != null && endDate != null && nameTeam == null) {
				Date start = null;
				Date end = null;
				try {
					start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
					end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if((match.getWinner().equals("HOME_TEAM")&&(match.getHtHome()>match.getHtAway())||match.getWinner().equals("AWAY_TEAM")&&(match.getHtHome()<match.htAway)||match.getWinner().equals("DRAW")&&(match.getHtHome()==match.getHtAway())) && match.getDate().compareTo(start)>0 && match.getDate().compareTo(end)<0) cont++;
				}
			//filtro solo per squadra
			if(startDate == null && endDate == null && nameTeam != null) {
				if((match.getHomeTeam().getName().equals(nameTeam)||match.getAwayTeam().getName().equals(nameTeam))&&(match.getWinner().equals("HOME_TEAM")&&(match.getHtHome()>match.getHtAway())||match.getWinner().equals("AWAY_TEAM")&&(match.getHtHome()<match.htAway)||match.getWinner().equals("DRAW")&&(match.getHtHome()==match.getHtAway()))) cont++;
			}
			//filtro per data e squadra
			if(startDate!=null && endDate!= null && nameTeam != null) {
				Date start = null;
				Date end = null;
				try {
					start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
					end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if((match.getHomeTeam().getName().equals(nameTeam)||match.getAwayTeam().getName().equals(nameTeam))&&(match.getWinner().equals("HOME_TEAM")&&(match.getHtHome()>match.getHtAway())||match.getWinner().equals("AWAY_TEAM")&&(match.getHtHome()<match.htAway)||match.getWinner().equals("DRAW")&&(match.getHtHome()==match.getHtAway())) && (match.getDate().compareTo(start)>0 && match.getDate().compareTo(end)<0)) cont++;
			}
		}	
		return cont;
	}

	@Override
	public int firstWinFinalLose(String yearSeason, String startDate, String endDate, String nameTeam) {
		// TODO Auto-generated method stub
		call = new ChiamataAPI(nameCompetition);
		jo=call.getDataMatches(yearSeason);
		JSONArray matches = (JSONArray) jo.get("matches");
		int cont = 0;
		for(int i=0; i<matches.size(); i++) {
			Match match = new Match();
			JSONObject obj = (JSONObject) matches.get(i);
			JSONObject utcDate = (JSONObject) obj.get("utcDate");
			try {
				match.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(utcDate.toString().substring(0, 9)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject score = (JSONObject) obj.get("score");
			match.setWinner(score.get("winner").toString());
			JSONObject ht = (JSONObject) score.get("halftime");
			match.setHtHome(Integer.parseInt(ht.get("homeTeam").toString()));
			match.setHtAway(Integer.parseInt(ht.get("awayTeam").toString()));
			JSONObject homeTeam = (JSONObject) obj.get("homeTeam");
			Team hTeam = new Team();
			hTeam.setName(homeTeam.get("name").toString());
			match.setHomeTeam(hTeam);
			JSONObject awayTeam = (JSONObject) obj.get("awayTeam");
			Team aTeam = new Team();
			aTeam.setName(awayTeam.get("name").toString());
			//nessun filtro e filtro solo per stagione
			if(startDate == null && endDate == null && nameTeam == null) {
				if(match.getWinner().equals("HOME_TEAM")&&(match.getHtHome()<match.getHtAway())||match.getWinner().equals("AWAY_TEAM")&&(match.getHtHome()>match.getHtAway())) cont++;
			}
			//filtro solo per data
			if(startDate != null && endDate != null && nameTeam == null) {
				Date start = null;
				Date end = null;
				try {
					start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
					end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(match.getWinner().equals("HOME_TEAM")&&(match.getHtHome()<match.getHtAway())||match.getWinner().equals("AWAY_TEAM")&&(match.getHtHome()>match.getHtAway()) && match.getDate().compareTo(start)>0 && match.getDate().compareTo(end)<0) cont++;
				}
			//filtro solo per squadra
			if(startDate == null && endDate == null && nameTeam != null) {
				if((match.getHomeTeam().getName().equals(nameTeam)||match.getAwayTeam().getName().equals(nameTeam)) && (match.getWinner().equals("HOME_TEAM")&&(match.getHtHome()<match.getHtAway())||match.getWinner().equals("AWAY_TEAM")&&(match.getHtHome()>match.getHtAway()))) cont++;
			}
			//filtro per data e squadra
			if(startDate!=null && endDate!= null && nameTeam != null) {
				Date start = null;
				Date end = null;
				try {
					start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
					end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if((match.getHomeTeam().getName().equals(nameTeam)||match.getAwayTeam().getName().equals(nameTeam))&&(match.getWinner().equals("HOME_TEAM")&&(match.getHtHome()<match.getHtAway())||match.getWinner().equals("AWAY_TEAM")&&(match.getHtHome()>match.htAway)) && (match.getDate().compareTo(start)>0 && match.getDate().compareTo(end)<0)) cont++;
			}
		}	
		return cont;
	}

	@Override
	public int firstDrawFinalWin(String yearSeason, String startDate, String endDate, String nameTeam) {
		// TODO Auto-generated method stub
		call = new ChiamataAPI(nameCompetition);
		jo=call.getDataMatches(yearSeason);
		JSONArray matches = (JSONArray) jo.get("matches");
		int cont = 0;
		for(int i=0; i<matches.size(); i++) {
			Match match = new Match();
			JSONObject obj = (JSONObject) matches.get(i);
			JSONObject utcDate = (JSONObject) obj.get("utcDate");
			try {
				match.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(utcDate.toString().substring(0, 9)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject score = (JSONObject) obj.get("score");
			match.setWinner(score.get("winner").toString());
			JSONObject ht = (JSONObject) score.get("halftime");
			match.setHtHome(Integer.parseInt(ht.get("homeTeam").toString()));
			match.setHtAway(Integer.parseInt(ht.get("awayTeam").toString()));
			JSONObject homeTeam = (JSONObject) obj.get("homeTeam");
			Team hTeam = new Team();
			hTeam.setName(homeTeam.get("name").toString());
			match.setHomeTeam(hTeam);
			JSONObject awayTeam = (JSONObject) obj.get("awayTeam");
			Team aTeam = new Team();
			aTeam.setName(awayTeam.get("name").toString());
			//nessun filtro o solo stagione
			if(startDate == null && endDate == null && nameTeam == null) {
			if(match.getWinner().equals("HOME_TEAM")&&(match.getHtHome()==match.getHtAway())||match.getWinner().equals("AWAY_TEAM")&&(match.getHtHome()==match.getHtAway())) cont++;
			}
			//filtro solo per data
			if(startDate != null && endDate != null && nameTeam == null) {
				Date start = null;
				Date end = null;
				try {
					start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
					end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if((match.getWinner().equals("HOME_TEAM")&&(match.getHtHome()==match.getHtAway())||match.getWinner().equals("AWAY_TEAM")&&(match.getHtHome()==match.getHtAway()) && (match.getDate().compareTo(start)>0 && match.getDate().compareTo(end)<0))) cont++;
			}
			//filtro solo per squadra
			if(startDate == null && endDate == null && nameTeam != null) {
				if((match.getHomeTeam().getName().equals(nameTeam)||match.getAwayTeam().getName().equals(nameTeam)) && (match.getWinner().equals("HOME_TEAM")&&(match.getHtHome()==match.getHtAway())||match.getWinner().equals("AWAY_TEAM")&&(match.getHtHome()==match.getHtAway()))) cont++;
				}
			//filtro per data e squadra
			if(startDate!=null && endDate!= null && nameTeam != null) {
				Date start = null;
				Date end = null;
				try {
					start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
					end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if((match.getHomeTeam().getName().equals(nameTeam)||match.getAwayTeam().getName().equals(nameTeam)) && (match.getWinner().equals("HOME_TEAM")&&(match.getHtHome()==match.getHtAway())||match.getWinner().equals("AWAY_TEAM") && (match.getHtHome()==match.htAway)) && (match.getDate().compareTo(start)>0 && match.getDate().compareTo(end)<0)) cont++;
			}
			
		}
		return cont;
}

	@Override
	public int firstWinFinalDraw(String yearSeason, String startDate, String endDate, String nameTeam) {
		// TODO Auto-generated method stub
		call = new ChiamataAPI(nameCompetition);
		jo=call.getDataMatches(yearSeason);
		JSONArray matches = (JSONArray) jo.get("matches");
		int cont = 0;
		for(int i=0; i<matches.size(); i++) {
			Match match = new Match();
			JSONObject obj = (JSONObject) matches.get(i);
			JSONObject utcDate = (JSONObject) obj.get("utcDate");
			try {
				match.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(utcDate.toString().substring(0, 9)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject score = (JSONObject) obj.get("score");
			match.setWinner(score.get("winner").toString());
			JSONObject ht = (JSONObject) score.get("halftime");
			match.setHtHome(Integer.parseInt(ht.get("homeTeam").toString()));
			match.setHtAway(Integer.parseInt(ht.get("awayTeam").toString()));
			JSONObject homeTeam = (JSONObject) obj.get("homeTeam");
			Team hTeam = new Team();
			hTeam.setName(homeTeam.get("name").toString());
			match.setHomeTeam(hTeam);
			JSONObject awayTeam = (JSONObject) obj.get("awayTeam");
			Team aTeam = new Team();
			aTeam.setName(awayTeam.get("name").toString());
			//nessun filtro o solo stagione
			if(startDate == null && endDate == null && nameTeam == null) {
			if(match.getWinner().equals("DRAW")&&(match.getHtHome()!=match.getHtAway())) cont++;
			}
			//filtro solo per data
			if(startDate != null && endDate != null && nameTeam == null) {
				Date start = null;
				Date end = null;
				try {
					start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
					end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(match.getWinner().equals("DRAW")&&(match.getHtHome()!=match.getHtAway()) && (match.getDate().compareTo(start)>0) && (match.getDate().compareTo(end)<0)) cont++;
			}
			//filtro solo per squadra
			if(startDate == null && endDate == null && nameTeam != null) {
				if((match.getWinner().equals("DRAW")&&(match.getHtHome()!=match.getHtAway())) && (match.getHomeTeam().getName().equals(nameTeam) || match.getAwayTeam().getName().equals(nameTeam))) cont++;				
			}
			//filtro per squadra e data
			if(startDate!=null && endDate!= null && nameTeam != null) {
				Date start = null;
				Date end = null;
				try {
					start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
					end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if((match.getHomeTeam().getName().equals(nameTeam)||match.getAwayTeam().getName().equals(nameTeam)) && (match.getWinner().equals("DRAW")&&(match.getHtHome()!=match.getHtAway())) && (match.getDate().compareTo(start)>0 && match.getDate().compareTo(end)<0)) cont++;
			}
		}
		return cont;
	}
	
	@Override
	public int numberReferees(String yearSeason, String startDate, String endDate, String nameTeam) {
		call = new ChiamataAPI(nameCompetition);
		jo=call.getDataMatches(yearSeason);
		JSONArray matches = (JSONArray) jo.get("matches");
		int cont = 0;
		for(int i=0; i<matches.size(); i++) {
			Match match = new Match();
			JSONObject obj = (JSONObject) matches.get(i);
			JSONObject utcDate = (JSONObject) obj.get("utcDate");
			try {
				match.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(utcDate.toString().substring(0, 9)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject score = (JSONObject) obj.get("score");
			match.setWinner(score.get("winner").toString());
			JSONObject ht = (JSONObject) score.get("halftime");
			match.setHtHome(Integer.parseInt(ht.get("homeTeam").toString()));
			match.setHtAway(Integer.parseInt(ht.get("awayTeam").toString()));
			JSONObject homeTeam = (JSONObject) obj.get("homeTeam");
			Team hTeam = new Team();
			hTeam.setName(homeTeam.get("name").toString());
			match.setHomeTeam(hTeam);
			JSONObject awayTeam = (JSONObject) obj.get("awayTeam");
			Team aTeam = new Team();
			aTeam.setName(awayTeam.get("name").toString());
			JSONArray ref = (JSONArray) obj.get("referees");
			match.setNumberRefrees(ref.size());
			//nessun filtro e filtro solo per stagione
			if(startDate == null && endDate == null && nameTeam == null) cont+=match.getNumberRefrees();
			//filtro solo per data
			if(startDate != null && endDate != null && nameTeam == null) {
				Date start = null;
				Date end = null;
				try {
					start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
					end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(match.getDate().compareTo(start)>0 && match.getDate().compareTo(end)<0) cont+=match.getNumberRefrees();
				}
			//filtro solo per squadra
			if(startDate == null && endDate == null && nameTeam != null) {
				if((match.getHomeTeam().getName().equals(nameTeam)||match.getAwayTeam().getName().equals(nameTeam))) cont+=match.getNumberRefrees();
			}
			//filtro per data e squadra
			if(startDate!=null && endDate!= null && nameTeam != null) {
				Date start = null;
				Date end = null;
				try {
					start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
					end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if((match.getHomeTeam().getName().equals(nameTeam)||match.getAwayTeam().getName().equals(nameTeam))&&(match.getDate().compareTo(start)>0 && match.getDate().compareTo(end)<0)) cont+=match.getNumberRefrees();
			}
		}	
		return cont;
	}
	
	/**
	 * Metodo che restituisce un JSONArray con numero min, max, med di squadre per competizioni
	 * @return JSONArray contenente numero min, max, med di squadre
	 */
	
	@SuppressWarnings("unchecked")
	public JSONArray GenStatsTeams(){
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
			Vector<Competition> elenco = CompetitionReader.getCompetitions(nameCompetition);
			for(int i=1; i<=elenco.size();i++) {
				jo.put(i + "° competizione", elenco.get(i).getName());
				ja.add(jo);
			}
			jo.put("Numero massimo", maxTeams(elenco));
			jo.put("Numero minimo", minTeams(elenco));
			jo.put("Numero medio", avTeams(elenco));
			ja.add(jo);

		return ja;
	}
	
	/**
	 * Metodo che restituisce un JSONArray con la durata media delle competizioni in gioni, mesi
	 * @return JSONArray
	 */
	@SuppressWarnings("unchecked")
	public JSONArray GenAvDuration(){
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
			Vector<Competition> elenco = CompetitionReader.getCompetitions(nameCompetition);
			for(int i=1; i<=elenco.size();i++) {
				jo.put(i + "° competizione", elenco.get(i).getName());
				ja.add(jo);
			}
			jo.put("Mesi", avDuration(elenco)[0]);
			jo.put("Giorni", avDuration(elenco)[1]);
			ja.add(jo);

		return ja;
	}
	
	/**
	 * Metodo che restituisce un JSONArray con il numero di stagioni salvate per competizione
	 * @return JSONArray
	 */
	@SuppressWarnings("unchecked")
	public JSONArray GenStatsSeasons(){
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
			Vector<Competition> elenco = CompetitionReader.getCompetitions(nameCompetition);
			for(int i=1; i<=elenco.size();i++) {
				jo.put(i + "° competizione", elenco.get(i).getName());
				ja.add(jo);
			}
			jo.put("Numero medio stagioni salvate", avAvailableSeason(elenco));
			ja.add(jo);

		return ja;
	}
	
}	