package examples.dao.impl;

import examples.dao.UserDao;
import examples.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserDaoBean implements UserDao{

	@PersistenceContext(name = "games")
	EntityManager em;
	@Override
	public User getUser(int id) {
		return em.find(User.class, id);
	}

	@Override
	public List<User> getUserList() {
		return em.createQuery("SELECT u FROM User AS u", User.class).getResultList();
	}
}
