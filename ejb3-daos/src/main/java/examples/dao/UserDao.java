package examples.dao;


import examples.domain.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserDao {
	User getUser(int id);
	List<User> getUserList();
}
