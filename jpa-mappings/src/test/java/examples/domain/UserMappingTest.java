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

public class UserMappingTest {
	private static final Log LOG = LogFactory.getLog(TagMappingTest.class);

	private static EntityManagerFactory emf;
	private static EntityManager em;

	@BeforeClass
	public static void setUp() {
		emf = Persistence.createEntityManagerFactory("games");

		em = emf.createEntityManager();
	}

	@Test
	public void testGetUser() {
		User user = em.find(User.class, 1);
		assertNotNull(user);
		LOG.debug(user);
	}

	@Test
	public void testGetUserList() {
		List<User> users = em.createQuery("SELECT u FROM User AS u", User.class).getResultList();
		assertNotNull(users);
		assertFalse(users.isEmpty());
		LOG.debug(users);
	}

	@AfterClass
	public static void tearDown() {
		em.close();
		emf.close();
	}

}
