/**
 * 
 */
package it.univpm.esameGabrielliVirgili.util;

//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import it.univpm.esameGabrielliVirgili.model.*;

/**
 * classe che legge il file .json locale contenente le competizioni di interesse e crea vettori di tipo competizione contenenti la/le competizione/i specificata/e 
 * 
 * @author lorenzo gabrielli e claudio virgili
 *
 */
public class CompetitionReader{
	
	/**
	 * percorso del file contenente l'elenco delle competizioni
	 */

	public static String percorsoCompetizioni = "src/main/resources/competition.list.json";
	
    /**
     * metodo che prende l'elenco di tutte le competizioni salvato in un file locale e le salva in un vettore
     * @return Vector<Competition> allCompetitions
     */
    public static Vector<Competition> allCompetitions() {
    	
    	JSONParser parser = new JSONParser();
    	Object obj;
    	Vector<Competition> competitions = new Vector<Competition>();
		try {
			obj = parser.parse(new FileReader(percorsoCompetizioni));
			JSONArray ja = (JSONArray) obj;
	    	for (int i=0; i<ja.size(); i++) {
	    		JSONObject comp = (JSONObject) ja.get(i);
	    		Competition c = new Competition();
	    		c.setId(Integer.parseInt(comp.get("id").toString()));
	    		c.setName(comp.get("name").toString());
	    		c.setNumberOfAvaiable(Integer.parseInt(comp.get("numberOfAvailableSeasons").toString()));
	    		JSONObject seas = (JSONObject) comp.get("currentSeason");
	    		Season s = new Season();
	    		s.setId(Integer.parseInt(seas.get("id").toString()));
	    		s.setStartDate(seas.get("startDate").toString());
	    		s.setEndDate(seas.get("endDate").toString());
	    		s.setCurrentMatchDay(Integer.parseInt(seas.get("currentMatchday").toString()));
	    		c.setCurrentSeason(s);
	    		competitions.add(c);
	    	}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return competitions;
    }
    
    /**
     * metodo che riceve in ingresso una stringa contenente i nomi delle competizioni di interesse separati da ", " e ritorna un vettore contenente le competizioni richieste
     * 
     * @param nameCompetitions: nomi delle competizioni separati da ", "
     * @return Vector<Competition> getCompetitions
     */
    
    public static Vector<Competition> getCompetitions(String nameCompetitions) {
    	//Competition c = new Competition();
    	Vector<Competition> cp = new Vector<Competition>();
    	String[] competitions = nameCompetitions.split(", ");
    	cp = allCompetitions();
    	Vector<Competition> returnComp = new Vector<Competition>();
    	for(String s : competitions) {
    	for(int i=0;i<cp.size();i++) {
    		if(cp.get(i).getName().equals(s)) returnComp.add(cp.get(i));
    	}
    	}
    	return returnComp;
    }
    /**
     * particolarizzazione del metodo getCompetitions nel caso in cui non gli venga dato in ingresso niente, ritorna il vettore contenente tutte le competizioni
     * @return Vector<Competition> allCompetitions
     */
    public static Vector<Competition> getCompetitions() {
    	//Competition c = new Competition();
    	Vector<Competition> cp = new Vector<Competition>();
    	//String[] competitions = nameCompetitions.split(", ");
    	cp = allCompetitions();
    	return cp;
    }
    
    /**
     * metodo che riceve in ingresso una stringa contenente il nome di una competizione e la restituisce sottoforma di oggetto "Competition" 
     * @param nameCompetition nome della competizione di interesse
     * @return Competition: competizione di interesse
     */
    public static Competition getCompetition(String nameCompetition) {
    	Competition c = new Competition();
    	Vector<Competition> cp = new Vector<Competition>();
    	cp = allCompetitions();
    	for(int i=0;i<cp.size();i++) {
    		if(cp.get(i).getName().equals(nameCompetition)) c=cp.get(i);
    	}
    	return c;
    }
}