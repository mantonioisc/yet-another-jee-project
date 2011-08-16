package examples.dao;

import examples.domain.Developer;

import javax.ejb.Local;
import java.util.List;

/**
 * Date: 8/15/11
 * Time: 8:58 PM
 */
@Local
public interface DeveloperDao {
	Developer getDeveloper(int id);

	List<Developer> getDeveloperList();
}
