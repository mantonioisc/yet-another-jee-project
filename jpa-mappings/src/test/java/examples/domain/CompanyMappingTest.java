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
 * CompanyMappingTest.java
 * Date: 8/15/11
 * Time: 7:55 PM
 */
public class CompanyMappingTest {
	private static final Log log = LogFactory.getLog(CompanyMappingTest.class);
	private static EntityManagerFactory emf;
	private static EntityManager em;

	@BeforeClass
	public static void setUp() {
		emf = Persistence.createEntityManagerFactory("games");

		em = emf.createEntityManager();
	}

	@Test
	public void testGetCompany() {
		Company company = em.find(Company.class, 1);
		assertNotNull(company);
		log.debug(company);
	}

	@Test
	public void testGetCompanies() {
		List<Company> companies =
				em.createQuery("SELECT c FROM Company AS c", Company.class).getResultList();

		assertNotNull(companies);
		assertFalse(companies.isEmpty());
		log.debug(companies.size());
	}

	@AfterClass
	public static void tearDown() {
		em.close();
		emf.close();
	}

}
