package it.univpm.esameGabrielliVirgili.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.esameGabrielliVirgili.model.Competition;
import it.univpm.esameGabrielliVirgili.util.CompetitionReader;

class ReaderOfflineTest {
	
	private String nameNull;
	private String nameCompetitions;
	private Competition comp;
	private Vector<Competition> competitions;

	@BeforeEach
	void setUp() throws Exception{
		nameNull = "Serie B";
		nameCompetitions = "Serie A, Ligue 1";
		competitions = new Vector<Competition>();
	}
	
	@AfterEach
	void tearDown() throws Exception{
		
	}
	
	@Test
	@DisplayName ("Verifica se la competizione è nulla")
	void testGetCompetition() {
		comp = CompetitionReader.getCompetition(nameNull);
		assertNull(comp);
	}
	
	@Test
	@DisplayName ("Verifica se l'elenco delle competizioni è non nullo")
	void testGetCompetitions() {
		competitions = CompetitionReader.getCompetitions(nameCompetitions);
		assertNotNull(competitions);
	}

}
