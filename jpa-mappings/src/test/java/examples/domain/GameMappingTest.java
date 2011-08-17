package examples.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * GameMappingTest.java
 * Date: 8/16/11
 * Time: 8:28 PM
 */
public class GameMappingTest {
	private static final Log log = LogFactory.getLog(GameMappingTest.class);

	private static EntityManagerFactory emf;
	private static EntityManager em;

	@BeforeClass
	public static void setUp() {
		emf = Persistence.createEntityManagerFactory("games");
		em = emf.createEntityManager();
	}

	@Test
	public void testGetGame() {
		Game game = em.find(Game.class, "BLUS30109");
		assertNotNull(game);
		log.debug(game);
		assertNotNull(game.getDeveloper());
		log.debug(game.getDeveloper());
	}

	@Test
	public void testGameList() {
		List<Game> games = em.createQuery("SELECT g FROM Game AS g", Game.class).getResultList();
		assertNotNull(games);
		assertFalse(games.isEmpty());
	}

	@AfterClass
	public static void tearDown() {
		em.close();
		emf.close();
	}

}
