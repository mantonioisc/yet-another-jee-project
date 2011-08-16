package examples.dao.impl;

import examples.dao.DeveloperDao;
import examples.domain.Developer;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Date: 8/15/11
 * Time: 9:01 PM
 */
@Local
public class DeveloperDaoBean implements DeveloperDao {
	@PersistenceContext(name = "games")
	EntityManager em;

	@Override
	public Developer getDeveloper(int id) {
		return em.find(Developer.class, id);
	}

	@Override
	public List<Developer> getDeveloperList() {
		return em.createQuery("SELECT d FROM Developer AS d", Developer.class).getResultList();
	}
}
