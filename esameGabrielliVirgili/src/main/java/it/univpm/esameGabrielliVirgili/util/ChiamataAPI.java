/**
 * 
 */
package it.univpm.esameGabrielliVirgili.util;

import it.univpm.esameGabrielliVirgili.model.Competition;
import it.univpm.esameGabrielliVirgili.util.CompetitionReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**Classe per gestire le varie chiamate API
 * 
 * @author lorenzo gabrielli e claudio virgili
 *
 */
public class ChiamataAPI {

	private static String token = "02973a519e534558bd3fbeb927509401";
	private String url;
	private String idCompetition = new String();
	
	/**
	 * costruttore
	 * @param nameCompetition nome della competizione
	 */
	
	public ChiamataAPI(String nameCompetition) {
		this.url="https://api.football-data.org/v2/";
		//JSONArray ja = CompetitionTeamReader.caricaArray("src/main/resources/competition.list.json");
		Competition c = CompetitionReader.getCompetition(nameCompetition);
		//this.idCompetition=jo.get("id").toString();
		this.idCompetition= String.valueOf(c.getId());
	}
	
	/**
	 * Metodo che restituisce un JSONObject contenente i dati relativi ad una competizione
	 * @return JSONObject
	 */
	public JSONObject getDataCompetition() {
		String api = new String();
		JSONObject jo = null;
		String line = "";
		String dataFilter = "";
		try{
			api = this.url + "competition/" + idCompetition;
			HttpURLConnection openConnection = (HttpURLConnection) new URL(api).openConnection();
			openConnection.setRequestProperty("X-Auth-Token", token);
			InputStream in = openConnection.getInputStream();

			InputStreamReader input = new InputStreamReader( in );
			BufferedReader buf = new BufferedReader( input );
			
			while ( ( line = buf.readLine() ) != null ) {
				dataFilter += line;
			}
			
			in.close();
			jo = (JSONObject) JSONValue.parseWithException(dataFilter);
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		catch (IOException e ) {
			e.printStackTrace();
		}
		return jo;
}
	/**
	 * metodo che restituisce un JSONObject con le informaioni inerenti alle squadre di una determinata competizione
	 * @return JSONObject
	 */
	public JSONObject getCompetitionTeams() {
		
		String api = this.url + "competition/" + idCompetition + "/teams";
		JSONObject jo = null;
		String line = "";
		String dataFilter = "";
		try{
			HttpURLConnection openConnection = (HttpURLConnection) new URL(api).openConnection();
			openConnection.setRequestProperty("X-Auth-Token", token);
			InputStream in = openConnection.getInputStream();

			InputStreamReader input = new InputStreamReader( in );
			BufferedReader buf = new BufferedReader( input );
			
			while ( ( line = buf.readLine() ) != null ) {
				dataFilter += line;
			}
			
			in.close();
			jo = (JSONObject) JSONValue.parseWithException(dataFilter);
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		catch (IOException e ) {
			e.printStackTrace();
		}
		return jo;
	}
	
	/**
	 * metodo che restituisce un JSONObject con le informazioni relative alle partite di una determinata competizione
	 * @param yearSeason anno d'inizio della stagione che si sta considerando
	 * @return JSONObject
	 */
	
	public JSONObject getDataMatches(String yearSeason) {
		
		String api = this.url + "competition/" + idCompetition + "/matches?season=" +yearSeason;
		JSONObject jo = null;
		String line = "";
		String dataFilter = "";
		try{
			HttpURLConnection openConnection = (HttpURLConnection) new URL(api).openConnection();
			openConnection.setRequestProperty("X-Auth-Token", token);
			InputStream in = openConnection.getInputStream();

			InputStreamReader input = new InputStreamReader( in );
			BufferedReader buf = new BufferedReader( input );
			
			while ( ( line = buf.readLine() ) != null ) {
				dataFilter += line;
			}
			
			in.close();
			jo = (JSONObject) JSONValue.parseWithException(dataFilter);
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		catch (IOException e ) {
			e.printStackTrace();
		}
		return jo;
	}
}
