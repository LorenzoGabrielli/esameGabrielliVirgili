/**
 * 
 */
package it.univpm.esameGabrielliVirgili.model;

import java.util.Date;
/**
 * @author lorenzo gabrielli e claudio virgili
 *
 */
public class Match {
	public String winner;
	public int htHome;
	public int htAway;
	public Date date;
	public Season season;
	public Team homeTeam;
	public Team awayTeam;
	public int numberRefrees;
	public Match() {
		super();
		this.winner = null;
		this.htHome = 0;
		this.htAway = 0;
		this.date = null;
		this.season = null;
		this.homeTeam = null;
		this.awayTeam = null;
		this.numberRefrees = 0;
	}
	public Match(String winner, int htHome, int htAway, Date date, Season season, Team homeTeam, Team awayTeam, int numberRefrees) {
		super();
		this.winner = winner;
		this.htHome = htHome;
		this.htAway = htAway;
		this.date = date;
		this.season = season;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.numberRefrees = numberRefrees;
		
	}
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public int getHtHome() {
		return htHome;
	}
	public void setHtHome(int htHome) {
		this.htHome = htHome;
	}
	public int getHtAway() {
		return htAway;
	}
	public void setHtAway(int htAway) {
		this.htAway = htAway;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Season getSeason() {
		return season;
	}
	public void setSeason(Season season) {
		this.season = season;
	}
	public int getNumberRefrees() {
		return numberRefrees;
	}
	public void setNumberRefrees(int numberRefrees) {
		this.numberRefrees = numberRefrees;
	}
	@Override
	public String toString() {
		return "Match [winner=" + winner + ", htHome=" + htHome + ", htAway=" + htAway + ", date=" + date + ", season="
				+ season + "]";
	}
	
}