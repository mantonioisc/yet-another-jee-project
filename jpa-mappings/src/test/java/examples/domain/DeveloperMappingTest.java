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
 * DeveloperMappingTest.java
 * Date: 8/15/11
 * Time: 8:18 PM
 */
public class DeveloperMappingTest {
	private static final Log log = LogFactory.getLog(DeveloperMappingTest.class);

	private static EntityManagerFactory emf;
	private static EntityManager em;

	@BeforeClass
	public static void setUp() {
		emf = Persistence.createEntityManagerFactory("games");
		em = emf.createEntityManager();
	}

	@Test
	public void testGetDeveloper() {
		Developer developer = em.find(Developer.class, 1);
		assertNotNull(developer);
		log.debug(developer);
	}

	@Test
	public void testGetDevelopers() {
		List<Developer> developers = em.createQuery("SELECT d FROM Developer d", Developer.class).getResultList();
		assertNotNull(developers);
		assertFalse(developers.isEmpty());
		log.debug(developers.size());
	}

	@AfterClass
	public static void tearDown() {
		em.close();
		emf.close();
	}
}
