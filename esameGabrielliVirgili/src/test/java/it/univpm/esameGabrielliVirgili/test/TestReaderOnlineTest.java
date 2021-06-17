package it.univpm.esameGabrielliVirgili.test;

import static org.junit.Assert.assertEquals;
import it.univpm.esameGabrielliVirgili.stats.*;
import it.univpm.esameGabrielliVirgili.model.Competition;
import it.univpm.esameGabrielliVirgili.util.CompetitionReader;

import java.util.Vector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestReaderOnlineTest {
	
	String competition;
	Vector<Competition> c = new Vector<Competition>();
	int min;
	int max;
	float average;

	@BeforeEach
	void setUp() throws Exception {
		c = CompetitionReader.allCompetitions();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Testa che le statistiche sulle partite funzionino")
	void test() {
		StatsImpl stats = new StatsImpl(competition);
		min = stats.minTeams(c);
		assertEquals(min, 18);
		max = stats.maxTeams(c);
		assertEquals(max, 20);
	}

}
