package examples.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class TagMappingTest {
	private static final Log LOG = LogFactory.getLog(TagMappingTest.class);

	private static EntityManagerFactory emf;
	private static EntityManager em;

	@BeforeClass
	public static void setUp() {
		emf = Persistence.createEntityManagerFactory("games");
		
		em = emf.createEntityManager();
	}

	@Test
	public void testGetTag() {
		Tag tag = em.find(Tag.class, 1);
		assertNotNull(tag);
		LOG.debug(tag);
	}

	@Test
	public void testGetTags() {
		TypedQuery<Tag> query = em.createQuery("SELECT t FROM Tag AS t", Tag.class);

		List<Tag> tags = query.getResultList();

		assertNotNull(tags);
		assertFalse(tags.isEmpty());
		LOG.debug(tags);
	}

	/**
	 * Will insert one record in DB that's why it's ignored
	 */
	@Test
	@Ignore
	public void testSaveTag(){
		em.getTransaction().begin();
		Tag tag = new Tag();
		tag.setName("created");
		em.persist(tag);
		LOG.debug(tag.getId());
		em.getTransaction().commit();
	}

	@AfterClass
	public static void tearDown(){
		em.close();
		emf.close();
	}
}
