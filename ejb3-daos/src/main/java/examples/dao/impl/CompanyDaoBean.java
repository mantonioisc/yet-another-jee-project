package examples.dao.impl;

import examples.dao.CompanyDao;
import examples.domain.Company;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Date: 8/15/11
 * Time: 9:02 PM
 */
@Stateless
public class CompanyDaoBean implements CompanyDao {
	@PersistenceContext(name = "games")
	EntityManager em;

	@Override
	public Company getCompany(int id) {
		return em.find(Company.class, id);
	}

	@Override
	public List<Company> getCompanyList() {
		return em.createQuery("SELECT c FROM Company AS c", Company.class).getResultList();
	}
}
