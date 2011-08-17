package examples.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * ConsoleMappingTest.java
 * Date: 8/16/11
 * Time: 8:29 PM
 */
public class ConsoleMappingTest {
	private static final Log log = LogFactory.getLog(ConsoleMappingTest.class);

	private static EntityManagerFactory emf;
	private static EntityManager em;

	@BeforeClass
	public static void setUp() {
		emf = Persistence.createEntityManagerFactory("games");
		em = emf.createEntityManager();
	}

	@Test
	public void testGetConsole() {
		Console console = em.find(Console.class, 1);
		assertNotNull(console);
		log.debug(console);
		assertNotNull(console.getCompany());
		log.debug(console.getCompany());
	}

	@Test
	public void testGetConsoleList() {
		List<Console> consoles = em.createQuery("SELECT c FROM Console AS c", Console.class).getResultList();
		assertNotNull(consoles);
		assertFalse(consoles.isEmpty());
		log.debug(consoles);
	}

	@AfterClass
	public static void tearDown() {
		em.close();
		emf.close();
	}
}
